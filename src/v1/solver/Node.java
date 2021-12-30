package v1.solver;

import v1.puzzle.Action;
import v1.puzzle.Puzzle;

public class Node<T extends Puzzle> {

    private Node<T> parent;                 // Node's Parent
    private T state;                        // State of the Puzzle
    private Action lastAction;              // Last action performed to arrive at this state
    private int depth;                      // Depth of the search space
    private int pathCost;                   // Total cost to get to this node
    private int heuristicEstimate;          // Heuristic Estimate

    public Node(T state, int heuristicEstimate) {
        this(null, state, state.getDefaultAction(), 0, state.getCostOfLastMove(), heuristicEstimate);
    }

    public Node(Node<T> parent, T state, Action action, int depth, int pathCost, int heuristicEstimate) {
        this.parent = parent;
        this.state = state;
        this.depth = depth;
        this.pathCost = pathCost;
        this.lastAction = action;
        this.heuristicEstimate = heuristicEstimate;
    }


    public int getDepth() {
        return depth;
    }

    public T getState() {
        return state;
    }

    public int getPathCost() {
        return pathCost;
    }

    public int getHeuristicCost() {
        return heuristicEstimate;
    }

    public int getTotalCost() {
        return pathCost + heuristicEstimate;
    }

    public Action getLastAction() {
        return lastAction;
    }

    public Node<T> getParent() {
        return parent;
    }

}
