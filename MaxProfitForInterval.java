/*
Find the max profit given a list of intervals
*/

import java.util.*;

class MaxProfitForInterval {
    class Element {
        int start;
        int end;
        int profit;

        public Element (int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    public int solve(int[][] intervals) {
        List<Element> list = new ArrayList();
        for (int i=0;i<intervals.length; i++) {
            list.add(new Element(intervals[i][0], intervals[i][1], intervals[i][2]));
        }
        Collections.sort(list, (a,b)-> a.end - b.end);

        int[] dp = new int[list.size()];

        // Initialize the DP
        for (int i=0; i<list.size(); i++) {
            dp[i] = list.get(i).profit;
        }

        // Compute
        for (int i=0; i<intervals.length; i++) {
            int start = list.get(i).start, end = list.get(i).end, profit = list.get(i).profit;
            
            int pIndex = binarySearch(list, start);
            if (pIndex != -1) {
                dp[i] = dp[pIndex] + profit;        
            }
            
            dp[i] = i>=1 ? Math.max(dp[i], dp[i-1]) : profit;

        }
        
        // Return
        return dp[dp.length - 1];
    }

    public int binarySearch(List<Element> list, int start) {

        int begin = 0, end = list.size()-1;
        int potentialIndex = -1;

        while (begin <= end) {
            int mid = (begin + end)/2;
            if (list.get(mid).end <= start) {
                potentialIndex = mid;
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return potentialIndex;

    }

}
