package solver;

import puzzle.Action;
import puzzle.Puzzle;
import queues.QueueingMechanism;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;

/**
 * PuzzleSolver is a general purpose single-agent puzzle solver that can be used to solve objects
 * that implement "Puzzle". PuzzleSolver takes in an initial state, a goal state, a successor function, and a
 * queue. The successor function will determine the path costs and children for a given state while the
 * queue will keep track of search ordering.
 */
public class PuzzleSolver<T extends Puzzle<T>> {

    private T initialState;                             // Where we start
    private T goalState;                                // Where we finish
    private QueueingMechanism<T> queue;                 // How we determine which node is next
    private BiFunction<T, T, Integer> heuristic;        // Puzzle method to get a heuristic score
    private HashSet<T> visited;                         // Keep Track of Expanded Nodes

    /**
     * Only Constructor, initialize all relevant data
     */
    public PuzzleSolver(T initialState, T goalState, QueueingMechanism<T> queue, BiFunction<T, T, Integer> heuristic) {
        this.initialState = initialState;
        this.goalState = goalState;
        this.queue = queue;
        this.heuristic = heuristic;
        this.visited = new HashSet<>();
    }

    /**
     * Reset the initial state to a new one
     */
    public void setInitialState(T initialState) {
        this.initialState = initialState;
    }

    /**
     * Compile a list of actions to get from the initial state to the finish node
     */
    public List<Action> compileActionList(Node<T> finish) {
        // Create our action list
        List<Action> actions = new ArrayList<>(finish.getDepth());

        // Go bottom-up the search space until we reach the root
        Node<T> current = finish;
        while (current != null) {
            actions.add(current.getLastAction());
            current = current.getParent();
        }

        // Chop off the root node action and reverse the actions
        actions = actions.subList(0, actions.size()-1);
        Collections.reverse(actions);

        return actions;
    }

    /**
     * Solve the puzzle. Optional argument maxDepth can limit the depth of the search (for Iterative Deepening),
     * but can be set to '-1' to ignore.
     */
    public Solution solve(int maxDepth) {
        // Reset internal data
        reset();

        // Add in the initial node to the queue
        queue.addOrUpdate(new Node<>(initialState, heuristic.apply(initialState, goalState)));

        while (queue.size() > 0) {
            // Remove the current node (removal algorithm is determined by the QueueingMechanism provided)
            Node<T> current = queue.remove();
            T state = current.getState();

            if (state.equals(goalState)) {
                // We found the goal! Compile the solution information
                return new Solution<T>(current.getDepth(), compileActionList(current), queue.getMaxSize(), current.getPathCost(), queue.getNumberOfPops());
            }

            // Mark State as Visited
            visited.add(state);

            // Should we go deeper?
            if (maxDepth == -1 || current.getDepth() < maxDepth) {
                // Call the successor function to get the next nodes.
                List<Node<T>> children = successorFunction(current);
                for (Node<T> child : children) {
                    // Add the child node (addOrUpdate algorithm is determined by the provided QueueingMechanism)
                    if (!visited.contains(child.getState())) {
                        queue.addOrUpdate(child);
                    }
                }
            }
        }

        // No solution found
        return null;
    }

    private void reset() {
        visited.clear();
        queue.clear();
    }

    /**
     * Returns list of nodes representing all the possible next states for a particular node
     */
    public List<Node<T>> successorFunction(Node<T> parent) {
        // Create a list of nodes
        List<Node<T>> nodeList = new ArrayList<>();
        T currentState = parent.getState();

        for (Action action : currentState.getActions()) {
            T nextState = currentState.doAction(action);
            nodeList.add(new Node<>(
                    parent,
                    nextState,
                    action,
             parent.getDepth() + 1,
           parent.getPathCost() + nextState.getCostOfLastMove(),
                    heuristic.apply(nextState, goalState))
            );
        }

        return nodeList;
    }

}
