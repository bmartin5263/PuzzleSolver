package v1.puzzle.lights_out;

import v1.puzzle.Action;

public class ToggleCell implements Action {
    private final int row;
    private final int col;

    public ToggleCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
