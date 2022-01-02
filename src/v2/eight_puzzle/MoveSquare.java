package v2.eight_puzzle;

import v2.framework.Action;

public class MoveSquare implements Action<EightPuzzle> {

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private final int newBlank;
    private final Direction direction;

    public MoveSquare(Direction direction, int currentBlankIndex) {
        this.direction = direction;
        switch (direction) {
            case UP:
                newBlank = currentBlankIndex - 3;
                break;
            case DOWN:
                newBlank = currentBlankIndex + 3;
                break;
            case RIGHT:
                newBlank = currentBlankIndex + 1;
                break;
            case LEFT:
                newBlank = currentBlankIndex - 1;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public EightPuzzle execute(EightPuzzle inState) {
        int[] newBoard = inState.copyBoard();
        EightPuzzle.swap(newBoard, inState.blankIndex(), newBlank);
        return new EightPuzzle(newBoard, newBlank);
    }

    @Override
    public long cost(EightPuzzle inState) {
        return inState.board[newBlank];
    }

    @Override
    public String toString() {
        return "MoveSquare(" +
                direction +
                ')';
    }
}
