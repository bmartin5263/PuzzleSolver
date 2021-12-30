package v2.lights_out;

import v2.framework.Action;
import v2.framework.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightsOut implements Puzzle {

    private final int[][] board;

    public LightsOut(int n) {
        this.board = new int[n][n];
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                this.board[i][j] = 1;
            }
        }
    }

//    public LightsOut(int[][] arr) {
//        this.board = new int[arr.length][arr[0].length];
//        for (int i = 0; i < board.length; ++i) {
//            for (int j = 0; j < board[0].length; ++j) {
//                this.board[i][j] = arr[i][j];
//            }
//        }
//    }

    public LightsOut(int[][] newBoard) {
        for (int[] row : newBoard) {
            for (int v : row) {
                validate(v);
            }
        }
        this.board = newBoard;
    }

    public int[][] copyBoard() {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[0].length);
        }
        return newBoard;
    }

    private boolean isUnchanged(int v) {
        validate(v);
        return v == ToggleCell.OFF_UNCHANGED || v == ToggleCell.ON_UNCHANGED;
    }

    private boolean isOn(int v) {
        validate(v);
        return v == ToggleCell.ON_UNCHANGED || v == ToggleCell.ON_CHANGED;
    }

    private void validate(int v) {
        if (v == ToggleCell.OFF_UNCHANGED || v == ToggleCell.ON_UNCHANGED || v == ToggleCell.OFF_CHANGED || v == ToggleCell.ON_CHANGED) {
            return;
        }
        System.err.println(v);
        throw new IllegalArgumentException();
    }

    @Override
    public List<? extends Action<? extends Puzzle>> getActions() {
        List<ToggleCell> actions = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                int v = board[i][j];
                // Only add lights that haven't been changed
                if (isUnchanged(v)) {
                    actions.add(new ToggleCell(i, j));
                }
            }
        }
        return actions;
    }

    @Override
    public long heuristicCost() {
        // Calculate number of lights still on
        long lightsRemaining = 0;
        for (int[] lights : board) {
            for (int j = 0; j < board[0].length; ++j) {
                int v = lights[j];
                if (isOn(v)) {
                    lightsRemaining++;
                }
            }
        }
        return lightsRemaining;
    }

    @Override
    public boolean isGoal() {
        for (int[] lights : board) {
            for (int j = 0; j < board[0].length; ++j) {
                int v = lights[j];
                if (isOn(v)) {
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
        return Arrays.deepHashCode(board);
    }
}
