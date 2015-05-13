package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;

public class GrayCodeSolution {
    
    public static void main(String[] args) {
        System.out.println(new GrayCodeSolution().grayCode(5));
    }
    
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        for (int i = 0; i < n; i++) {
            int inc = 1 << i;
            for (int j = list.size()-1; j >= 0; j--) {
                list.add(list.get(j) + inc);
            }
        }
        return list;
    }
}