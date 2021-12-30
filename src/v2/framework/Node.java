package v2.framework;

public class Node {

    private final Node parent;                    // Node's Parent
    private final Puzzle state;                   // State of the Puzzle
    private final Action lastAction;              // Last action performed to arrive at this state, nullable
    private final int depth;                      // Depth of the search space
    private final int pathCost;                   // Total cost to get to this node

    public Node(Node parent, Puzzle state, Action lastAction, int depth, int pathCost) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.pathCost = pathCost;
        this.lastAction = lastAction;
    }

    public static Node forStart(Puzzle start) {
        return new Node(null, start, null, 0, 0);
    }

    public Node getParent() {
        return parent;
    }

    public Puzzle getState() {
        return state;
    }

    public Action getLastAction() {
        return lastAction;
    }

    public int getDepth() {
        return depth;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getTotalCost() {
        return getPathCost() + state.heuristicCost();
    }

}
