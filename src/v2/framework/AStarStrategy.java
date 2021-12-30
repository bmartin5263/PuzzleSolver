package v2.framework;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AStarStrategy implements SolveStrategy {

    private final TreeSet<Node> queue;
    private final Map<Puzzle, Node> lookup;

    public AStarStrategy() {
        this.queue = new TreeSet<>(Comparator.comparingInt(Node::getTotalCost));
        this.lookup = new HashMap<>();
    }

    @Override
    public void push(Node node) {
        Puzzle state = node.getState();
        Node insertedNode = lookup.get(state);
        if (insertedNode == null) {
            queue.add(node);
        }
        else if (node.getPathCost() < insertedNode.getPathCost()) {
            queue.remove(insertedNode);
            queue.add(node);
        }
        lookup.put(state, node);
    }

    @Override
    public boolean shouldContinueSearching() {
        return !queue.isEmpty();
    }

    @Override
    public Node pop() {
        return queue.pollFirst();
    }
}
