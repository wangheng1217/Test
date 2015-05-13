package wangheng.leetcode;

public class PermutationSequenceSolution {

    public static void main(String[] args) {
        PermutationSequenceSolution solution = new PermutationSequenceSolution();
        int n = 3;
        for (int k = 1; k <= 6; k++) {
            System.out.println(solution.getPermutation(n, k));
        }
    }
    
    public String getPermutation2(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        k = k - 1;
        int[] factorial = new int[n];
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i+1;
            factorial[i] = (i == 0 ? 1 : i*factorial[i-1]);
        }
        
        int remain = k;
        for (int i = 0; i < n; i++) {
            if (remain == 0) break;
            remain = setFirstChar(array, i, remain, factorial);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public int setFirstChar(int[] array, int startIndex, int k, int[] factorial) {
        int groupSize = factorial[array.length-1-startIndex];
        int groupN = k/groupSize;
        int remain = k%groupSize;
        int tmp = array[startIndex+groupN];
        for (int i = startIndex + groupN; i > startIndex; i--) {
            array[i] = array[i-1];
        }
        array[startIndex] = tmp;
        return remain;
    }
    
    public String getPermutation3(int n, int k) {
        boolean[] selected = new boolean[n];
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i-1] * (i);
        }
        
        String s = "";
        k = k-1;
        for (int i = n-1; i >= 0; i--) {
            int j = k/factorial[i];
            for (int l = 0; l < n; l++) {
                if (selected[l] == false) {
                    if (j == 0) {
                        s = s + (l+1);
                        selected[l] = true;
                        break;
                    } else {
                        j--;
                    }
                }
            }
            k = k%factorial[i];
        }
        
        return s;
    }

    public String getPermutation(int n, int k) {
        int[] factorial = new int[n + 1];
        int[] num = new int[n];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            num[i - 1] = i;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < n; i++) {
            if (k > 0) {
                int j = (k - 1) / factorial[n - 1 - i];

                // shift num[i+j] to num[i];
                if (j != 0) {
                    int temp = num[i + j];
                    for (int l = i + j; l > i; l--) {
                        num[l] = num[l - 1];
                    }
                    num[i] = temp;
                }

                // update k
                k = k - j * factorial[n - 1 - i];
            }
            sb.append(num[i]);
        }

        return sb.toString();
    }
}
