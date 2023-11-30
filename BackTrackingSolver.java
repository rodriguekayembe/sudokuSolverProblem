import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *  Write a backtracking algorithm to solve a sudoku board.
 *  I.e. Fill in an empty cell with the first allowed value (i.e. 1) and if there is no violation of constraints, move
 *  onto the next cell. If there is a violation, increment the value used. Once the value reaches 9 and there is a violation
 *  backtrack to the previous cell.
 *
 */
public class BackTrackingSolver implements SudokuSolver {

    @Override
    public void solve(int[][] board) {

    }


    /**
     * A valid solution is one where every cell has a number between 1-9
     * Rows has the number 1 to 9 once
     * Column has the number 1 to 9 once
     * No block of 3*3 has a duplicate and has all the numbers 1 to 9
     *
     * @param board
     * @return boolean
     */
    @Override
    public boolean isValidSolution(int[][] board) {
        return false;
    }


    public boolean isValidRow(int[] row) {
        return Arrays.stream(row).distinct().filter(i -> i > 0 && i <= 9).sum() == 45;
    }

    public int[][][] getBlocks(int[][] board) {
        int[][][] blocks = new int[9][3][3];

        for (int block = 0; block < 9; block++) {
            int rowOffset = (block / 3) * 3;
            int colOffset = (block % 3) * 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    blocks[block][i][j] = board[rowOffset + i][colOffset + j];
                }
            }
        }

        return blocks;
    }

    public boolean isAvailable(int[][] board, int[] location) {
        return board[location[0]][location[1]] == 0;
    }

    public boolean validateEntry(int[][] board, int[] cell, int input) {
        final int[] row = board[cell[0]];
        final int[] column = getColumn(board, cell[1]);
        final int blockIndex = cell[0] / 3 * 3 + cell[1] / 3;
        final boolean blockContainsNumber = blockContainsNumber(getBlocks(board), blockIndex, input);

        final boolean noneMatchRow = Arrays.stream(row).noneMatch(x -> x == input);
        final boolean noneMatchColumn= Arrays.stream(column).noneMatch(x -> x == input);

        return isAvailable(board, cell) && noneMatchRow && noneMatchColumn && !blockContainsNumber;
    }

    public int[] getColumn(int[][] board, int columnNumber) {
        int[] result = new int[9];
        for (int i = 0; i < board.length; i++) {
                result[i] = board[i][columnNumber];
            }
        return result;
    }


    private boolean blockContainsNumber(int[][][] blocks, int blockIndex, int number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (blocks[blockIndex][i][j] == number) {
                    return true; // Number exists in the block
                }
            }
        }

        return false; // Number doesn't exist in the block
    }
}
