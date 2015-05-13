package wangheng.leetcode;

public class LengthOfLastWordSolution {
    
    public int lengthOfLastWord3(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int length = 0;
        for(int i = s.length()-1; i >= 0; i--) {
            if(s.charAt(i) == ' ') {
                if(length != 0) {
                    return length;
                }
            }
            else {
                length++;
            }
        }
        return length;
    }

    public int lengthOfLastWord2(String s) {
        int start = -1;
        int end = -1;
        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                if (end == -1) continue;
                else {
                    start = i + 1;
                    break;
                }
            } else {
                if (end == -1) {
                    end = i;
                    start = i;
                } else {
                    start = i;
                }
            }
        }
        
        if (end == -1) return 0;
        else return end-start+1;
    }

    public int lengthOfLastWord(String s) {
        int start = 0;
        int end = -1;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                if (i == 0 || s.charAt(i - 1) == ' ') {
                    start = i;
                }
                end = i;
            }
        }

        return end - start + 1;
    }

}
