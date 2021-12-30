package v1.queues;

import v1.solver.Node;

import java.util.Comparator;

/**
 * Helper class for the PriorityQueue implementation
 */
public class CompareByPathAndHeuristic implements Comparator<Node> {

    /**
     * Compare the Node's path and heuristic scores
     */
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getTotalCost(), o2.getTotalCost());
    }
}
