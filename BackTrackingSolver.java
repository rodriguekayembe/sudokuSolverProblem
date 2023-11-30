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
    public int[][] solve(int[][] board) {
        if (isValidSolution(board)) return board;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) { // Check for an empty cell
                    for (int k = 1; k <= 9; k++) {
                        if (isValidEntry(board, i, j, k)) {
                            board[i][j] = k; // Place a valid value

                            int[][] result = solve(board); // Recursive call

                            if (isValidSolution(result)) {
                                return result; // Return the solved board
                            } else {
                                board[i][j] = 0; // Backtrack if solution is invalid
                            }
                        }
                    }
                    return board; // Backtrack to the previous state
                }
            }
        }

        return board;
    }


    public boolean isValidSolution(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return false; // Ensure no cell contains a zero
                }
                // Check if the current cell value violates constraints
                if (!isValidEntry(board, i, j, board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidEntry(int[][] board, int row, int col, int value) {
        // Check row constraint
        for (int i = 0; i < board.length; i++) {
            if (i != col && board[row][i] == value) {
                return false;
            }
        }

        // Check column constraint
        for (int i = 0; i < board.length; i++) {
            if (i != row && board[i][col] == value) {
                return false;
            }
        }

        // Check 3x3 block constraint
        int blockStartRow = (row / 3) * 3;
        int blockStartCol = (col / 3) * 3;
        for (int i = blockStartRow; i < blockStartRow + 3; i++) {
            for (int j = blockStartCol; j < blockStartCol + 3; j++) {
                if (i != row && j != col && board[i][j] == value) {
                    return false;
                }
            }
        }

        return true; // Cell value satisfies all constraints
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


    public int[] getColumn(int[][] board, int columnNumber) {
        int[] result = new int[9];
        for (int i = 0; i < board.length; i++) {
                result[i] = board[i][columnNumber];
            }
        return result;
    }
}
