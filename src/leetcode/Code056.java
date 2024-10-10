package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Code056 {
    public int[][] merge(int[][] intervals) {
        int[][] ans = new int[intervals.length][2];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int L = 0;
        int max = intervals[0][1];
        int min = intervals[0][0];
        int index = 0;
        while (L < intervals.length) {
            // 初始位置向后推 一直到推不动为止
            while (intervals[L][0] <= max) {
                max = Math.max(max, intervals[L][1]);
                L++;
                if (L == intervals.length) {
                    break;
                }
            }
            ans[index][0] = min;
            ans[index][1] = max;
            index++;
            if (L < intervals.length) {
                min = intervals[L][0];
                max = intervals[L][1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        Code056 solution = new Code056();
        int[][] merge = solution.merge(intervals);
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
