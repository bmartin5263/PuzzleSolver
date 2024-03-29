package v2.framework;

import java.util.ArrayDeque;
import java.util.Deque;

public class BfsStrategy extends SolveStrategy {

    private final Deque<Node> queue;

    public BfsStrategy() {
        this.queue = new ArrayDeque<>();
    }

    @Override
    public void insert(Node node) {
        this.queue.add(node);
    }

    @Override
    public boolean isSearching() {
        return !queue.isEmpty();
    }

    @Override
    public Node next() {
        return queue.remove();
    }

    @Override
    long size() {
        return queue.size();
    }
}
