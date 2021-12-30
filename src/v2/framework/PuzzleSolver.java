package v2.framework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PuzzleSolver {

    private final Puzzle start;

    private Set<Puzzle> expanded;
    private SolveStrategy strategy;

    public <T extends Puzzle> PuzzleSolver(T start) {
        this.start = start;
        this.expanded = null;
        this.strategy = null;
    }

    public Solution solve(SolveStrategy strategy) {
        this.strategy = strategy;
        this.expanded = new HashSet<>();

        strategy.push(Node.forStart(start));
        while (strategy.shouldContinueSearching()) {
            Node next = strategy.pop();
            Puzzle state = next.getState();

            if (state.isGoal()) {
                return new Solution();
            }
            expanded.add(state);
            List<Node> children = successorFunction(next);
            for (Node node : children) {
                if (!expanded.contains(node.getState())) {
                    strategy.push(node);
                }
            }
        }
        return null;
    }

    private List<Node> successorFunction(Node parent) {
        List<Node> children = new ArrayList<>();
        Puzzle state = parent.getState();
        for (Action<? extends Puzzle> action : state.getActions()) {
            Action<Puzzle> casted = (Action<Puzzle>) action;
            Puzzle nextState = casted.execute(state);
            if (!expanded.contains(nextState)) {
                children.add(new Node(
                        parent,
                        nextState,
                        action,
                        parent.getDepth() + 1,
                        parent.getPathCost() + action.cost()
                ));
            }
        }
        return children;
    }
}
