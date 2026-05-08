package powerbot;

/**
 * Directed transmission line from `from` to `to`.
 * Positive flow means power moves from→to; negative means reverse.
 * Capacity limit applies in both directions.
 */
public class TransmissionLine {
    public final String name;
    public final Node from;
    public final Node to;
    public double flow;       // MW (positive = from→to)
    public double capacity;   // MW max in either direction

    public TransmissionLine(String name, Node from, Node to, double capacity) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.capacity = capacity;
    }

    public boolean isCongested() {
        return Math.abs(flow) >= capacity - 1e-6;
    }

    @Override
    public String toString() {
        String congMark = isCongested() ? " [CONGESTED]" : "";
        return String.format("Line %s (%s→%s) | flow=%.2f/%.1f MW%s",
                name, from.name, to.name, flow, capacity, congMark);
    }
}
