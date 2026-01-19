package leetcode.classic150;

public class Code057 {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        int l = 0, r = 0;
        while (l < n && intervals[l][1] < newInterval[0]) {
            l++;
        }
        while (r < n && intervals[r][0] <= newInterval[1]) {
            r++;
        }

        // 范围在[l,r)之间
        int[][] res = new int[n - (r - l) + 1][2];
        // 复制前半部分
        for (int i = 0; i < l; i++) {
            res[i] = intervals[i];
        }
        // 合并区间
        int start = newInterval[0], end = newInterval[1];
        if (l < r) {
            start = Math.min(start, intervals[l][0]);
            end = Math.max(end, intervals[r - 1][1]);
        }
        res[l] = new int[]{start, end};
        // 复制后半部分
        for (int i = r; i < n; i++) {
            res[l + 1 + i - r] = intervals[i];
        }
        return res;
    }
}
