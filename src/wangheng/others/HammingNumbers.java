package wangheng.others;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HammingNumbers {

    public static void main(String[] args) {
        HammingNumbers solution = new HammingNumbers();
        System.out.println(solution.getHammingNumbers(20));
        System.out.println(solution.getHammingNumbers2(20));
        System.out.println(solution.getHammingNumbers3(20));
        System.out.println(solution.getHammingNumbers(1691).get(1690));
        System.out.println(solution.getHammingNumbers2(1691).get(1690));
        System.out.println(solution.getHammingNumbers3(1691).get(1690));
    }

    public List getHammingNumbers(int n) {
        PriorityQueue<BigInteger> queue = new PriorityQueue<BigInteger>();
        List<BigInteger> list = new ArrayList<BigInteger>();

        if (n == 0)
            return list;

        BigInteger one = BigInteger.valueOf(1);
        queue.offer(one);

        BigInteger two = BigInteger.valueOf(2);
        BigInteger three = BigInteger.valueOf(3);
        BigInteger five = BigInteger.valueOf(5);

        for (int i = 0; i < n; i++) {
            BigInteger hammingNumber = queue.poll();
            list.add(hammingNumber);

            while (!queue.isEmpty() && hammingNumber.equals(queue.peek())) {
                queue.poll();
            }

            queue.offer(hammingNumber.multiply(two));
            queue.offer(hammingNumber.multiply(three));
            queue.offer(hammingNumber.multiply(five));
        }

        return list;
    }

    public List getHammingNumbers2(int n) {
        List<BigInteger> list = new ArrayList<BigInteger>();

        if (n == 0)
            return list;

        BigInteger one = BigInteger.valueOf(1);
        list.add(one);

        BigInteger two = BigInteger.valueOf(2);
        BigInteger three = BigInteger.valueOf(3);
        BigInteger five = BigInteger.valueOf(5);

        BigInteger x2 = two;
        BigInteger x3 = three;
        BigInteger x5 = five;

        int i = 0, j = 0, k = 0;

        for (int p = 1; p < n; p++) {
            BigInteger hammingNumber = x2.min(x3).min(x5);
            list.add(hammingNumber);

            if (hammingNumber.equals(x2))
                x2 = two.multiply(list.get(++i));
            if (hammingNumber.equals(x3))
                x3 = three.multiply(list.get(++j));
            if (hammingNumber.equals(x5))
                x5 = five.multiply(list.get(++k));
        }

        return list;
    }

    public List getHammingNumbers3(int n) {
        List<Integer> list = new ArrayList<Integer>();

        if (n == 0)
            return list;

        for (int i = 1; list.size() < n; i++) {
            int k = i;
            while (k % 2 == 0)
                k = k / 2;
            while (k % 3 == 0)
                k = k / 3;
            while (k % 5 == 0)
                k = k / 5;

            if (k == 1)
                list.add(i);
        }

        return list;
    }
}
