package v2.framework;

public class AStarStrategy extends PriorityStrategy {
    @Override
    public long getComparisonValue(Node n) {
        return n.getTotalCost();
    }
}
