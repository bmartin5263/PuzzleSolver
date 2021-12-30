package v1.puzzle.eight_puzzle;

import v1.queues.DFSStack;
import v1.solver.PuzzleSolver;
import v1.solver.Solution;
import v1.solver.SolverBuilder;

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
