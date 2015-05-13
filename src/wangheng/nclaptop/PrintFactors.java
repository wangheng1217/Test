package wangheng.nclaptop;

import java.util.ArrayList;
import java.util.List;

public class PrintFactors {
    public static void main(String[] args) {
        PrintFactors solution = new PrintFactors();
        print(solution.printFactors(32));
    }

    private static void print(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    private List<List<Integer>>[] map;

    public List<List<Integer>> printFactors(int n) {
        if (map == null)
            map = new ArrayList[n];

        if (map[n - 1] != null)
            return map[n - 1];

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(n);
        result.add(list);

        for (int i = 2; i * i <= n; i++) {
            if (n % i > 0)
                continue;

            int rest = n / i;
            list = new ArrayList<Integer>();
            list.add(i);
            list.add(rest);
            result.add(list);

            List<List<Integer>> restLists = printFactors(rest);
            for (List<Integer> restList : restLists) {
                if (restList.get(0) < i)
                    continue;

                list = new ArrayList<Integer>();
                list.add(i);
                list.addAll(restList);
                result.add(list);
            }
        }

        map[n - 1] = result;
        return result;
    }
}
