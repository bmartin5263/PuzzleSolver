package queues;

import puzzle.Puzzle;
import solver.Node;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class PriorityNodeQueue<T extends Puzzle> implements QueueingMechanism<T> {

    private PriorityQueue<Node<T>> priorityQueue;
    private HashSet<T> states;
    private int maxSize;
    private int pops;

    public PriorityNodeQueue(Comparator<Node> comparator) {
        priorityQueue = new PriorityQueue<>(comparator);
        states = new HashSet<>();
        maxSize = 0;
        pops = 0;
    }

    /**
     * Adds a node to the priority queue if it's state isn't present. If it's state is present, the node
     * is removed from the priority queue only if the new node's cost (comparator dependant) is less than
     * the cost of the already-enqueued node.
     */
    @Override
    public void addOrUpdate(Node<T> newNode) {
        // Is the node's state already in the queue?
        if (states.contains(newNode.getState())) {
            // Remove the node if its path cost is greater than the new one.
            boolean removedNode = priorityQueue.removeIf(new Predicate<Node<T>>() {
                 @Override
                 public boolean test(Node<T> tNode) {
                     return (newNode.getState().equals(tNode.getState())) && (priorityQueue.comparator().compare(newNode, tNode) < 0);
                 }
             });
            // Was a node removed?
            if (removedNode) {
                priorityQueue.add(newNode);
            } else {
                return;
            }
        } else {
            states.add(newNode.getState());
            priorityQueue.add(newNode);
        }
        // Update the max size
        if (priorityQueue.size() > maxSize) {
            maxSize = priorityQueue.size();
        }
    }

    /**
     * Pop the node with the lowest score.
     */
    @Override
    public Node<T> remove() {
        Node<T> polled = priorityQueue.poll();
        states.remove(polled.getState());
        pops++;
        return polled;
    }

    @Override
    public int size() {
        return priorityQueue.size();
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
        priorityQueue.clear();
        states.clear();
    }

}
