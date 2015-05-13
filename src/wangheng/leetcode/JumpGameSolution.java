package wangheng.leetcode;

public class JumpGameSolution {

    public boolean canJump(int[] A) {
        int curr = 0;
        while (curr < A.length - 1) {
            if (A[curr] == 0)
                return false;
            if (curr + A[curr] >= A.length - 1)
                return true;
            int maxJump = 0;
            int maxJumpIndex = curr + 1;
            for (int i = curr + 1; i <= curr + A[curr]; i++) {
                int jump = i + A[i];
                if (jump >= maxJump) {
                    maxJump = jump;
                    maxJumpIndex = i;
                }
            }
            curr = maxJumpIndex;
        }
        return true;
    }

}
