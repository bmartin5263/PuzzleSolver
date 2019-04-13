package puzzle;

public interface Command<T extends Puzzle<T>> {

    T execute();
    T getState();
    int getCost();

}
