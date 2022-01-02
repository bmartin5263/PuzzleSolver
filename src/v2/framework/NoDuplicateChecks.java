package v2.framework;

public class NoDuplicateChecks implements InsertionStrategy {
    @Override
    public void insert(Node node, PriorityStrategy priorityStrategy) {
        priorityStrategy.insertNoDuplicateCheck(node);
    }
}
