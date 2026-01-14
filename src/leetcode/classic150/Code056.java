package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

public class Code056 {
    public int[][] merge(int[][] intervals) {
        // 排序 会议开始时间进行排序
        java.util.Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> ans = new ArrayList<>();
        int index = 0;
        int right = 1;
        int end = intervals[0][1];
        int n = intervals.length;
        while (index < n) {
            while (right < n && intervals[right][0] <= end) {
                end = Math.max(end, intervals[right][1]);
                right++;
            }
            // 结算
            if (index == right) {
                ans.add(new int[]{intervals[index][0], end});
                index++;
                right++;
                if (index == n) {
                    break;
                }
                end = Math.max(end, intervals[index][1]);
            } else {
                // 不相等 说明有合并
                ans.add(new int[]{intervals[index][0], end});
                index = right;
                if (index == n) {
                    break;
                }
                end = intervals[index][1];
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }

    static void main() {
        Code056 code056 = new Code056();
        int[][] merge = code056.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        for (int[] arr : merge) {
            System.out.println(java.util.Arrays.toString(arr));
        }
    }
}
