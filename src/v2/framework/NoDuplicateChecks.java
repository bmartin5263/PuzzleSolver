package v2.framework;

public class NoDuplicateChecks implements InsertionStrategy {
    @Override
    public void insert(Node node, AStarStrategy aStarStrategy) {
        aStarStrategy.insertNoDuplicateCheck(node);
    }
}
