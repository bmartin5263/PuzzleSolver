import v2.framework.AStarStrategy;
import v2.framework.Action;
import v2.framework.PuzzleSolver;
import v2.framework.Solution;
import v2.lights_out.LightsOut;
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

        int[][] start = new int[][] {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        PuzzleSolver<LightsOut> puzzleSolver = new PuzzleSolver<>(new LightsOut(start));
        Solution<LightsOut> solution = puzzleSolver.solve(new AStarStrategy()).orElse(null);
        System.out.println(solution);
        if (solution != null) {
            for (Action<LightsOut> action : solution.getActions()) {
                System.out.println(action);
            }
        }
    }
}
