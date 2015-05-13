package wangheng.leetcode;

import java.util.*;

public class WordLadderIISolution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        Map<String, List<List<String>>> map = new HashMap<String, List<List<String>>>();
        Queue<String> queue = new LinkedList<String>();
        
        List<List<String>> result = new ArrayList<List<String>>();
        boolean isFound = false;
        
        queue.add(start);
        List<String> list = new ArrayList<String>();
        list.add(start);
        List<List<String>> lists = new ArrayList<List<String>>();
        lists.add(list);
        map.put(start, lists);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String s = queue.poll();
                for (int j = 0; j < s.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != s.charAt(j)) {
                            String newS = replace(s, j, c);
                            if (newS.equals(end)) {
                                isFound = true;
                                lists = map.get(s);
                                for (List<String> theList : lists) {
                                    List<String> newList = new ArrayList<String>(theList);
                                    newList.add(end);
                                    result.add(newList);
                                }
                            } else {
                                if (!isFound && dict.contains(newS)) {
                                    if (map.get(newS) == null || map.get(newS).get(0).size() == map.get(s).get(0).size()+1) {
                                        if (map.get(newS) == null) {
                                            map.put(newS, new ArrayList<List<String>>());
                                            queue.add(newS);
                                        }
                                        
                                        lists = map.get(s);
                                        for (List<String> theList : lists) {
                                            List<String> newList = new ArrayList<String>(theList);
                                            newList.add(newS);
                                            map.get(newS).add(newList);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            if (isFound) break;
        }
        
        return result;
    }
    
    private String replace(String s, int j, char c) {
        char[] cArray = s.toCharArray();
        cArray[j] = c;
        return new String(cArray);
    }

    public ArrayList<ArrayList<String>> findLadders(String start, String end,
            HashSet<String> dict) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();

        if (start.equals(end)) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(start);
            res.add(list);
            return res;
        }

        Queue<String> queue = new LinkedList<String>();
        Map<String, Integer> depthMap = new HashMap<String, Integer>();
        Map<String, ArrayList<ArrayList<String>>> pathMap = new HashMap<String, ArrayList<ArrayList<String>>>();

        queue.add(start);
        depthMap.put(start, 1);
        ArrayList<String> list = new ArrayList<String>();
        list.add(start);
        ArrayList<ArrayList<String>> pathList = new ArrayList<ArrayList<String>>();
        pathList.add(list);
        pathMap.put(start, pathList);

        int foundDepth = -1;

        while (!queue.isEmpty()) {
            String s = queue.poll();

            int depth = depthMap.get(s);
            if (foundDepth != -1 && depth > foundDepth) {
                return res;
            }

            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < 26; j++) {
                    char[] cArray = s.toCharArray();
                    cArray[i] = (char) ((int) 'a' + j);
                    String newS = new String(cArray);
                    if (newS.equals(end)) {
                        if (foundDepth == -1) {
                            foundDepth = depth;
                        }

                        ArrayList<ArrayList<String>> pList = pathMap.get(s);
                        for (int k = 0; k < pList.size(); k++) {
                            ArrayList<String> sList = pList.get(k);
                            ArrayList<String> resList = new ArrayList<String>(
                                    sList);
                            resList.add(end);
                            res.add(resList);
                        }
                    }

                    if (foundDepth == -1) {
                        if (dict.contains(newS)) {
                            if (depthMap.get(newS) == null) {
                                queue.add(newS);
                                depthMap.put(newS, depth + 1);

                                ArrayList<ArrayList<String>> newPathList = new ArrayList<ArrayList<String>>();
                                ArrayList<ArrayList<String>> pList = pathMap
                                        .get(s);
                                for (int k = 0; k < pList.size(); k++) {
                                    ArrayList<String> sList = pList.get(k);
                                    ArrayList<String> resList = new ArrayList<String>(
                                            sList);
                                    resList.add(newS);
                                    newPathList.add(resList);
                                }

                                pathMap.put(newS, newPathList);
                            } else if (depthMap.get(newS) == depth + 1) {
                                ArrayList<ArrayList<String>> newPathList = new ArrayList<ArrayList<String>>();
                                ArrayList<ArrayList<String>> pList = pathMap
                                        .get(s);
                                for (int k = 0; k < pList.size(); k++) {
                                    ArrayList<String> sList = pList.get(k);
                                    ArrayList<String> resList = new ArrayList<String>(
                                            sList);
                                    resList.add(newS);
                                    newPathList.add(resList);
                                }

                                pathMap.get(newS).addAll(newPathList);
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

}
