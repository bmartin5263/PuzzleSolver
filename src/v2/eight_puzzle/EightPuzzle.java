package v2.eight_puzzle;

import v2.framework.Action;
import v2.framework.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EightPuzzle implements Puzzle {

    public final int[] board;          // Current Puzzle State
    public final int blankIndex;       // Index of the blank (zero) space

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
        this.board = board;
        this.blankIndex = blankIndex;
    }

    public int[] copyBoard() {
        return Arrays.copyOf(board, board.length);
    }

    public int blankIndex() {
        return blankIndex;
    }

    static void swap(int[] board, int x, int y) {
        int temp = board[x];
        board[x] = board[y];
        board[y] = temp;
    }

    @Override
    public long heuristicCost() {
        return distanceFromIndexHeuristic();
    }

    @Override
    public boolean isGoal() {
        for (int i = 0; i < board.length - 1; ++i) {
            if (board[i] != i + 1) {
                return false;
            }
        }
        return true;
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

    private int distanceFromIndexHeuristic() {
        int score = 0;
        for (int i = 0; i < board.length - 1; i++) {
            score += distanceTo(board[i] + 1, i);
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

    public List<? extends Action<? extends Puzzle>> getActions() {
        List<MoveSquare> actions = new ArrayList<>();
        int modulo3 = blankIndex % 3;
        if (blankIndex + 3 <= 8) actions.add(new MoveSquare(MoveSquare.Direction.DOWN, blankIndex));
        if (blankIndex - 3 >= 0) actions.add(new MoveSquare(MoveSquare.Direction.UP, blankIndex));
        if (modulo3 != 2) actions.add(new MoveSquare(MoveSquare.Direction.RIGHT, blankIndex));
        if (modulo3 != 0) actions.add(new MoveSquare(MoveSquare.Direction.LEFT, blankIndex));
        return actions;
    }

}
