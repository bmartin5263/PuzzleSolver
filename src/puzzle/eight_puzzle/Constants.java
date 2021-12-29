package puzzle.eight_puzzle;

public class Constants {

    public static final EightPuzzle GOAL;
    public static final EightPuzzle EASY;
    public static final EightPuzzle VERY_EASY;
    public static final EightPuzzle MEDIUM;
    public static final EightPuzzle HARD;
    public static final EightPuzzle EX;

    static {
        GOAL = new EightPuzzle(new int[] {1,2,3,
                                          8,0,4,
                                          7,6,5});

        VERY_EASY = new EightPuzzle(new int[] {1,2,3,
                                               8,4,0,
                                               7,6,5});

        EASY = new EightPuzzle(new int[] {1,3,4,
                                          8,6,2,
                                          7,0,5});

        MEDIUM = new EightPuzzle(new int[] {2,8,1,
                                            0,4,3,
                                            7,6,5});

        HARD = new EightPuzzle(new int[] {5,6,7,
                                          4,0,8,
                                          3,2,1});

        EX = new EightPuzzle(new int[] {5,4,0,
                                        6,1,8,
                                        7,3,2});

    }

}
