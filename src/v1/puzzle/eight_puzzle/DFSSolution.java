package v1.puzzle.eight_puzzle;

import v1.queues.DFSStack;
import v1.solver.PuzzleSolver;
import v1.solver.Solution;
import v1.solver.SolverBuilder;

public class DFSSolution {

    public static void run() {
        PuzzleSolver<EightPuzzle> puzzleSolver;
        Solution s;

        /////////////////////////////////////////////////////////////
        System.out.println("===== DFS =====");

        puzzleSolver = new SolverBuilder<EightPuzzle>()
                .goalState(Constants.GOAL)
                .initialState(Constants.EASY)
                .queueingMechanism(new DFSStack<>())
                .build();

        /////////////////////////////////////////////////////////////
        System.out.println("Running Easy...");

        s = puzzleSolver.solve(-1);
        s.verify(Constants.EASY, Constants.GOAL);
        System.out.println(s);

        /////////////////////////////////////////////////////////////
        System.out.println("Running Medium...");
        puzzleSolver.setInitialState(Constants.MEDIUM);

        s = puzzleSolver.solve(-1);
        s.verify(Constants.MEDIUM, Constants.GOAL);
        System.out.println(s);

        /////////////////////////////////////////////////////////////
        System.out.println("Running Hard...");
        puzzleSolver.setInitialState(Constants.HARD);

        s = puzzleSolver.solve(-1);
        s.verify(Constants.HARD, Constants.GOAL);
        System.out.println(s);

    }
}
