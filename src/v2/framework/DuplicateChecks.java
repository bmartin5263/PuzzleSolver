package v2.framework;

public class DuplicateChecks implements InsertionStrategy {
    @Override
    public void insert(Node node, AStarStrategy aStarStrategy) {
        aStarStrategy.insertWithDuplicateCheck(node);
    }
}
