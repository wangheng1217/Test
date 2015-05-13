package wangheng.leetcode;

public class GasStationSolution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sum = 0;
        int total = 0;
        int j = -1;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            sum = sum + diff;
            total = total + diff;
            if (sum < 0) {
                sum = 0;
                j = i;
            }
        }
        return total >= 0 ? j+1 : -1;
    }
    
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int n = gas.length;
        int[] remainFromIToN = new int[n];
        int[] needFromIToN = new int[n];
        int[] remainFromZeroToI = new int[n];
        int[] needFromZeroToI = new int[n];
        
        for (int i = n-1; i >= 0; i--) {
            if (i == n-1) {
                remainFromIToN[i] = 0;
                needFromIToN[i] = 0;
            }
            else {
                remainFromIToN[i] = gas[i] - cost[i] + remainFromIToN[i+1];
                if (gas[i] >= cost[i]) {
                    needFromIToN[i] = (gas[i]-cost[i] >= needFromIToN[i+1]) ? 0 : needFromIToN[i+1]-(gas[i]-cost[i]);
                } else {
                    needFromIToN[i] = cost[i] - gas[i] + needFromIToN[i+1];
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                remainFromZeroToI[i] = 0;
                needFromZeroToI[i] = 0;
            } else {
                remainFromZeroToI[i] = remainFromZeroToI[i-1] + gas[i-1] - cost[i-1];
                if (gas[i-1] >= cost[i-1]) {
                    needFromZeroToI[i] = needFromZeroToI[i-1];
                } else {
                    needFromZeroToI[i] = (needFromZeroToI[i-1]+remainFromZeroToI[i-1] >= cost[i-1]-gas[i-1]) ? needFromZeroToI[i-1] : needFromZeroToI[i-1]+cost[i-1]-gas[i-1]-(needFromZeroToI[i-1]+remainFromZeroToI[i-1]);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (needFromIToN[i] == 0 && remainFromIToN[i] + gas[n-1] - cost[n-1] >= needFromZeroToI[i]) {
                return i;
            }
        }
        
        return -1;
    }
}