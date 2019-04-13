package queues;

import puzzle.Puzzle;
import solver.Node;

/**
 * Abstract representation of a collection of nodes where you can addOrUpdate and remove from in only a single
 * way. Classes will implement this interface to define where nodes are added and how they are removed.
 */
public interface QueueingMechanism<T extends Puzzle> {

    /**
     * Adds a node to the queue, or replace a node already on the queue if it has the same state as
     * the new one. This is algorithm dependant, not all algorithms replace the node.
     */
    void addOrUpdate(Node<T> node);

    /**
     * Pop the next node. This is algorithm dependant.
     */
    Node<T> remove();

    /**
     * Get size of queue.
     */
    int size();

    /**
     * Get size of queue at its max.
     */
    int getMaxSize();

    /**
     * Get the number of times the queue was popped.
     */
    int getNumberOfPops();

    /**
     * Clear all queue data.
     */
    void clear();

}
