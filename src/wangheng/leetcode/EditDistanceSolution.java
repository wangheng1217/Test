package wangheng.leetcode;

// DP
public class EditDistanceSolution {

    public int minDistance2(String word1, String word2) {
        if (word1.length() > word2.length())
            return minDistance2(word2, word1);

        int[] line1 = new int[word1.length() + 1];
        int[] line2 = new int[word1.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            line1[i] = i;
        }

        for (int j = 1; j <= word2.length(); j++) {
            line2[0] = j;
            for (int i = 1; i <= word1.length(); i++) {
                int d1 = line1[i] + 1;
                int d2 = line2[i - 1] + 1;
                int d3 = line1[i - 1]
                        + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                line2[i] = min(d1, d2, d3);
            }

            for (int k = 0; k < line1.length; k++) {
                line1[k] = line2[k];
            }
        }

        return line1[word1.length()];
    }

    public int minDistance(String word1, String word2) {
        int[][] map = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            map[i][0] = i;
        }

        for (int j = 0; j <= word2.length(); j++) {
            map[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                int d1 = map[i - 1][j] + 1;
                int d2 = map[i][j - 1] + 1;
                int d3 = map[i - 1][j - 1]
                        + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                map[i][j] = min(d1, d2, d3);
            }
        }
        return map[word1.length()][word2.length()];
    }

    private int min(int a, int b, int c) {
        return (a < b) ? (a < c ? a : c) : (b < c ? b : c);
    }

}
