package leetcode.day;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyCalendar {
    int minTime = Integer.MIN_VALUE;
    Integer maxTime = Integer.MIN_VALUE;
    int size;
    TreeMap<Integer, Info> minMap = null;
    TreeMap<Integer, Info> maxMap = null;
    List<int[]> booked;

    public MyCalendar() {
        size = 0;
        booked = new ArrayList<int[]>();

        minMap = new TreeMap<>();
        maxMap = new TreeMap<>();
    }

    /**
     * 这个解法有问题  没考虑到中间的情况
     * // 第一个区间是41---46
     * 第二个区间是  27-33
     * 那么久回产生  在一个数轴上  27左边的数    46右边的数   33-41之间的数
     * 这个思路少算了33-41这个区间的数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean book(int startTime, int endTime) {
        boolean ans = false;
        // 只有两种情况允许加入
        // startTime >= maxTime
        // minTime >= endTime
        if (size == 0) {
            minTime = startTime;
            maxTime = endTime;
            size++;
            ans = true;
        } else {
            // 里面已经有数据了
            if (startTime >= maxTime) {
                size++;
                ans = true;
            }
            if (minTime >= endTime) {
                size++;
                ans = true;
            }
            if (ans) {
                minTime = Math.min(minTime, startTime);
                maxTime = Math.max(maxTime, endTime);
                return ans;
            }
        }
        return ans;
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean book2(int startTime, int endTime) {
        for (int[] arr : booked) {
            int l = arr[0], r = arr[1];
            if (l < endTime && startTime < r) {
                return false;
            }
        }
        booked.add(new int[]{startTime, endTime});
        return true;
    }

    class Info {
        private int startTime;
        private int endTime;

        Info(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
