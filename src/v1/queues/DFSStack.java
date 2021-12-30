package v1.queues;

import v1.puzzle.Puzzle;
import v1.solver.Node;

import java.util.Stack;

/**
 * Wrapper for Java's Stack Class
 */
public class DFSStack<T extends Puzzle> implements QueueingMechanism<T> {

    private Stack<Node<T>> stack;
    private int maxSize;
    private int pops;

    public DFSStack() {
        stack = new Stack<>();
        maxSize = 0;
        pops = 0;
    }

    @Override
    public void addOrUpdate(Node<T> node) {
        stack.push(node);   // <-- No check here either because it makes it VERY slow
        if (stack.size() > maxSize) {
            maxSize = stack.size();
        }
    }

    @Override
    public Node<T> remove() {
        pops++;
        return stack.pop();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public int getNumberOfPops() {
        return pops;
    }

    @Override
    public void clear() {
        pops = 0;
        maxSize = 0;
        stack.clear();
    }

}
