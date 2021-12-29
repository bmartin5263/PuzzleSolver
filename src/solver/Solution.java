package solver;

import puzzle.Action;
import puzzle.Puzzle;

import java.util.List;

public class Solution {

    public final List<Action> actions;
    //public final List<Command<T>> commands;
    public final int depth;
    public final int maxQueue;
    public final int pathCost;
    public final int time;

    public Solution(int depth, List<Action> actions, int maxQueue, int pathCost, int time) {
        this.depth = depth;
        this.actions = actions;
        this.maxQueue = maxQueue;
        this.pathCost = pathCost;
        this.time = time;
        //this.commands = commands;
    }

    /**
     * Check the solution to see if it is valid
     */
    public void verify(Puzzle puzzle, Puzzle goal) {
        Puzzle current = puzzle;
        int cost = 0;
        //System.out.println(current);
        for (Action action : actions) {
            current = current.doAction(action);
            cost += current.getCostOfLastMove();
            //System.out.println("[" + action + "]" + "  Cost = " + current.getCostOfLastMove() + "  Total = " + cost);
            //System.out.println(current);
        }
        //System.out.println();
        if (!current.equals(goal)) throw new IllegalStateException("Solution is invalid");
    }

    /**
     * Check the solution to see if it is valid
     */
    //public void verifyCommands(T puzzle) {
    //    T goal = (T)Constants.GOAL;
    //    T current = puzzle;
    //    int cost = 0;
    //    System.out.println(current);
    //    for (Command<T> command : commands) {
    //        if (!current.equals(command.getState())) throw new IllegalStateException("Solution is invalid");
    //        current = command.execute();
    //        cost += current.getCostOfLastMove();
    //        System.out.println("[" + command.toString() + "]" + "  Cost = " + current.getCostOfLastMove() + "  Total = " + cost);
    //        System.out.println(current);
    //    }
    //    System.out.println();
    //    if (!current.equals(goal)) throw new IllegalStateException("Solution is invalid");
    //}

    @Override
    public String toString() {
        return "- Solution -\n" +
                "Length: " +
                depth +
                " \n" +
                "Space: " +
                maxQueue +
                " \n" +
                "Cost: " +
                pathCost +
                " \n" +
                "Time: " +
                time +
                " \n";
    }
}
