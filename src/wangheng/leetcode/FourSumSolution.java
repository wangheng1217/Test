package wangheng.leetcode;

import java.util.ArrayList;

public class FourSumSolution {

    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        if (num.length < 4)
            return list;
        java.util.Arrays.sort(num);
        for (int i = 0; i < num.length - 3; i++) {
            if (i > 0 && num[i] == num[i - 1])
                continue;
            for (int j = i + 1; j < num.length - 2; j++) {
                if (j > i + 1 && num[j] == num[j - 1])
                    continue;
                int k = j + 1;
                int l = num.length - 1;
                while (k < l) {
                    int sum = num[i] + num[j] + num[k] + num[l];
                    if (sum < target) {
                        k++;
                    } else if (sum > target) {
                        l--;
                    } else {
                        ArrayList<Integer> intList = new ArrayList<Integer>(4);
                        intList.add(0, num[i]);
                        intList.add(1, num[j]);
                        intList.add(2, num[k]);
                        intList.add(3, num[l]);
                        list.add(intList);
                        k++;
                        while (k < l && num[k] == num[k - 1]) {
                            k++;
                        }
                        l--;
                        while (k < l && num[l] == num[l + 1]) {
                            l--;
                        }
                    }
                }
            }
        }
        return list;
    }

}
