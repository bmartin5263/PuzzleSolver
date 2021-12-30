package v2.framework;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class AStarStrategy extends SolveStrategy {

    private final TreeSet<Node> queue;
    private final Map<Puzzle, Node> lookup;
    private final InsertionStrategy insertionStrategy;

    public AStarStrategy() {
        this(new DuplicateChecks());
    }

    public AStarStrategy(InsertionStrategy insertionStrategy) {
        this.queue = new TreeSet<>(Comparator.comparingLong(Node::getTotalCost));
        this.lookup = new HashMap<>();
        this.insertionStrategy = insertionStrategy;
    }

    @Override
    public void insert(Node node) {
        insertionStrategy.insert(node, this);
    }

    public void insertNoDuplicateCheck(Node node) {
        Puzzle state = node.getState();
        queue.add(node);
        lookup.put(state, node);
    }

    public void insertWithDuplicateCheck(Node node) {
        Puzzle state = node.getState();
        Node insertedNode = lookup.get(state);
        if (insertedNode == null) {
            queue.add(node);
            lookup.put(state, node);
        }
        else if (node.getPathCost() < insertedNode.getPathCost()) {
            queue.remove(insertedNode);
            queue.add(node);
            lookup.put(state, node);
        }
    }

    @Override
    public boolean shouldContinueSearching() {
        return !queue.isEmpty();
    }

    @Override
    long size() {
        return queue.size();
    }

    @Override
    public Node next() {
        return queue.pollFirst();
    }
}
