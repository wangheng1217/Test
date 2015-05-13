package wangheng.leetcode;

public class ValidSudokuSolution {

    public boolean isValidSudoku(char[][] board) {
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
