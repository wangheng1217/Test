package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddressesSolution {
    public List<String> restoreIpAddresses2(String s) {
        List<String> result = new ArrayList<String>();
        List<String> list = new ArrayList<String>();
        dfs(s, result, list, 0);
        return result;
    }
    
    private void dfs(String s, List<String> result, List<String> list, int p) {
        if (list.size() == 4) {
            String ip = list.get(0) + "." + list.get(1) + "." + list.get(2) + "." + list.get(3);
            result.add(ip);
            return;
        }
        
        if (p >= s.length()) return;
        
        if (list.size() < 3) {
            list.add(s.substring(p, p+1));
            dfs(s, result, list, p+1);
            list.remove(list.size()-1);
            
            if (s.charAt(p) != '0' && p+1 < s.length()) {
                list.add(s.substring(p, p+2));
                dfs(s, result, list, p+2);
                list.remove(list.size()-1);
            }
            
            if (s.charAt(p) != '0' && p+2 < s.length() && Integer.valueOf(s.substring(p, p+3)) <= 255) {
                list.add(s.substring(p, p+3));
                dfs(s, result, list, p+3);
                list.remove(list.size()-1);
            }
        } else {
            // list.size() == 3
            // check substring length first to avoid overflow while parsing integer
            if ( (s.charAt(p) == '0' && p < s.length()-1) || s.length() - p > 3 || Integer.valueOf(s.substring(p, s.length())) > 255 ) return;
            
            list.add(s.substring(p, s.length()));
            dfs(s, result, list, s.length());
            list.remove(list.size()-1);
        }
    }

    public ArrayList<String> restoreIpAddresses(String s) {
        ArrayList<String> res = new ArrayList<String>();
        int[] pos = new int[3];
        for (int i = 1; i < s.length()
                && ((i == 1) || (i == 2 && s.charAt(0) != '0') || (i == 3 && Integer
                        .valueOf(s.substring(0, i)) <= 255)); i++) {
            pos[0] = i;
            for (int j = i + 1; j < s.length()
                    && ((j == i + 1) || (j == i + 2 && s.charAt(i) != '0') || (j == i + 3 && Integer
                            .valueOf(s.substring(i, j)) <= 255)); j++) {
                pos[1] = j;
                for (int k = j + 1; k < s.length()
                        && ((k == j + 1) || (k == j + 2 && s.charAt(j) != '0') || (k == j + 3 && Integer
                                .valueOf(s.substring(j, k)) <= 255)); k++) {
                    pos[2] = k;
                    if ((s.length() - k == 1)
                            || (s.length() - k == 2 && s.charAt(k) != '0')
                            || (s.length() - k == 3 && s.charAt(k) != '0' && Integer
                                    .valueOf(s.substring(k, s.length())) <= 255)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(s.substring(0, pos[0]));
                        sb.append(".");
                        sb.append(s.substring(pos[0], pos[1]));
                        sb.append(".");
                        sb.append(s.substring(pos[1], pos[2]));
                        sb.append(".");
                        sb.append(s.substring(pos[2], s.length()));
                        String ip = sb.toString();
                        res.add(ip);
                    }
                }
            }
        }
        return res;
    }

}
