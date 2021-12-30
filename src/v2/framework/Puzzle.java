package v2.framework;

import java.util.List;

public interface Puzzle {
    List<? extends Action<? extends Puzzle>> getActions();
    int heuristicCost();
    boolean isGoal();
}
