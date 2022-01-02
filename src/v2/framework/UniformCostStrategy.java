package v2.framework;

public class UniformCostStrategy extends PriorityStrategy {

    public UniformCostStrategy() {
    }

    public UniformCostStrategy(InsertionStrategy insertionStrategy) {
        super(insertionStrategy);
    }

    @Override
    public long getComparisonValue(Node n) {
        return n.getPathCost();
    }
}
