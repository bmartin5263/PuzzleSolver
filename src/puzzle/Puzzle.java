package puzzle;

import java.util.List;

public interface Puzzle<T extends Puzzle<T>> {

    /**
     * Return a list of all possible actions that can be performed on the current state
     */
    List<Action> getActions();

    /**
     * Return a new state based on the action performed
     */
    T doAction(Action action);

    /**
     * Return an uninitialized action
     */
    Action getDefaultAction();

    /**
     * Return the last number moved, used as a hack to get the path cost
     */
    int getCostOfLastMove();

}
