package powerbot;

/**
 * Power Pricing Bot — 3-node ring network simulation.
 *
 * Network topology (ring):
 *
 *        A
 *       / \
 *    AB/   \CA
 *     /     \
 *    B ---BC- C
 *
 * Balance constraints (as given):
 *   S[A] = demand[A] + T[AB] - T[CA]
 *   S[B] = demand[B] + T[BC] - T[AB]
 *   S[C] = demand[C] + T[CA] - T[BC]
 *
 * Positive flow on AB means power moves A→B, etc.
 */
public class PowerPricingBotMain {

    public static void main(String[] args) {
        System.out.println("=== Power Pricing Bot — 3-Node Ring Network ===\n");

        // --- Scenario 1: Uncongested network ---
        // All nodes have headroom; prices should equalize.
        System.out.println("--- Scenario 1: Uncongested Network ---");
        runScenario(
            new Node("A", 100, 200, 30.0),   // cheap generator
            new Node("B", 150, 200, 45.0),   // medium generator
            new Node("C",  80, 200, 60.0),   // expensive generator
            200, 200, 200                     // large line capacities (MW)
        );

        System.out.println();

        // --- Scenario 2: Congested line AB ---
        // Line AB is constrained; A is isolated from B, prices diverge.
        System.out.println("--- Scenario 2: Congested Line AB ---");
        runScenario(
            new Node("A", 50,  300, 25.0),   // very cheap, lots of supply
            new Node("B", 200, 100, 50.0),   // expensive, undersupplied
            new Node("C", 100, 200, 40.0),
            40,   // lineAB capacity (MW) — tight!
            200,
            200
        );

        System.out.println();

        // --- Scenario 3: High demand, multi-line congestion ---
        System.out.println("--- Scenario 3: High Demand / Multi-Congestion ---");
        runScenario(
            new Node("A", 200, 350, 20.0),
            new Node("B", 200, 200, 55.0),
            new Node("C", 150, 200, 35.0),
            80,   // lineAB
            80,   // lineBC
            80    // lineCA
        );
    }

    private static void runScenario(Node a, Node b, Node c,
                                    double capAB, double capBC, double capCA) {
        TransmissionLine ab = new TransmissionLine("AB", a, b, capAB);
        TransmissionLine bc = new TransmissionLine("BC", b, c, capBC);
        TransmissionLine ca = new TransmissionLine("CA", c, a, capCA);

        MarketClearer clearer = new MarketClearer(a, b, c, ab, bc, ca);
        clearer.clear();

        System.out.println("Network State after Market Clearing:");
        System.out.println("  " + a);
        System.out.println("  " + b);
        System.out.println("  " + c);
        System.out.println("  " + ab);
        System.out.println("  " + bc);
        System.out.println("  " + ca);
        System.out.println("  Balance check: " + (clearer.verifyBalance() ? "PASS" : "FAIL"));

        TradingBot bot = new TradingBot(a, b, c, ab, bc, ca);
        bot.trade();
        bot.printTrades();
    }
}
