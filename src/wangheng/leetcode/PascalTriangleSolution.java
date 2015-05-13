package wangheng.leetcode;

import java.util.ArrayList;

public class PascalTriangleSolution {

    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        if (numRows <= 0)
            return res;

        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        res.add(list);

        for (int i = 2; i <= numRows; i++) {
            ArrayList<Integer> preList = res.get(res.size() - 1);
            list = new ArrayList<Integer>();
            list.add(1);
            for (int j = 1; j < preList.size(); j++) {
                list.add(preList.get(j - 1) + preList.get(j));
            }
            list.add(1);
            res.add(list);
        }

        return res;
    }

}
