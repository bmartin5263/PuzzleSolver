package v2.framework;

public class DuplicateChecks implements InsertionStrategy {
    @Override
    public void insert(Node node, PriorityStrategy priorityStrategy) {
        priorityStrategy.insertWithDuplicateCheck(node);
    }
}
