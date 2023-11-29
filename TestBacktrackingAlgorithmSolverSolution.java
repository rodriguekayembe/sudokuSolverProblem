import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TestBacktrackingAlgorithmSolverSolution {

    public static final int[][] BOARD = Main.board;
    BackTrackingSolver solver = new BackTrackingSolver();

    @Test
    void testValidSolution() {
        // Given
        Assert.assertFalse(solver.isValidSolution(BOARD));
    }


    @Test
    void testValidRow() {
        int[] validRow = { 8, 1, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowDuplicateValue = { 8, 9, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowWithInvalidValue = { 8, 13, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowWithZeroValue = { 8, 0, 2, 3, 4, 5, 6, 7, 9 };
        int[] invalidRowWithFittingDuplicateValue = { 8, 1, 1, 4, 4, 5, 6, 7, 9 };
        int[] invalidRowWithNumbersAboveLimitFitting = { 8, 1, 2, 3, 4, 5, 6, 0, 16 };

        Assert.assertTrue(solver.isValidRow(validRow));
        Assert.assertFalse(solver.isValidRow(invalidRowDuplicateValue));
        Assert.assertFalse(solver.isValidRow(invalidRowWithInvalidValue));
        Assert.assertFalse(solver.isValidRow(invalidRowWithZeroValue));
        Assert.assertFalse(solver.isValidRow(invalidRowWithFittingDuplicateValue));
        Assert.assertFalse(solver.isValidRow(invalidRowWithNumbersAboveLimitFitting));
    }
}