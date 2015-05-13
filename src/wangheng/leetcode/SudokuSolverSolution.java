package wangheng.leetcode;

public class SudokuSolverSolution {

    public static void main(String[] args) {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = '.';
            }
        }
        new SudokuSolverSolution().solveSudoku(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
    }

    private boolean solveSudoku(char[][] board, int row, int col) {
        boolean found = false;
        int x = 0, y = 0;
        for (int i = row; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == row && j < col)
                    j = col;
                if (board[i][j] == '.') {
                    found = true;
                    x = i;
                    y = j;
                    break;
                }
            }
            if (found)
                break;
        }
        if (!found)
            return true;

        for (int i = 0; i < 9; i++) {
            board[x][y] = (char) ((int) '1' + i);
            //boolean valid = isValidSudoku(board);
            boolean valid = isValidSudoku(board, x, y);
            if (!valid)
                continue;
            else {
                boolean b = solveSudoku(board, x, y);
                if (b == true)
                    return true;
            }
        }
        board[x][y] = '.';
        return false;
    }
    
    private boolean isValidSudoku(char[][] board, int x, int y) {
        boolean isValid = isValid(board[x]);
        if(!isValid) return false;
        
        char[] column = new char[9];
        for(int i = 0; i < 9; i++) {
            column[i] = board[i][y];
        }
        isValid = isValid(column);
        if(!isValid) return false;
        
        char[] block = new char[9];
        int blockX = (x/3)*3;
        int blockY = (y/3)*3;
        for(int i = 0; i < 9; i++) {
            block[i] = board[blockX + i/3][blockY + i%3];
        }
        isValid = isValid(block);
        if(!isValid) return false;
        
        return true;
    }

    private boolean isValidSudoku(char[][] board) {
        if (board.length != 9)
            return false;
        if (board[0].length != 9)
            return false;

        for (int i = 0; i < 9; i++) {
            boolean isValid = isValid(board[i]);
            if (!isValid)
                return false;

            char[] column = new char[9];
            for (int j = 0; j < 9; j++) {
                column[j] = board[j][i];
            }
            isValid = isValid(column);
            if (!isValid)
                return false;

            char[] block = new char[9];
            for (int j = 0; j < 9; j++) {
                block[j] = board[(i / 3) * 3 + j / 3][(i % 3) * 3 + j % 3];
            }
            isValid = isValid(block);
            if (!isValid)
                return false;
        }

        return true;
    }

    private boolean isValid(char[] row) {
        java.util.HashMap<Character, Integer> map = new java.util.HashMap<Character, Integer>();
        for (int i = 0; i < row.length; i++) {
            char c = row[i];
            if (c >= '1' && c <= '9') {
                Integer inte = map.get(c);
                if (inte == null) {
                    map.put(c, 1);
                } else
                    return false;
            }
        }
        return true;
    }

}
