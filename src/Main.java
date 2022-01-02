import v2.eight_puzzle.EightPuzzle;
import v2.framework.*;
//import v1.solver.PuzzleSolver;
//import v1.solver.Solution;
//import v1.solver.SolverBuilder;

public class Main {

    public static void main(String[] args) {
//        BFSSolution.run();
//        DFSSolution.run();
//        IterativeDeepeningSolution.run();
//        UniformCostSolution.run();
//        BestFirstSolution.run();
//        AStar1Solution.run();
//        AStar2Solution.run();

        int[] start = new int[] {
                1, 2, 3,
                7, 4, 6,
                5, 8, 0
        };

        PuzzleSolver<EightPuzzle> puzzleSolver = new PuzzleSolver<>(new EightPuzzle(start));
        Solution<EightPuzzle> solution = puzzleSolver.solve(new GreedyStrategy(new NoDuplicateChecks())).orElse(null);
        System.out.println(solution);
        if (solution != null) {
            for (Action<EightPuzzle> action : solution.getActions()) {
                System.out.println(action);
            }
        }
    }
}
