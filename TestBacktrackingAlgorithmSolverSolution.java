import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestBacktrackingAlgorithmSolverSolution {

    public static final int[][] BOARD = Main.board;
    BackTrackingSolver solver = new BackTrackingSolver();

    @Test
    void testValidSolution() {
        // Given
        assertFalse(solver.isValidSolution(BOARD));
    }


    @Test
    void testValidRowOrColumn() {
        int[] validRow = { 8, 1, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowDuplicateValue = { 8, 9, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowWithInvalidValue = { 8, 13, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowWithZeroValue = { 8, 0, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowWithFittingDuplicateValue = { 8, 1, 1, 4, 4, 5, 6, 7, 9 };
        int[] invalidRowWithNumbersAboveLimitFitting = { 8, 1, 2, 3, 4, 5, 6, 0, 16 };

        assertTrue(solver.isValidRow(validRow));
        assertFalse(solver.isValidRow(invalidRowDuplicateValue));
        assertFalse(solver.isValidRow(invalidRowWithInvalidValue));
        assertFalse(solver.isValidRow(invalidRowWithZeroValue));
        assertFalse(solver.isValidRow(invalidRowWithFittingDuplicateValue));
        assertFalse(solver.isValidRow(invalidRowWithNumbersAboveLimitFitting));
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
        final int[] unavailableCell = {0, 0};
        for (int input = 0; input < 9; input++) {
            assertFalse(solver.validateEntry(BOARD, unavailableCell, input));
        }

        final int[] availableCell = {0, 1};
        assertTrue(solver.validateEntry(BOARD, availableCell, 1));
        assertTrue(solver.validateEntry(BOARD, availableCell, 2));
        assertFalse(solver.validateEntry(BOARD, availableCell, 3));
        assertTrue(solver.validateEntry(BOARD, availableCell, 4));
        assertFalse(solver.validateEntry(BOARD, availableCell, 5));
        assertTrue(solver.validateEntry(BOARD, availableCell, 6));
        assertFalse(solver.validateEntry(BOARD, availableCell, 7));
        assertFalse(solver.validateEntry(BOARD, availableCell, 8));
        assertFalse(solver.validateEntry(BOARD, availableCell, 9));
    }
}