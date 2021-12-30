package v2.framework;

public class Node {

    private final Node parent;                    // Node's Parent
    private final Puzzle state;                   // State of the Puzzle
    private final Action<?> lastAction;              // Last action performed to arrive at this state, nullable
    private final long depth;                      // Depth of the search space
    private final long pathCost;                   // Total cost to get to this node
    private final long heuristicCost;
    private final long totalCost;

    public Node(Node parent, Puzzle state, Action<?> lastAction, long depth, long pathCost) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.pathCost = pathCost;
        this.lastAction = lastAction;
        this.heuristicCost = state.heuristicCost();
        this.totalCost = pathCost + heuristicCost;
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
        return totalCost;
    }

}
