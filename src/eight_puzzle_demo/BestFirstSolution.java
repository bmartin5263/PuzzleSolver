package eight_puzzle_demo;

import queues.CompareByHeuristic;
import queues.PriorityNodeQueue;
import solver.PuzzleSolver;
import solver.Solution;
import solver.SolverBuilder;

public class BestFirstSolution {

    public static void run() {
        PuzzleSolver<EightPuzzle> puzzleSolver;
        Solution s;

        System.out.println("===== Best-First Search =====");

            puzzleSolver = new SolverBuilder<EightPuzzle>()
                    .goalState(Constants.GOAL)
                    .initialState(Constants.EASY)
                    .queueingMechanism(new PriorityNodeQueue<>(new CompareByHeuristic()))
                    .heuristic(EightPuzzle::incorrectPositionHeuristic)
                    .build();

        System.out.println("Running Easy...");

            s = puzzleSolver.solve(-1);
            s.verify(Constants.EASY, Constants.GOAL);
            System.out.println(s);

        System.out.println("Running Medium...");

            puzzleSolver.setInitialState(Constants.MEDIUM);
            s = puzzleSolver.solve(-1);
            s.verify(Constants.MEDIUM, Constants.GOAL);
            System.out.println(s);

        System.out.println("Running Hard...");

            puzzleSolver.setInitialState(Constants.HARD);
            s = puzzleSolver.solve(-1);
            s.verify(Constants.HARD, Constants.GOAL);
            System.out.println(s);
    }

}
