package wangheng.leetcode;

public class JumpGameIISolution {

    public int jump(int[] A) {
        if (A.length <= 1)
            return 0;
        int count = 0;
        for (int i = 0; i < A.length;) {
            if (i + A[i] >= A.length - 1)
                return count + 1;

            int maxJump = 0;
            int maxJumpIndex = 0;
            for (int j = i + 1; j <= i + A[i]; j++) {
                int jump = j + A[j];
                if (jump > maxJump) {
                    maxJump = jump;
                    maxJumpIndex = j;
                }
            }

            if (maxJumpIndex == 0)
                return -1;

            i = maxJumpIndex;
            count++;
        }
        return count;
    }

}
