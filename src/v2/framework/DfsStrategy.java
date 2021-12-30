package v2.framework;

import java.util.ArrayDeque;
import java.util.Deque;

public class DfsStrategy implements SolveStrategy {

    private final Deque<Node> stack;
    private final int maxDepth;

    public DfsStrategy() {
        this.stack = new ArrayDeque<>();
        this.maxDepth = -1;
    }

    public DfsStrategy(int maxDepth) {
        this.stack = new ArrayDeque<>();
        this.maxDepth = maxDepth;
    }

    @Override
    public void push(Node node) {
        if (maxDepth != -1) {
            if (node.getDepth() <= maxDepth) {
                this.stack.push(node);
            }
        }
        else {
            this.stack.push(node);
        }
    }

    @Override
    public boolean shouldContinueSearching() {
        return !stack.isEmpty();
    }

    @Override
    public Node pop() {
        return stack.pop();
    }
}
