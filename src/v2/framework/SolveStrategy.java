package v2.framework;

abstract class SolveStrategy {

    private long operations = 0;
    private long mostQueuedNodes = 0;

    public void push(Node node) {
        insert(node);
        mostQueuedNodes = Math.max(mostQueuedNodes, size());
    }
    public Node pop() {
        ++operations;
        return next();
    }

    public abstract boolean isSearching();
    abstract void insert(Node node);
    abstract Node next();
    abstract long size();

    public long getMostQueuedNodes() {
        return mostQueuedNodes;
    }

    public long getNumOperations() {
        return operations;
    }
}
