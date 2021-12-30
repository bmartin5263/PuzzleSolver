package v2.framework;

public interface Action<T extends Puzzle> {
    T execute(T inState);
    int cost();
}
