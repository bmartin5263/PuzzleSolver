package puzzle.eight_puzzle;

import puzzle.Action;
import puzzle.Puzzle;

import java.util.*;

public class EightPuzzle implements Puzzle<EightPuzzle> {

    public enum EightAction implements Action {
        UNINITIALIZED, UP, DOWN, LEFT, RIGHT
    }

    public final int[] board;          // Current Puzzle State
    public final int blankIndex;       // Index of the blank (zero) space
    public final int lastMoveCost;         // The number last moved to arrive at this state

    private static int findBlank(int[] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) return i;
        }
        return -1;
    }

    public EightPuzzle(int[] board) {
        this(board, findBlank(board));
    }

    public EightPuzzle(int[] board, int blankIndex) {
        this(board, blankIndex, 0);
    }

    public EightPuzzle(int[] board, int blankIndex, int lastMoveCost) {
        this.board = board;
        this.blankIndex = blankIndex;
        this.lastMoveCost = lastMoveCost;
    }

    static void swap(int[] board, int x, int y) {
        int temp = board[x];
        board[x] = board[y];
        board[y] = temp;
    }

    /**
     * Return a new state based on the action performed
     */
    public EightPuzzle doAction(Action action) {
        int[] newBoard = Arrays.copyOf(board, 9);
        int newBlank = -1;
        switch ((EightAction)action) {
            case UP:
                newBlank = blankIndex - 3;
                break;
            case DOWN:
                newBlank = blankIndex + 3;
                break;
            case RIGHT:
                newBlank = blankIndex + 1;
                break;
            case LEFT:
                newBlank = blankIndex - 1;
                break;
            case UNINITIALIZED:
                throw new IllegalArgumentException("Action cannot be UNINITIALIZED");
        }
        try {
            swap(newBoard, blankIndex, newBlank);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Arrays.toString(board));
            System.out.println(action);
        }
        return new EightPuzzle(newBoard, newBlank, 1);
    }

    /**
     * Return a list of all possible actions that can be performed on the current state
     */
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
        int modulo3 = blankIndex % 3;
        if (blankIndex + 3 <= 8) actions.add(EightAction.DOWN);
        if (blankIndex - 3 >= 0) actions.add(EightAction.UP);
        if (modulo3 != 2) actions.add(EightAction.RIGHT);
        if (modulo3 != 0) actions.add(EightAction.LEFT);
        return actions;
    }

    /**
     * Return an uninitialized action
     */
    @Override
    public Action getDefaultAction() {
        return EightAction.UNINITIALIZED;
    }

    /**
     * Return the last number moved, used as a hack to get the path cost
     */
    @Override
    public int getCostOfLastMove() {
        return lastMoveCost;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EightPuzzle)) return false;
        if (obj == this) return true;
        EightPuzzle other = (EightPuzzle) obj;
        return Arrays.equals(board, other.board);
    }

    @Override
    public String toString() {
        return Arrays.toString(board);
    }


    public int incorrectPositionHeuristic(EightPuzzle goal) {
        int score = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] != goal.board[i]) score++;
        }
        return score;
    }

    public int distanceFromIndexHeuristic(EightPuzzle goal) {
        int score = 0;
        Map<Integer, Integer> locationMap = new HashMap<>();
        for (int i = 0; i < goal.board.length; i++) {
            locationMap.put(goal.board[i], i);
        }
        for (int i = 0; i < board.length; i++) {
            score += distanceTo(i, locationMap.get(board[i]));
        }
        return score;
    }

    private int distanceTo(int i, int j) {
        int x1 = i % 3;
        int y1 = i / 3;
        int x2 = j % 3;
        int y2 = j / 3;
        return Math.abs(x2 - x1) + Math.abs(y2 - y1);
    }
}
