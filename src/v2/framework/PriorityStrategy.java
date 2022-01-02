package v2.framework;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public abstract class PriorityStrategy extends SolveStrategy {

    private final TreeSet<Node> queue;
    private final Map<Puzzle, Node> lookup;
    private final InsertionStrategy insertionStrategy;

    public PriorityStrategy() {
        this(new DuplicateChecks());
    }

    public PriorityStrategy(InsertionStrategy insertionStrategy) {
        this.queue = new TreeSet<>(Comparator.comparingLong(this::getComparisonValue));
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
    public boolean isSearching() {
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

    public abstract long getComparisonValue(Node n);
}
