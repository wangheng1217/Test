package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangleIISolution {
    
    public static void main(String[] args) {
        PascalTriangleIISolution solution = new PascalTriangleIISolution();
        System.out.println(solution.getRow(1));
    }
    
    public List<Integer> getRow3(int rowIndex) {
        Integer[] row = new Integer[rowIndex+1];
        row[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            row[i] = 0;
            for (int j = i; j >= 1; j--) {
                row[j] = row[j] + row[j-1];
            }
        }

        return Arrays.asList(row);
    }
    
    public ArrayList<Integer> getRow(int rowIndex) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] row = new int[rowIndex+1];
        row[0] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i; j >= 1; j--) {
                row[j] = row[j] + row[j-1];
            }
        }
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < row.length; i++) {
            res.add(row[i]);
        }
        
        return res;
    }

    public ArrayList<Integer> getRow2(int rowIndex) {
        int[] row = new int[rowIndex + 1];
        row[rowIndex] = 1;
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = rowIndex - i; j < rowIndex; j++) {
                row[j] = row[j] + row[j + 1];
            }
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < row.length; i++) {
            res.add(row[i]);
        }
        return res;
    }

}
