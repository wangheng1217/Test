package wangheng.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegionsSolution {
    
    public void solve3(char[][] board) {
        if (board.length == 0) return;
        
        int m = board.length;
        int n = board[0].length;
        
        Queue<Node> queue = new LinkedList<Node>();
        boolean[][] visited = new boolean[m][n];
        for (int j = 0; j < n; j++) {
            visit(0, j, queue, visited, board);
            visit(m-1, j, queue, visited, board);
        }
        for (int i = 0; i < m; i++) {
            visit(i, 0, queue, visited, board);
            visit(i, n-1, queue, visited, board);
        }
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            visit(x, y-1, queue, visited, board);
            visit(x, y+1, queue, visited, board);
            visit(x-1, y, queue, visited, board);
            visit(x+1, y, queue, visited, board);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void visit(int x, int y, Queue<Node> queue, boolean[][] visited, char[][] board) {
        if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] == 'O' && !visited[x][y]) {
            queue.add(new Node(x, y));
            visited[x][y] = true;
        }
    }
    
    private class Node {
        int x;
        int y;
        Node (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    //BFS, but performance seems to be worse
    public void solve2(char[][] board) {
        if (board.length == 0)
            return;

        boolean[][] visited = new boolean[board.length][board[0].length];

        java.util.Queue<Node> queue = new java.util.LinkedList<Node>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == board.length - 1 || j == 0
                            || j == board[0].length - 1) {
                        visited[i][j] = true;
                        queue.add(new Node(i, j));
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x;
            int y = node.y;
            if (x - 1 > 0 && x - 1 < board.length && board[x - 1][y] == 'O'
                    && visited[x - 1][y] == false) {
                visited[x - 1][y] = true;
                queue.add(new Node(x - 1, y));
            }
            if (x + 1 > 0 && x + 1 < board.length && board[x + 1][y] == 'O'
                    && visited[x + 1][y] == false) {
                visited[x + 1][y] = true;
                queue.add(new Node(x + 1, y));
            }
            if (y - 1 > 0 && y - 1 < board.length && board[x][y - 1] == 'O'
                    && visited[x][y - 1] == false) {
                visited[x][y - 1] = true;
                queue.add(new Node(x, y - 1));
            }
            if (y + 1 > 0 && y + 1 < board.length && board[x][y + 1] == 'O'
                    && visited[x][y + 1] == false) {
                visited[x][y + 1] = true;
                queue.add(new Node(x, y + 1));
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (visited[i][j] == false) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void solve(char[][] board) {
        if (board.length == 0)
            return;

        int[][] map = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X')
                    map[i][j] = 1;
            }
        }

        while (true) {
            boolean changed = false;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (map[i][j] == 0) {
                        if (i == 0 || i == board.length - 1 || j == 0
                                || j == board[0].length - 1) {
                            map[i][j] = 2;
                            changed = true;
                        } else if (map[i - 1][j] == 2 || map[i + 1][j] == 2
                                || map[i][j - 1] == 2 || map[i][j + 1] == 2) {
                            map[i][j] = 2;
                            changed = true;
                        }
                    }
                }
            }
            if (!changed)
                break;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (map[i][j] == 0) {
                    board[i][j] = 'X';
                }
            }
        }
    }

}

class Node {
    int x;
    int y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
