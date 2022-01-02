package v2.framework;

public class GreedyStrategy extends PriorityStrategy {

    public GreedyStrategy() {
    }

    public GreedyStrategy(InsertionStrategy insertionStrategy) {
        super(insertionStrategy);
    }

    @Override
    public long getComparisonValue(Node n) {
        return n.getHeuristicCost();
    }
}
