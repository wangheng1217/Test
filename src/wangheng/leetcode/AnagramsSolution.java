package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

// sort the str first from a to z
public class AnagramsSolution {

    public ArrayList<String> anagrams2(String[] strs) {
        ArrayList<String> res = new ArrayList<String>();

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < strs.length; i++) {
            String formattedStr = formatString(strs[i]);
            Integer count = map.get(formattedStr);
            if (count == null) {
                map.put(formattedStr, i);
            } else if (count >= 0) {
                map.put(formattedStr, -1);
                res.add(strs[i]);
                res.add(strs[count]);
            } else {
                res.add(strs[i]);
            }
        }

        return res;
    }

    public ArrayList<String> anagrams(String[] strs) {
        ArrayList<String> res = new ArrayList<String>();

        String[] formattedStrs = new String[strs.length];
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < strs.length; i++) {
            formattedStrs[i] = formatString(strs[i]);
            Integer count = map.get(formattedStrs[i]);
            if (count == null) {
                map.put(formattedStrs[i], 1);
            } else {
                map.put(formattedStrs[i], count + 1);
            }
        }

        for (int i = 0; i < formattedStrs.length; i++) {
            Integer count = map.get(formattedStrs[i]);
            if (count != null && count > 1) {
                res.add(strs[i]);
            }
        }

        return res;
    }

    private String formatString(String s) {
        char[] ss = s.toCharArray();
        Arrays.sort(ss);
        String formattedStr = new String(ss);
        return formattedStr;
    }

}
