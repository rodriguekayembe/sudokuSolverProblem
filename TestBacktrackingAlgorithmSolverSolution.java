import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBacktrackingAlgorithmSolverSolution {

    public int[][] BOARD;
    BackTrackingSolver solver = new BackTrackingSolver();

    @BeforeEach
    void setUp() {
        BOARD = new int[][]{
                {8, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 6, 0, 0, 0, 0, 0},
                {0, 7, 0, 0, 9, 0, 2, 0, 0},
                {0, 5, 0, 0, 0, 7, 0, 0, 0},
                {0, 0, 0, 0, 4, 5, 7, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 3, 0},
                {0, 0, 1, 0, 0, 0, 0, 6, 8},
                {0, 0, 8, 5, 0, 0, 0, 1, 0},
                {0, 9, 0, 0, 0, 0, 4, 0, 0}
        };
    }

    @Test
    void testValidSolution() {
        // Given
        assertFalse(solver.isValidSolution(BOARD));
        assertTrue(solver.isValidSolution(solver.solve(BOARD)));
    }


    /**
     * 9 blocks of 3 * 3
     */
    @Test
    void testGetBlock() {
        final int[][][] expected =
                {{{8, 0, 0},{0, 0, 3}, {0, 7, 0}}, {{0, 0, 0}, {6, 0, 0}, {0, 9, 0}},
                {{0, 0, 0}, {0, 0, 0},  {2, 0, 0}}, {{0, 5, 0}, {0, 0, 0}, {0,  0, 0}},
                        {{0, 0, 7}, {0, 4, 5}, {1, 0, 0}}, {{0, 0, 0}, {7, 0, 0}, {0, 3, 0}},
                        {{0, 0, 1}, {0, 0, 8}, {0, 9, 0}}, {{0, 0, 0},  {5, 0, 0}, {0, 0, 0 }}, {{0, 6, 8}, {0, 1, 0}, {4, 0, 0}}};

        assertArrayEquals(expected, solver.getBlocks(BOARD));
    }

    @Test
    void testAvailable() {
        int[] invalidLocation = new int[]{0, 0};
        int[] validLocation = new int[]{0, 1};
        assertFalse(solver.isAvailable(BOARD, invalidLocation));
        assertTrue(solver.isAvailable(BOARD, validLocation));
    }


    @Test
    void getColumn() {
        assertArrayEquals(solver.getColumn(BOARD, 2), new int[]{0, 3, 0, 0, 0, 0, 1, 8, 0});
    }

    @Test
    void isValidEntry() {
        assertTrue(solver.isValidEntry(BOARD, 0,1, 1));
        assertTrue(solver.isValidEntry(BOARD, 0,1, 2));
        assertFalse(solver.isValidEntry(BOARD, 0,1, 3));
        assertTrue(solver.isValidEntry(BOARD, 0,1, 4));
        assertFalse(solver.isValidEntry(BOARD, 0,1, 5));
        assertTrue(solver.isValidEntry(BOARD, 0,1, 6));
        assertFalse(solver.isValidEntry(BOARD, 0,1, 7));
        assertFalse(solver.isValidEntry(BOARD, 0,1, 8));
        assertFalse(solver.isValidEntry(BOARD, 0,1, 9));
    }

    @Test
    void testSolve() {
        int[][] resolved = {{8, 1, 2, 7, 5, 3, 6, 4, 9},
            {9, 4, 3, 6, 8, 2, 1, 7, 5},
            {6, 7, 5, 4, 9, 1, 2, 8, 3},
            {1, 5, 4, 2, 3, 7, 8, 9, 6},
            {3, 6, 9, 8, 4, 5, 7, 2, 1},
            {2, 8, 7, 1, 6, 9, 5, 3, 4},
            {5, 2, 1, 9, 7, 4, 3, 6, 8},
            {4, 3, 8, 5, 2, 6, 9, 1, 7},
            {7, 9, 6, 3, 1, 8, 4, 5, 2}};

        assertArrayEquals(resolved, solver.solve(BOARD));
    }
}