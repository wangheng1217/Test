package wangheng.leetcode;

// TODO find out the solution using iteration, DP
public class WildcardMatchingSolution {
    
    public static void main(String[] args) {
        WildcardMatchingSolution solution = new WildcardMatchingSolution();
        String s = "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba";
        String p = "a*******b";
        System.out.println(solution.isMatch(s, p));
        System.out.println(solution.count1 + " " + solution.count2);
    }
    
    /*
     * 0 - not calculated
     * 1 - match
     * 2 - not match
     */
    int[][] map;
    
    int count1 = 0;
    int count2 = 0;

    public boolean isMatch(String s, String p) {
        map = new int[s.length()+1][p.length()+1];
        return isMatch(s, 0, p, 0);
    }

    private boolean isMatch(String s, int sBeginIndex, String p, int pBeginIndex) {
        count1++;
        
        int flag = map[sBeginIndex][pBeginIndex];
        if (flag == 1) return true;
        else if(flag == 2) return false;
        
        count2++;
        
        if (sBeginIndex >= s.length()) {
            if (pBeginIndex >= p.length()) {
                map[sBeginIndex][pBeginIndex] = 1;
                return true;
            } else {
                for (int i = pBeginIndex; i < p.length(); i++) {
                    if (p.charAt(i) != '*') {
                        map[sBeginIndex][pBeginIndex] = 2;
                        return false;
                    }
                }
                map[sBeginIndex][pBeginIndex] = 1;
                return true;
            }
        } else {
            if (pBeginIndex >= p.length()) {
                map[sBeginIndex][pBeginIndex] = 2;
                return false;
            } else {
                char pC = p.charAt(pBeginIndex);
                if (pC == '?') {
                    boolean match = isMatch(s, sBeginIndex + 1, p, pBeginIndex + 1);
                    map[sBeginIndex][pBeginIndex] = match ? 1 : 2;
                    return match;
                } else if (pC == '*') {
                    int lastStraightStar = pBeginIndex;
                    for (int i = pBeginIndex+1; i < p.length() && p.charAt(i) == '*'; i++) {
                        lastStraightStar = i;
                    }
                    
                    boolean subMatch = isMatch(s, sBeginIndex, p,
                            lastStraightStar + 1);
                    if (subMatch) {
                        map[sBeginIndex][pBeginIndex] = 1;
                        return true;
                    } else {
                        boolean match = isMatch(s, sBeginIndex + 1, p, lastStraightStar);
                        map[sBeginIndex][pBeginIndex] = match ? 1 : 2;
                        return match;
                    }
                } else {
                    char sC = s.charAt(sBeginIndex);
                    if (sC == pC) {
                        boolean match = isMatch(s, sBeginIndex + 1, p, pBeginIndex + 1);
                        map[sBeginIndex][pBeginIndex] = match ? 1 : 2;
                        return match;
                    } else {
                        map[sBeginIndex][pBeginIndex] = 2;
                        return false;
                    }
                }
            }
        }
    }

}
