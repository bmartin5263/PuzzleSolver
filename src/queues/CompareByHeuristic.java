package queues;

import solver.Node;

import java.util.Comparator;

/**
 * Helper class for the PriorityQueue implementation
 */
public class CompareByHeuristic implements Comparator<Node> {

    /**
     * Compare the Node's heuristic scores
     */
    @Override
    public int compare(Node o1, Node o2) {
        return Integer.compare(o1.getHeuristicCost(), o2.getHeuristicCost());
    }
}
