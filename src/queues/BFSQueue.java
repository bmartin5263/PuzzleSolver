package queues;

import puzzle.Puzzle;
import solver.Node;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Wrapper for Java's Deque Class
 */
public class BFSQueue<T extends Puzzle> implements QueueingMechanism<T> {

    private Queue<Node<T>> queue;
    private int maxSize;
    private int pops;

    public BFSQueue() {
        queue = new ArrayDeque<>();
        maxSize = 0;
    }

    @Override
    public void addOrUpdate(Node<T> node) {
        queue.add(node);        // <-- Checking if the node is already in the queue here make the algorithm very slow.
        if (queue.size() > maxSize) {
            maxSize = queue.size();
        }
    }

    @Override
    public Node<T> remove() {
        pops++;
        return queue.poll();
    }

    @Override
    public int size() {
        return queue.size();
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
        queue.clear();
        pops = 0;
        maxSize = 0;
    }

}
