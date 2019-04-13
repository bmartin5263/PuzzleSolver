package queues;

import solver.Node;

import java.util.Comparator;

/**
 * Helper class for the PriorityQueue implementation
 */
public class CompareByPathCost implements Comparator<Node> {

    /**
     * Compare the Node's path costs
     */
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getPathCost(), o2.getPathCost());
    }
}
