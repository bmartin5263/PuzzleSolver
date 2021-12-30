package v2.lights_out;

import v2.framework.Action;

import java.util.HashMap;
import java.util.Map;

public class ToggleCell implements Action<LightsOut> {

    public static final int OFF_UNCHANGED = 0b00;
    public static final int OFF_CHANGED = 0b10;
    public static final int ON_UNCHANGED = 0b01;
    public static final int ON_CHANGED = 0b11;

    private static final Map<Integer, Integer> TOGGLES = new HashMap<>();
    static {
        TOGGLES.put(OFF_UNCHANGED, ON_CHANGED);
        TOGGLES.put(ON_UNCHANGED, OFF_CHANGED);
    }

    private static final Map<Integer, Integer> TOGGLES2 = new HashMap<>();
    static {
        TOGGLES2.put(OFF_UNCHANGED, ON_UNCHANGED);
        TOGGLES2.put(OFF_CHANGED, ON_CHANGED);
        TOGGLES2.put(ON_UNCHANGED, OFF_UNCHANGED);
        TOGGLES2.put(ON_CHANGED, OFF_CHANGED);
    }

    private final int row;
    private final int col;

    public ToggleCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public LightsOut execute(LightsOut inState) {
        int[][] newBoard = inState.copyBoard();
        newBoard[row][col] = TOGGLES.get(newBoard[row][col]);
        if (col > 0) {
            newBoard[row][col - 1] = TOGGLES2.get(newBoard[row][col - 1]);
        }
        if (col < newBoard[0].length - 1) {
            newBoard[row][col + 1] = TOGGLES2.get(newBoard[row][col + 1]);
        }
        if (row > 0) {
            newBoard[row - 1][col] = TOGGLES2.get(newBoard[row - 1][col]);
        }
        if (row < newBoard.length - 1) {
            newBoard[row + 1][col] = TOGGLES2.get(newBoard[row + 1][col]);
        }
        return new LightsOut(newBoard);
    }

    @Override
    public int cost() {
        return 1;
    }

    @Override
    public String toString() {
        return "ToggleCell(" +
                "row=" + row +
                ", col=" + col +
                ')';
    }
}
