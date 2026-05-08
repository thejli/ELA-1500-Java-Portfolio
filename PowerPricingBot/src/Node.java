package powerbot;

/**
 * Represents a bus/node in the power network.
 * Supply and demand are in MW; marginalCost is in $/MWh.
 */
public class Node {
    public final String name;
    public double demand;       // MW (fixed, inelastic)
    public double supply;       // MW (decision variable)
    public double maxSupply;    // MW capacity limit
    public double marginalCost; // $/MWh generation cost
    public double lmp;          // Locational Marginal Price $/MWh (computed)

    public Node(String name, double demand, double maxSupply, double marginalCost) {
        this.name = name;
        this.demand = demand;
        this.maxSupply = maxSupply;
        this.marginalCost = marginalCost;
    }

    @Override
    public String toString() {
        return String.format("Node %s | demand=%.1f MW | supply=%.1f MW | LMP=$%.2f/MWh",
                name, demand, supply, lmp);
    }
}
