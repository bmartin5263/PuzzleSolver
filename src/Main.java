import v2.framework.DfsStrategy;
import v2.framework.PuzzleSolver;
import v2.framework.Solution;
import v2.lights_out.LightsOut;
//import solver.PuzzleSolver;
//import solver.Solution;
//import solver.SolverBuilder;

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
                {1, 1, 0},
                {1, 0, 0},
                {0, 1, 0}
        };

        PuzzleSolver puzzleSolver = new PuzzleSolver(new LightsOut(start));
        Solution solution = puzzleSolver.solve(new DfsStrategy());
        System.out.println(solution);
    }

}
