package wangheng.leetcode;

public class WordSearchSolution {
    public boolean exist2(char[][] board, String word) {
        if (word.length() == 0) return true;
        
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, i, j, 0, word, used)) return true;
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, int depth, String word, boolean[][] used) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;
        if (board[i][j] != word.charAt(depth)) return false;
        if (used[i][j]) return false;
        
        if (depth == word.length()-1) return true;
        
        used[i][j] = true;
        
        if (dfs(board, i-1, j, depth+1, word, used)) return true;
        if (dfs(board, i+1, j, depth+1, word, used)) return true;
        if (dfs(board, i, j-1, depth+1, word, used)) return true;
        if (dfs(board, i, j+1, depth+1, word, used)) return true;
        
        used[i][j] = false;
        
        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0)
            return "".equals(word) ? true : false;
        int[][] map = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean exist = exist(board, word, map, i, j, 0);
                if (exist)
                    return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String word, int[][] map,
            int rowIndex, int colIndex, int strIndex) {

        if (strIndex >= word.length())
            return true;

        if (rowIndex < 0 || rowIndex >= board.length || colIndex < 0
                || colIndex >= board[0].length)
            return false;

        if (map[rowIndex][colIndex] == 1)
            return false;

        char c = board[rowIndex][colIndex];

        if (c != word.charAt(strIndex))
            return false;

        map[rowIndex][colIndex] = 1;

        if (exist(board, word, map, rowIndex, colIndex - 1, strIndex + 1))
            return true;
        if (exist(board, word, map, rowIndex, colIndex + 1, strIndex + 1))
            return true;
        if (exist(board, word, map, rowIndex - 1, colIndex, strIndex + 1))
            return true;
        if (exist(board, word, map, rowIndex + 1, colIndex, strIndex + 1))
            return true;

        map[rowIndex][colIndex] = 0;
        return false;
    }

}
