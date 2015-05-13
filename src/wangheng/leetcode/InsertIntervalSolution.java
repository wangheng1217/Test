package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;

public class InsertIntervalSolution {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> mergedList = new ArrayList<Interval>();
        boolean isMerged = false;
        for (Interval interval : intervals) {
            if (isMerged) mergedList.add(interval);
            else {
                if (interval.end < newInterval.start) {
                    mergedList.add(interval);
                } else {
                    if (interval.start > newInterval.end) {
                        mergedList.add(newInterval);
                        mergedList.add(interval);
                        isMerged = true;
                    } else {
                        newInterval.start = Math.min(newInterval.start, interval.start);
                        newInterval.end = Math.max(newInterval.end, interval.end);
                    }
                }
            }
        }
        
        if (!isMerged) {
            mergedList.add(newInterval);
        }
        
        return mergedList;
    }

    public ArrayList<Interval> insert(ArrayList<Interval> intervals,
            Interval newInterval) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if (intervals.size() == 0) {
            res.add(newInterval);
            return res;
        }
        boolean inserted = false;
        for (int i = 0; i < intervals.size(); i++) {
            Interval currInterval = intervals.get(i);
            if (inserted) {
                res.add(currInterval);
            } else {
                if (newInterval.end < currInterval.start) {
                    res.add(newInterval);
                    res.add(currInterval);
                    inserted = true;
                } else if (currInterval.end < newInterval.start) {
                    res.add(currInterval);
                    if (i == intervals.size() - 1) {
                        res.add(newInterval);
                    }
                } else {
                    newInterval.start = min(currInterval.start,
                            newInterval.start);
                    newInterval.end = max(currInterval.end, newInterval.end);
                    if (i == intervals.size() - 1) {
                        res.add(newInterval);
                    }
                }
            }
        }
        return res;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}
