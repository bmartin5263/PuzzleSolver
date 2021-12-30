package v2.framework;

import java.util.List;

public class Solution<T extends Puzzle> {
    private final List<Action<T>> actions;
    private final long cost;
    private final long depth;
    private final long mostQueuedNodes;
    private final long milliseconds;
    private final long operations;

    public Solution(List<Action<T>> actions, long cost, long depth, long mostQueuedNodes, long operations, long milliseconds) {
        this.actions = actions;
        this.cost = cost;
        this.depth = depth;
        this.mostQueuedNodes = mostQueuedNodes;
        this.milliseconds = milliseconds;
        this.operations = operations;
    }

    public List<Action<T>> getActions() {
        return actions;
    }

    public long getCost() {
        return cost;
    }

    public long getDepth() {
        return depth;
    }

    public long getMostQueuedNodes() {
        return mostQueuedNodes;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public long getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        return "Solution(" +
                "cost=" + cost +
                ", depth=" + depth +
                ", mostQueuedNodes=" + mostQueuedNodes +
                ", operations=" + getOperations() +
                ", milliseconds=" + milliseconds +
                ')';
    }
}
