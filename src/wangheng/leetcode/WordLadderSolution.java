package wangheng.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//graph BFS (for optimal solution)
public class WordLadderSolution {
    public int ladderLength5(String start, String end, HashSet<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        dict.remove(start);
        int length = 1;

        while(!queue.isEmpty()) {
            int count = queue.size();
            for (int i = 0; i<count; i++){
                String current = queue.poll();
                for (char c = 'a'; c <= 'z'; c++) {
                    for (int j=0; j < current.length(); j++) {
                        if (c == current.charAt(j)) {
                            continue;
                        }
                        String tmp = replace(current, j, c);
                        if (tmp.equals(end)) {
                            return length + 1;
                        }
                        if (dict.contains(tmp)){
                            queue.offer(tmp);
                            dict.remove(tmp);
                        }
                    }
                }
            }
            length++;
        }
        return 0;
    }

    private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }

    public int ladderLength(String start, String end, HashSet<String> dict) {
        if (start.equals(end))
            return 1;

        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();

        queue.add(start);
        visited.add(start);

        int curr = 1;
        int next = 0;
        int depth = 0;

        while (!queue.isEmpty()) {
            String s = queue.poll();
            curr--;

            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    char[] cArray = s.toCharArray();
                    cArray[i] = (char) ((int) 'a' + j);
                    String newS = new String(cArray);
                    if (newS.equals(end))
                        return depth + 2;
                    if (dict.contains(newS) && !visited.contains(newS)) {
                        queue.add(newS);
                        visited.add(newS);
                        next++;
                    }
                }
            }

            if (curr == 0) {
                curr = next;
                next = 0;
                depth++;
            }
        }

        return 0;
    }

    public int ladderLength4(String start, String end, HashSet<String> dict) {
        if (start.equals(end))
            return 1;

        // recording depth for each node
        Map<String, Integer> visitedMap = new HashMap<String, Integer>();
        Queue<String> queue = new LinkedList<String>();

        queue.add(start);
        visitedMap.put(start, 1);

        while (!queue.isEmpty()) {
            String s = queue.poll();
            int depth = visitedMap.get(s);

            if (isTransformable(s, end)) {
                return depth + 1;
            }

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                for (int j = 0; j < 26; j++) {
                    char newC = (char) ((int) 'a' + j);
                    if (newC != c) {
                        char[] cArray = s.toCharArray();
                        cArray[i] = newC;
                        String newS = new String(cArray);
                        if (dict.contains(newS) && visitedMap.get(newS) == null) {
                            queue.add(newS);
                            visitedMap.put(newS, depth + 1);
                        }
                    }
                }
            }

        }

        return 0;
    }

    public int ladderLength3(String start, String end, HashSet<String> dict) {
        if (start.equals(end))
            return 1;

        // recording depth for each node
        Map<String, Integer> visitedMap = new HashMap<String, Integer>();
        Queue<String> queue = new LinkedList<String>();

        queue.add(start);
        visitedMap.put(start, 1);

        while (!queue.isEmpty()) {
            String s = queue.poll();
            int depth = visitedMap.get(s);

            if (isTransformable(s, end)) {
                return depth + 1;
            }

            Iterator<String> ite = dict.iterator();
            while (ite.hasNext()) {
                String word = ite.next();
                if (isTransformable(s, word) && visitedMap.get(word) == null) {
                    queue.add(word);
                    visitedMap.put(word, depth + 1);
                }
            }
        }

        return 0;
    }

    public int ladderLength2(String start, String end, HashSet<String> dict) {
        if (start.equals(end))
            return 1;
        if (isTransformable(start, end))
            return 2;

        List<String> listA = new ArrayList<String>();
        List<String> listB = new ArrayList<String>();

        Iterator<String> ite = dict.iterator();
        while (ite.hasNext()) {
            String s = ite.next();
            boolean a = isTransformable(start, s);
            boolean b = isTransformable(end, s);
            if (a && b)
                return 3;
            if (a)
                listA.add(s);
            if (b)
                listB.add(s);
        }

        int minPath = 0;

        for (int i = 0; i < listA.size(); i++) {
            String a = listA.get(i);
            dict.remove(a);
            for (int j = 0; j < listB.size(); j++) {
                String b = listB.get(j);
                dict.remove(b);

                int subPath = ladderLength(a, b, dict);
                if (subPath > 0) {
                    int path = 2 + subPath;
                    if (minPath == 0) {
                        minPath = path;
                    } else {
                        minPath = min(minPath, path);
                    }
                }

                dict.add(b);
            }
            dict.add(a);
        }

        return minPath;
    }

    private boolean isTransformable(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

}
