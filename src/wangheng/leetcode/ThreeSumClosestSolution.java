package wangheng.leetcode;

public class ThreeSumClosestSolution {

    public int threeSumClosest(int[] num, int target) {
        if (num.length < 3)
            return 0;
        java.util.Arrays.sort(num);
        int closestTarget = num[0] + num[1] + num[2];
        int closestDiff = diff(target, closestTarget);
        if (closestDiff == 0)
            return closestTarget;
        for (int i = 0; i < num.length - 2; i++) {
            int j = i + 1;
            int k = num.length - 1;
            while (j < k) {
                int sum = num[i] + num[j] + num[k];
                int diff = diff(sum, target);
                if (diff < closestDiff) {
                    closestDiff = diff;
                    closestTarget = sum;
                }
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return closestTarget;
    }

    private int diff(int a, int b) {
        return a > b ? a - b : b - a;
        // return Math.abs(a-b);
    }

}
