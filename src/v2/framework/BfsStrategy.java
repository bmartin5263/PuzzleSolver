package v2.framework;

import java.util.ArrayDeque;
import java.util.Deque;

public class BfsStrategy implements SolveStrategy {

    private Deque<Node> queue;

    public BfsStrategy() {
        this.queue = new ArrayDeque<>();
    }

    @Override
    public void push(Node node) {
        this.queue.add(node);
    }

    @Override
    public boolean shouldContinueSearching() {
        return !queue.isEmpty();
    }

    @Override
    public Node pop() {
        return queue.remove();
    }
}
