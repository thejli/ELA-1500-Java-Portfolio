package powerbot;

import java.util.Arrays;

/**
 * Clears the 3-node ring network using DC economic dispatch.
 *
 * Balance constraints (as given):
 *   S[A] = demand[A] + T[AB] - T[CA]
 *   S[B] = demand[B] + T[BC] - T[AB]
 *   S[C] = demand[C] + T[CA] - T[BC]
 *
 * For a lossless ring with uniform reactances, the DC power flow gives:
 *   T[AB] = (p[A] - p[B]) / 3      where p[i] = S[i] - demand[i]
 *   T[BC] = (p[B] - p[C]) / 3
 *   T[CA] = (p[C] - p[A]) / 3
 *
 * These formulas satisfy all three balance equations by construction
 * whenever p[A] + p[B] + p[C] = 0 (total supply = total demand).
 *
 * When a line hits its capacity limit, counter-scheduling re-dispatch
 * moves generation toward the import-constrained node.
 *
 * LMPs are the shadow prices of the nodal balance constraints.
 * In the uncongested case all LMPs equal system lambda. With congestion
 * each zone's LMP equals the marginal cost of its local marginal unit.
 */
public class MarketClearer {

    private final Node nodeA, nodeB, nodeC;
    private final TransmissionLine lineAB, lineBC, lineCA;

    public MarketClearer(Node nodeA, Node nodeB, Node nodeC,
                         TransmissionLine lineAB, TransmissionLine lineBC,
                         TransmissionLine lineCA) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.nodeC = nodeC;
        this.lineAB = lineAB;
        this.lineBC = lineBC;
        this.lineCA = lineCA;
    }

    public void clear() {
        double totalDemand = nodeA.demand + nodeB.demand + nodeC.demand;

        // Step 1: Unconstrained economic dispatch (cheapest units first).
        Node[] byMargCost = {nodeA, nodeB, nodeC};
        Arrays.sort(byMargCost, (a, b) -> Double.compare(a.marginalCost, b.marginalCost));

        double remaining = totalDemand;
        for (Node n : byMargCost) {
            n.supply = Math.min(n.maxSupply, remaining);
            remaining -= n.supply;
        }
        if (remaining > 1e-3) {
            System.out.printf("WARNING: Short by %.2f MW (insufficient generation)%n", remaining);
        }

        // Step 2: DC power flow.
        updateFlows();

        // Step 3: Re-dispatch to relieve congestion (iterative, fractional step).
        for (int iter = 0; iter < 300; iter++) {
            TransmissionLine worst = worstViolation();
            if (worst == null) break;

            double excess = Math.abs(worst.flow) - worst.capacity;
            // Moving δ MW from the export node to the import node reduces
            // this line's flow by 2δ/3. Solve for δ:
            double needed = excess * 3.0 / 2.0;
            // Dampen to avoid oscillation in meshed networks.
            double step = needed * 0.6;

            // Identify which end to shed and which to pick up.
            Node shed = (worst.flow > 0) ? worst.from : worst.to;
            Node pick = (worst.flow > 0) ? worst.to   : worst.from;

            double canShift = Math.min(step,
                              Math.min(shed.supply, pick.maxSupply - pick.supply));

            if (canShift < 1e-6) {
                // Primary path blocked; try the third node as intermediary.
                Node third = thirdNode(shed, pick);
                canShift = Math.min(step,
                           Math.min(shed.supply, third.maxSupply - third.supply));
                if (canShift < 1e-6) break;
                shed.supply  -= canShift;
                third.supply += canShift;
            } else {
                shed.supply -= canShift;
                pick.supply += canShift;
            }
            updateFlows();
        }

        // Step 4: Compute LMPs.
        computeLMPs();
    }

    /** DC power flow for a lossless ring with uniform reactances. */
    private void updateFlows() {
        double pA = nodeA.supply - nodeA.demand;
        double pB = nodeB.supply - nodeB.demand;
        double pC = nodeC.supply - nodeC.demand;
        lineAB.flow = (pA - pB) / 3.0;
        lineBC.flow = (pB - pC) / 3.0;
        lineCA.flow = (pC - pA) / 3.0;
    }

    private TransmissionLine worstViolation() {
        TransmissionLine worst = null;
        double worstExcess = 1e-6; // tolerance
        for (TransmissionLine l : new TransmissionLine[]{lineAB, lineBC, lineCA}) {
            double excess = Math.abs(l.flow) - l.capacity;
            if (excess > worstExcess) {
                worstExcess = excess;
                worst = l;
            }
        }
        return worst;
    }

    private Node thirdNode(Node a, Node b) {
        if (nodeA != a && nodeA != b) return nodeA;
        if (nodeB != a && nodeB != b) return nodeB;
        return nodeC;
    }

    /**
     * LMP = shadow price of the nodal balance constraint.
     *
     * From KKT conditions: if a generator is interior (0 < S < S_max),
     * its node's LMP equals its marginal cost. For uncongested networks
     * this collapses to a single system lambda.
     */
    private void computeLMPs() {
        // Find system lambda = cost of the last (most expensive) dispatched unit.
        double systemLambda = 0;
        Node[] sorted = {nodeA, nodeB, nodeC};
        Arrays.sort(sorted, (a, b) -> Double.compare(b.marginalCost, a.marginalCost)); // high to low
        for (Node n : sorted) {
            if (n.supply > 1e-3) { systemLambda = n.marginalCost; break; }
        }

        nodeA.lmp = systemLambda;
        nodeB.lmp = systemLambda;
        nodeC.lmp = systemLambda;

        // Adjust for congestion: interior marginal units pin their node's LMP.
        for (Node n : new Node[]{nodeA, nodeB, nodeC}) {
            if (n.supply > 1e-3 && n.supply < n.maxSupply - 1e-3) {
                n.lmp = n.marginalCost;
            }
        }
    }

    /**
     * Checks all three balance constraints to within 0.1 MW tolerance.
     * The DC flow formula guarantees exact balance whenever supply == demand in total;
     * small residuals can arise from the congestion re-dispatch damping.
     */
    public boolean verifyBalance() {
        double tol = 0.1;
        double errA = Math.abs(nodeA.supply - (nodeA.demand + lineAB.flow - lineCA.flow));
        double errB = Math.abs(nodeB.supply - (nodeB.demand + lineBC.flow - lineAB.flow));
        double errC = Math.abs(nodeC.supply - (nodeC.demand + lineCA.flow - lineBC.flow));
        return errA < tol && errB < tol && errC < tol;
    }
}
