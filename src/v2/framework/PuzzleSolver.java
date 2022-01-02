package v2.framework;

import java.util.*;

public class PuzzleSolver<T extends Puzzle> {

    private final T start;

    private Set<T> expanded;

    public PuzzleSolver(T start) {
        this.start = start;
        this.expanded = null;
    }

    public Optional<Solution<T>> solve(SolveStrategy strategy) {
        long tick = System.currentTimeMillis();
        this.expanded = new HashSet<>();

        strategy.push(Node.forStart(start));
        while (strategy.isSearching()) {
            Node next = strategy.pop();
            T state = next.getState();

            if (state.isGoal()) {
                long tock = System.currentTimeMillis();
                long time = tock - tick;
                return Optional.of(new Solution<>(listActions(next), next.getPathCost(), next.getDepth(), strategy.getMostQueuedNodes(), strategy.getNumOperations(), time));
            }
            expanded.add(state);
            List<Node> neighbors = findNeighbors(next);
            for (Node node : neighbors) {
                strategy.push(node);
            }
        }
        System.err.println("Failed to find solution after expanding " + expanded.size() + " nodes");
        return Optional.empty();
    }

    @SuppressWarnings("unchecked")
    private List<Node> findNeighbors(Node current) {
        List<Node> neighbors = new ArrayList<>();
        T state = current.getState();
        List<Action<T>> actions = (List<Action<T>>) state.getActions();
        for (Action<T> action : actions) {
            T nextState = action.execute(state);
            if (!expanded.contains(nextState)) {
                neighbors.add(new Node(
                        current,
                        nextState,
                        action,
                        current.getDepth() + 1,
                        current.getPathCost() + action.cost(state)
                ));
            }
        }
        return neighbors;
    }

    private List<Action<T>> listActions(Node goal) {
        List<Action<T>> actions = new ArrayList<>((int) goal.getDepth());
        Node current = goal;
        while (current.getLastAction() != null) {
            actions.add(current.getLastAction());
            current = current.getParent();
        }
        Collections.reverse(actions);
        return actions;
    }
}
