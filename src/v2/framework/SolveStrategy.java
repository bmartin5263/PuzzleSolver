package v2.framework;

public interface SolveStrategy {
    void push(Node node);
    Node pop();
    boolean shouldContinueSearching();
}
