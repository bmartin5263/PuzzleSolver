package v2.framework;

public class Node {

    private final Node parent;
    private final Puzzle state;
    private final Action<?> lastAction;
    private final long depth;
    private final long pathCost;
    private final long heuristicCost;

    public Node(Node parent, Puzzle state, Action<?> lastAction, long depth, long pathCost) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.pathCost = pathCost;
        this.lastAction = lastAction;
        this.heuristicCost = state.heuristicCost();
    }

    public static Node forStart(Puzzle start) {
        return new Node(null, start, null, 0, 0);
    }

    public Node getParent() {
        return parent;
    }

    @SuppressWarnings("unchecked")
    public <T extends Puzzle> T getState() {
        return (T) state;
    }

    @SuppressWarnings("unchecked")
    public <T extends Puzzle> Action<T> getLastAction() {
        return (Action<T>) lastAction;
    }

    public long getDepth() {
        return depth;
    }

    public long getPathCost() {
        return pathCost;
    }

    public long getTotalCost() {
        return getHeuristicCost() + getPathCost();
    }

    public long getHeuristicCost() {
        return heuristicCost;
    }
}
