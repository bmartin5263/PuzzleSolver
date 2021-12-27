package solver;

import puzzle.Puzzle;
import queues.QueueingMechanism;

import java.util.Objects;
import java.util.function.BiFunction;

public class SolverBuilder<T extends Puzzle<T>> {

    private T initialState = null;
    private T goalState = null;
    private QueueingMechanism<T> queueingMechanism = null;
    private BiFunction<T, T, Integer> heuristic = (t, t2) -> 0;

    public SolverBuilder<T> initialState(T initialState) {
        this.initialState = initialState;
        return this;
    }

    public SolverBuilder<T> goalState(T goalState) {
        this.goalState = goalState;
        return this;
    }

    public SolverBuilder<T> queueingMechanism(QueueingMechanism<T> queueingMechanism) {
        this.queueingMechanism = queueingMechanism;
        return this;
    }

    public SolverBuilder<T> heuristic(BiFunction<T, T, Integer> heuristic) {
        this.heuristic = heuristic;
        return this;
    }

    public PuzzleSolver<T> build() {
        if (initialState == null || goalState == null || queueingMechanism == null || heuristic == null) {
            throw new IllegalArgumentException();
        }
        return new PuzzleSolver<>(
                Objects.requireNonNull(initialState, "You must provide an initial state of the puzzle to start at"),
                Objects.requireNonNull(goalState, "You must provide a goal state to build towards"),
                Objects.requireNonNull(queueingMechanism, "You must provide a Queue to order the nodes"),
                Objects.requireNonNull(heuristic)
        );
    }

}
