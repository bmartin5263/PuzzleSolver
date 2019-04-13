package eight_puzzle_demo;

import puzzle.EightPuzzle;
import queues.DFSStack;
import solver.PuzzleSolver;
import solver.Solution;
import solver.SolverBuilder;

public class IterativeDeepeningSolution {

    public static void run() {

        PuzzleSolver<EightPuzzle> puzzleSolver;
        Solution s;
        int i;

        System.out.println("===== Iterative Deepening =====");

            puzzleSolver = new SolverBuilder<EightPuzzle>()
                    .goalState(Constants.GOAL)
                    .initialState(Constants.EASY)
                    .queueingMechanism(new DFSStack<>())
                    .build();


        System.out.println("Running Easy...");

            i = 0;
            s = puzzleSolver.solve(i);
            while (s == null) {
                s = puzzleSolver.solve(++i);
            }
            s.verify(Constants.EASY, Constants.GOAL);
            System.out.println(s);

        System.out.println("Running Medium...");

            puzzleSolver.setInitialState(Constants.MEDIUM);

            i = 0;
            s = puzzleSolver.solve(i);
            while (s == null) {
                s = puzzleSolver.solve(++i);
            }
            s.verify(Constants.MEDIUM, Constants.GOAL);
            System.out.println(s);

        System.out.println("Running Hard...");

            puzzleSolver.setInitialState(Constants.HARD);

            i = 0;
            s = puzzleSolver.solve(i);
            while (s == null) {
                s = puzzleSolver.solve(++i);
            }
            s.verify(Constants.HARD, Constants.GOAL);
            System.out.println(s);
    }

}
