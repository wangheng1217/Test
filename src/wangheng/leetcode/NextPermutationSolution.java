package wangheng.leetcode;

public class NextPermutationSolution {
    public void nextPermutation2(int[] num) {
        boolean found = false;
        for (int i = num.length-2; i >= 0; i--) {
            if (num[i] >= num[i+1]) continue;
            else {
                // num[i] < num[i+1]
                for (int j = num.length-1; j > i; j--) {
                    if (num[j] <= num[i]) continue;
                    else {
                        swap(num, i, j);
                        reverse(num, i+1, num.length-1);
                        break;
                    }
                }
                found = true;
                break;
            }
        }
        if (!found) reverse(num, 0, num.length-1);
    }
    
    private void reverse(int[] num, int i, int j) {
        while (i < j) {
            swap(num, i, j);
            i++;
            j--;
        }
    }

    public void nextPermutation(int[] num) {
        for (int i = num.length - 1; i >= 0; i--) {
            for (int j = num.length - 1; j > i; j--) {
                if (num[i] < num[j]) {
                    swap(num, i, j);
                    int k = 0;
                    while (i + 1 + k < num.length - 1 - k) {
                        swap(num, i + 1 + k, num.length - 1 - k);
                        k++;
                    }
                    return;
                }
            }
        }

        int k = 0;
        while (k < num.length - 1 - k) {
            swap(num, k, num.length - 1 - k);
            k++;
        }
    }

    private void swap(int[] num, int a, int b) {
        num[a] = num[a] + num[b];
        num[b] = num[a] - num[b];
        num[a] = num[a] - num[b];
    }

}
