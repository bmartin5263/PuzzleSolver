package v1.puzzle.lights_out;

import v1.puzzle.Action;
import v1.puzzle.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightsOut implements Puzzle<LightsOut> {

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

    @Override
    public List<Action> getActions() {
        List<Action> actions = new ArrayList<>();
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
    public LightsOut doAction(Action action) {
        boolean[][] newBoard = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; ++i) {
            System.arraycopy(board[i], 0, newBoard[i], 0, board[0].length);
        }

        ToggleCell toggleCell = (ToggleCell) action;
        int row = toggleCell.getRow();
        int col = toggleCell.getCol();

        newBoard[row][col] = !newBoard[row][col];
        if (col > 0) {
            newBoard[row][col - 1] = !newBoard[row][col - 1];
        }
        if (col < newBoard[0].length - 1) {
            newBoard[row][col + 1] = !newBoard[row][col + 1];
        }
        if (row > 0) {
            newBoard[row - 1][col] = !newBoard[row - 1][col];
        }
        if (row < newBoard.length - 1) {
            newBoard[row + 1][col] = !newBoard[row + 1][col];
        }
        return new LightsOut(newBoard);
    }

    @Override
    public Action getDefaultAction() {
        return new ToggleCell(-1, -1);
    }

    @Override
    public int getCostOfLastMove() {
        return 1;
    }

    public static LightsOut goalState(int n) {
        LightsOut lightsOut = new LightsOut(n);
        for (int i = 0; i < lightsOut.board.length; ++i) {
            Arrays.fill(lightsOut.board[i], false);
        }
        return lightsOut;
    }

    public int numberOfWrongCells(LightsOut goal) {
        int score = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] != goal.board[i][j]) score++;
            }
        }
        return score;
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
