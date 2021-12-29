import puzzle.lights_out.LightsOut;
import queues.CompareByHeuristic;
import queues.PriorityNodeQueue;
import solver.PuzzleSolver;
import solver.Solution;
import solver.SolverBuilder;

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
                {0, 0, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        PuzzleSolver<LightsOut> puzzleSolver = new SolverBuilder<LightsOut>()
                .goalState(LightsOut.goalState(3))
                .initialState(new LightsOut(start))
                .queueingMechanism(new PriorityNodeQueue<>(new CompareByHeuristic()))
                .heuristic(LightsOut::numberOfWrongCells)
                .build();

        Solution solution = puzzleSolver.solve(-1);
        System.out.println(solution.toString());
    }

}
