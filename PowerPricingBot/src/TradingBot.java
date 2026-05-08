package powerbot;

import java.util.ArrayList;
import java.util.List;

/**
 * Trading bot that exploits LMP differentials across the 3-node network.
 *
 * Trading logic:
 *   - Buy energy at the low-LMP node, sell at the high-LMP node.
 *   - Volume is limited by available transmission capacity on that path.
 *   - A trade is only executed when (LMP_high - LMP_low) > MIN_SPREAD_THRESHOLD
 *     to cover transaction costs and slippage.
 *
 * Financial Transmission Rights (FTRs) are used to lock in the price spread
 * between two nodes without physically moving power — the settlement is:
 *   profit = (LMP_sell - LMP_buy) * MW_traded
 */
public class TradingBot {

    private static final double MIN_SPREAD_THRESHOLD = 1.0; // $/MWh

    private final Node nodeA, nodeB, nodeC;
    private final TransmissionLine lineAB, lineBC, lineCA;

    private double totalPnL = 0.0;
    private final List<String> tradeLog = new ArrayList<>();

    public TradingBot(Node nodeA, Node nodeB, Node nodeC,
                      TransmissionLine lineAB, TransmissionLine lineBC,
                      TransmissionLine lineCA) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.nodeC = nodeC;
        this.lineAB = lineAB;
        this.lineBC = lineBC;
        this.lineCA = lineCA;
    }

    /**
     * Evaluates all node-pair spreads and executes profitable FTR trades.
     * Call this after MarketClearer.clear() has set the LMPs.
     */
    public void trade() {
        tradeLog.clear();
        tradeLog.add("=== Trading Round ===");

        evaluateSpread(nodeA, nodeB, lineAB);
        evaluateSpread(nodeB, nodeC, lineBC);
        evaluateSpread(nodeC, nodeA, lineCA);

        // Also check reverse paths (flow can go either direction).
        evaluateSpread(nodeB, nodeA, lineAB);
        evaluateSpread(nodeC, nodeB, lineBC);
        evaluateSpread(nodeA, nodeC, lineCA);
    }

    /**
     * Evaluates the spread between buyNode and sellNode over the given line.
     * Executes a trade if the spread exceeds the threshold and line has capacity.
     */
    private void evaluateSpread(Node buyNode, Node sellNode, TransmissionLine line) {
        double spread = sellNode.lmp - buyNode.lmp;
        if (spread <= MIN_SPREAD_THRESHOLD) return;

        // Available capacity depends on current flow direction.
        double availableCapacity;
        if (line.from == buyNode) {
            availableCapacity = line.capacity - line.flow; // headroom in normal direction
        } else {
            availableCapacity = line.capacity + line.flow; // headroom in reverse direction
        }
        availableCapacity = Math.max(0, availableCapacity);
        if (availableCapacity < 1.0) return; // too congested to trade

        double mwTraded = Math.min(availableCapacity, 50.0); // cap at 50 MW per trade
        double pnl = spread * mwTraded;
        totalPnL += pnl;

        String msg = String.format(
            "  TRADE: Buy %.1f MW @ %s ($%.2f/MWh) | Sell @ %s ($%.2f/MWh) | "
            + "Spread=$%.2f/MWh | PnL=$%.2f",
            mwTraded, buyNode.name, buyNode.lmp, sellNode.name, sellNode.lmp, spread, pnl);
        tradeLog.add(msg);
    }

    public void printTrades() {
        tradeLog.forEach(System.out::println);
        System.out.printf("  Cumulative PnL: $%.2f%n", totalPnL);
    }

    public double getTotalPnL() { return totalPnL; }
}
