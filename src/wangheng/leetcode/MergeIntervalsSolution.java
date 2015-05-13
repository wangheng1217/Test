package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervalsSolution {
    public List<Interval> merge(List<Interval> intervals) {
        Comparator<Interval> c = new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                if (i1.start < i2.start) return -1;
                if (i1.start > i2.start) return 1;
                return 0;
            }
        };
        
        Collections.sort(intervals, c);
        
        List<Interval> mergeList = new ArrayList<Interval>();
        Interval currInterval = null;
        for (Interval interval : intervals) {
            if (currInterval == null) {
                currInterval = interval;
            } else {
                if (currInterval.end >= interval.start) {
                    currInterval.end = Math.max(currInterval.end, interval.end);
                } else {
                    mergeList.add(currInterval);
                    currInterval = interval;
                }
            }
        }
        
        if (currInterval != null) mergeList.add(currInterval);
        
        return mergeList;
    }

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<Interval>();
        if (intervals.size() == 0)
            return res;
        java.util.Collections.sort(intervals, new IntervalComparator());
        Interval currInterval = intervals.get(0);
        res.add(currInterval);
        for (int i = 1; i < intervals.size(); i++) {
            Interval mergeInterval = intervals.get(i);
            if (mergeInterval.start > currInterval.end) {
                res.add(mergeInterval);
                currInterval = mergeInterval;
            } else {
                currInterval.end = max(currInterval.end, mergeInterval.end);
            }
        }
        return res;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

}

class IntervalComparator implements java.util.Comparator<Interval> {

    @Override
    public int compare(Interval o1, Interval o2) {
        if (o1 == null) {
            return o2 == null ? 0 : 1;
        } else if (o2 == null) {
            return -1;
        } else {
            return o1.start - o2.start;
        }
    }
}
