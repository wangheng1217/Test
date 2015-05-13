package wangheng.leetcode;

//TODO read KMP algorithm
public class ImplementStrStrSolution {

    public String strStr(String haystack, String needle) {
        if (needle.length() == 0)
            return haystack;
        for (int i = 0; i < haystack.length()
                && i + needle.length() - 1 < haystack.length(); i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j))
                    break;
                else {
                    if (j == needle.length() - 1) {
                        return haystack.substring(i, haystack.length());
                    }
                }
            }
        }
        return null;
    }

}
