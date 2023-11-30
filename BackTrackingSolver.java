import java.util.Arrays;

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
}
