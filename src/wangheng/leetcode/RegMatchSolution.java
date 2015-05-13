package wangheng.leetcode;

/*
 * 1. use recursion; (backtracking)
 * 2. since there are duplicated calls during the recursion,
 *    we could use DP to store the previous result;
 * 3. see isMatch2 for pure DP solution
 *    
 * TODO : read the idea of DFA
 * A sample diagram of a deterministic finite state automata (DFA). 
 * DFAs are useful for doing lexical analysis and pattern matching. 
 * An example is UNIX’s grep tool. 
 * Please note that this post does not attempt to descibe a solution using DFA.
 */
public class RegMatchSolution {

    static int count1;
    static int count2;

    public static void main(String[] args) {
        System.out.println(new RegMatchSolution().isMatch("aaaaaaaaaaaaab",
                "a*a*a*a*a*a*a*a*a*a*c"));
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(count1);
        System.out.println(count2);
    }

    static int[][] map;

    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;

        // convert p to string array pattern
        java.util.List<String> patternList = new java.util.ArrayList<String>();
        int i = 0;
        while (i < p.length()) {
            if (i + 1 < p.length()) {
                char b = p.charAt(i + 1);
                if (b != '*') {
                    patternList.add(p.substring(i, i + 1));
                    i++;
                } else {
                    patternList.add(p.substring(i, i + 2));
                    i = i + 2;
                }
            } else {
                patternList.add(p.substring(i, i + 1));
                i++;
            }
        }
        
        String[] pattern = patternList.toArray(new String[patternList.size()]);

        char[] exp = s.toCharArray();

        map = new int[exp.length + 1][pattern.length + 1];

        return isMatch(exp, 0, pattern, 0);
    }

    private boolean isMatch(char[] exp, int expBeginIndex, String[] pattern,
            int pBeginIndex) {
        
        /*
        if (pPos == patterns.length) {
            return sPos == s.length();
        }
        
        if (sPos == s.length()) {
            for (int i = pPos; i < patterns.length; i++) {
                if (patterns[i].length() != 2) {
                    return false;
                }
            }
            return true;
        }
         */
        
        count1++;

        int value = map[expBeginIndex][pBeginIndex];
        // 0 : not calculated, 1 : match, 2 : not match;
        if (value == 1)
            return true;
        else if (value == 2)
            return false;

        count2++;

        if (expBeginIndex >= exp.length) {
            if (pBeginIndex >= pattern.length) {
                map[expBeginIndex][pBeginIndex] = 1;
                return true;
            } else {
                for (int i = pBeginIndex; i < pattern.length; i++) {
                    String p = pattern[i];
                    if (p.length() == 1) {
                        map[expBeginIndex][pBeginIndex] = 2;
                        return false;
                    }
                }
                map[expBeginIndex][pBeginIndex] = 1;
                return true;
            }
        } else {
            if (pBeginIndex >= pattern.length) {
                map[expBeginIndex][pBeginIndex] = 2;
                return false;
            } else {
                String p = pattern[pBeginIndex];
                if (p.length() == 1) {
                    if (p.charAt(0) == '.' || p.charAt(0) == exp[expBeginIndex]) {
                        boolean match = isMatch(exp, expBeginIndex + 1,
                                pattern, pBeginIndex + 1);
                        map[expBeginIndex][pBeginIndex] = match ? 1 : 2;
                        return match;
                    } else {
                        map[expBeginIndex][pBeginIndex] = 2;
                        return false;
                    }
                } else {
                    // p == '_*';

                    // could be 0 match
                    if (isMatch(exp, expBeginIndex, pattern, pBeginIndex + 1)) {
                        map[expBeginIndex][pBeginIndex] = 1;
                        return true;
                    }

                    for (int i = expBeginIndex; i < exp.length; i++) {
                        if (p.charAt(0) == '.' || p.charAt(0) == exp[i]) {
                            if (isMatch(exp, i + 1, pattern, pBeginIndex + 1)) {
                                map[expBeginIndex][pBeginIndex] = 1;
                                return true;
                            }
                        } else {
                            map[expBeginIndex][pBeginIndex] = 2;
                            return false;
                        }
                    }
                    map[expBeginIndex][pBeginIndex] = 2;
                    return false;
                }
            }
        }
    }

    // pure DP, take longer time than isMatch in best case (I think)
    public boolean isMatch2(String s, String p) {
        boolean[] lastRow = new boolean[s.length() + 1];
        boolean[] currRow = new boolean[s.length() + 1];
        boolean[] tmpRow;

        // init star[] and pattern
        int cnt = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*')
                cnt++;
        }
        boolean[] star = new boolean[p.length() - cnt];
        StringBuffer sb = new StringBuffer(p.length());
        int index = -1;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                star[index] = true;
            } else {
                sb.append(p.charAt(i));
                index++;
            }
        }
        p = sb.toString();

        // DP
        lastRow[0] = true;
        for (int i = 0; i < p.length(); i++) {
            // set currRow[0]
            if (star[i] && lastRow[0]) {
                currRow[0] = true;
            } else {
                currRow[0] = false;
            }

            for (int j = 0; j < s.length(); j++) {
                int rowIndex = j + 1;

                if (!star[i]) {
                    if (p.charAt(i) == '.') {
                        if (lastRow[rowIndex - 1]) {
                            currRow[rowIndex] = true;
                        } else {
                            currRow[rowIndex] = false;
                        }
                    } else {
                        if (p.charAt(i) == s.charAt(j) && lastRow[rowIndex - 1]) {
                            currRow[rowIndex] = true;
                        } else {
                            currRow[rowIndex] = false;
                        }
                    }
                } else {
                    if (lastRow[rowIndex]) {
                        currRow[rowIndex] = true;
                    } else if (lastRow[rowIndex - 1] || currRow[rowIndex - 1]) {
                        if (p.charAt(i) == '.' || p.charAt(i) == s.charAt(j)) {
                            currRow[rowIndex] = true;
                        } else {
                            currRow[rowIndex] = false;
                        }
                    } else {
                        currRow[rowIndex] = false;
                    }
                }
            }
            // swap lastRow and currRow
            tmpRow = lastRow;
            lastRow = currRow;
            currRow = tmpRow;
        }
        return lastRow[lastRow.length - 1];
    }

}
