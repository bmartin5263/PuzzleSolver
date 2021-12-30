package v2.lights_out;

import v2.framework.Action;
import v2.framework.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightsOut implements Puzzle {

    private final boolean[][] board;

    public LightsOut(int n) {
        this.board = new boolean[n][n];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                this.board[i][j] = true;
            }
        }
    }

    public LightsOut(int[][] arr) {
        this.board = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                this.board[i][j] = arr[i][j] == 1;
            }
        }
    }

    public LightsOut(boolean[][] newBoard) {
        this.board = newBoard;
    }

    public boolean[][] copyBoard() {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[0].length);
        }
        return newBoard;
    }

    @Override
    public List<? extends Action<? extends Puzzle>> getActions() {
        List<ToggleCell> actions = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (this.board[i][j]) {
                    actions.add(new ToggleCell(i, j));
                }
            }
        }
        return actions;
    }

    @Override
    public int heuristicCost() {
        // Calculate number of lights still on
        int score = 0;
        for (boolean[] booleans : board) {
            for (int j = 0; j < board[0].length; ++j) {
                if (booleans[j]) score++;
            }
        }
        return score;
    }

    @Override
    public boolean isGoal() {
        for (boolean[] lights : board) {
            for (int j = 0; j < board[0].length; ++j) {
                if (lights[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LightsOut lightsOut = (LightsOut) o;
        return Arrays.deepEquals(board, lightsOut.board);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board);
    }
}
