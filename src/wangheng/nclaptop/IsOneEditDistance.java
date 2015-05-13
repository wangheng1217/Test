package wangheng.nclaptop;

public class IsOneEditDistance {
    public boolean isOneEditDistance(String s1, String s2) {
        if (Math.abs(s1.length() - s2.length()) > 1)
            return false;
        if (s1.length() == s2.length()) {
            int count = 0;
            for (int p = 0; p < s1.length(); p++) {
                if (s1.charAt(p) != s2.charAt(p)) {
                    if (count == 1)
                        return false;
                    else
                        count++;
                }
            }

            return true;
        } else {
            if (s1.length() > s2.length())
                return isOneEditDistance(s2, s1);
            int p1 = 0, p2 = 0;
            int count = 0;
            while (p1 < s1.length()) {
                if (s1.charAt(p1) == s2.charAt(p2)) {
                    p1++;
                    p2++;
                } else {
                    if (count == 1)
                        return false;
                    else {
                        count++;
                        p2++;
                    }
                }
            }

            return true;
        }
    }

    public static void main(String[] args) {
        new IsOneEditDistance().test();
    }

    public void test() {
        assertTrue(isOneEditDistance("", ""));
        assertTrue(isOneEditDistance("", "a"));
        assertTrue(isOneEditDistance("a", ""));
        assertFalse(isOneEditDistance("", "aa"));
        assertFalse(isOneEditDistance("aa", ""));
        assertTrue(isOneEditDistance("a", "a"));
        assertTrue(isOneEditDistance("ab", "ab"));
        assertTrue(isOneEditDistance("abc", "abc"));
        assertTrue(isOneEditDistance("aa", "aa"));
        assertTrue(isOneEditDistance("aaa", "aaa"));
        assertTrue(isOneEditDistance("a", "aa"));
        assertTrue(isOneEditDistance("aa", "a"));
        assertTrue(isOneEditDistance("a", "ab"));
        assertTrue(isOneEditDistance("ab", "a"));
        assertFalse(isOneEditDistance("a", "abc"));
        assertFalse(isOneEditDistance("a", "bac"));
        assertTrue(isOneEditDistance("ab", "abc"));
        assertTrue(isOneEditDistance("ab", "acb"));
        assertTrue(isOneEditDistance("ab", "cab"));
        assertTrue(isOneEditDistance("ab", "aab"));
        assertFalse(isOneEditDistance("ab", "ba"));
        assertFalse(isOneEditDistance("ab", "cba"));
        assertTrue(isOneEditDistance("ab", "bab"));
        assertTrue(isOneEditDistance("abcdefghijklmn", "abcdefghijklmn"));
        assertTrue(isOneEditDistance("abcdefghijklmn", "0abcdefghijklmn"));
        assertTrue(isOneEditDistance("abcdefghijklmn", "abcdefghijklmn0"));
        assertTrue(isOneEditDistance("abcdefghijklmn", "abcdefgh0ijklmn"));
        assertTrue(isOneEditDistance("abcdefghijklmn", "aabcdefghijklmn"));
        assertTrue(isOneEditDistance("abcdefghijklmn", "abcdefghijklmnn"));
        assertTrue(isOneEditDistance("abcdefghijklmn", "abcdefghiijklmn"));
        assertTrue(isOneEditDistance("aaaaaaaaaaaaaa", "aaaaaaaaaaaaaa"));
        assertTrue(isOneEditDistance("aaaaaaaaaaaaaa", "aaaaaaaaaaaaaaa"));
        assertTrue(isOneEditDistance("aaaaaaaaaaaaaa", "aaaaaaaaaaaaaab"));
        assertTrue(isOneEditDistance("aaaaaaaaaaaaab", "aaaaaaaaaaaaaab"));
        assertFalse(isOneEditDistance("aaaaaaaaaaaaab", "aaaaaaaaaaaaaac"));        
    }

    private void assertFalse(boolean b) {
        if (b == false) System.out.println("passed");
        else System.out.println("failed");
    }

    private void assertTrue(boolean b) {
        if (b == true) System.out.println("passed");
        else System.out.println("failed");
    }
}
