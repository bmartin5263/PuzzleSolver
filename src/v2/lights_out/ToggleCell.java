package v2.lights_out;

import v2.framework.Action;

public class ToggleCell implements Action<LightsOut> {
    private final int row;
    private final int col;

    public ToggleCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public LightsOut execute(LightsOut inState) {
        boolean[][] newBoard = inState.copyBoard();
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
    public int cost() {
        return 1;
    }

}
