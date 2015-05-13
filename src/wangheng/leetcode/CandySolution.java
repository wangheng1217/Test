package wangheng.leetcode;

public class CandySolution {

    public static void main(String[] args) {
        int[] ratings = new int[20000];
        for (int i = 0; i < 20000; i++) {
            ratings[i] = 20000 - i;
        }
        long a = System.currentTimeMillis();
        System.out.println(new CandySolution().candy(ratings));
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }

    public int candy(int[] ratings) {
        int length = ratings.length;
        if (length == 0) return 0;
        
        int preCandy = 1;
        int total = 1;
        int c = 1;
        int mark = 0;
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i-1]) {
                preCandy++;
                total = total + preCandy;
                c = 0;
                mark = 0;
            } else if (ratings[i] == ratings[i-1]) {
                preCandy = 1;
                total++;
                c = 1;
                mark = 0;
            } else {
                if (c == 0) {
                    mark = preCandy;
                    preCandy = 1;
                    total++;
                    c = 1;
                } else {
                    preCandy = 1;
                    c++;
                    if (c == mark) c++;
                    total = total + c;
                }
            }
        }

        return total;
    }
    
    public int candy2(int[] ratings) {
        int length = ratings.length;
        if (length == 0) return 0;
        
        int[] candies = new int[length];
        for (int i = 0; i < length; i++) {
            candies[i] = 1;
        }
        
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            }
        }
        
        for (int i = length-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]) {
                candies[i] = candies[i+1] + 1;
            }
        }
        
        int total = 0;
        for (int i = 0; i < length; i++) {
            total = total + candies[i];
        }

        return total;
    }
}