package wangheng.leetcode;

import java.util.ArrayList;

// greedy
public class ThreeSumSolution {

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        java.util.Arrays.sort(num);

        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < num.length - 2; i++) {
            if (i >= 1 && num[i] == num[i - 1])
                continue;
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                if (num[j] + num[k] < -num[i]) {
                    j++;
                } else if (num[j] + num[k] > -num[i]) {
                    k--;
                } else {
                    ArrayList<Integer> intList = new ArrayList<Integer>(3);
                    intList.add(0, num[i]);
                    intList.add(1, num[j]);
                    intList.add(2, num[k]);
                    list.add(intList);
                    j++;
                    while (j < k && num[j] == num[j - 1])
                        j++;
                    k--;
                    while (k > j && num[k] == num[k + 1])
                        k--;
                }
            }
        }
        return list;
    }
}
