package solver;

import puzzle.Puzzle;
import queues.QueueingMechanism;

import java.util.function.BiFunction;

public class SolverBuilder<T extends Puzzle<T>> {

    private T _initialState;
    private T _goalState;
    private QueueingMechanism<T> _queueingMechanism;
    private BiFunction<T, T, Integer> _heuristic;

    public SolverBuilder() {
        this._initialState = null;
        this._goalState = null;
        this._queueingMechanism = null;
        this._heuristic = (t, t2) -> 0;
    }

    public SolverBuilder<T> initialState(T initialState) {
        _initialState = initialState;
        return this;
    }

    public SolverBuilder<T> goalState(T goalState) {
        _goalState = goalState;
        return this;
    }

    public SolverBuilder<T> queueingMechanism(QueueingMechanism<T> queueingMechanism) {
        _queueingMechanism = queueingMechanism;
        return this;
    }

    public SolverBuilder<T> heuristic(BiFunction<T, T, Integer> heuristic) {
        _heuristic = heuristic;
        return this;
    }

    public PuzzleSolver<T> build() {
        if (_initialState == null || _goalState == null || _queueingMechanism == null || _heuristic == null) {
            throw new IllegalArgumentException();
        }
        return new PuzzleSolver<>(_initialState, _goalState, _queueingMechanism, _heuristic);
    }

}
