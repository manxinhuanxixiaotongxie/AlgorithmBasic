package leetcode.day;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * <p>
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * <p>
 * 日程可以用一对整数 startTime 和 endTime 表示，这里的时间是半开区间，即 [startTime, endTime), 实数 x 的范围为，  startTime <= x < endTime 。
 * <p>
 * 实现 MyCalendar 类：
 * <p>
 * MyCalendar() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 * <p>
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 */
public class MyCalendar {

    List<int[]> booked;

    TreeSet<int[]> treeSet;

    public MyCalendar() {
        booked = new ArrayList<>();
        // 根据开始时间进行排序
        treeSet = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
    }

    /**
     * 暴力解法：
     * 维护一个列表 用于存放能够安排的日程信息
     * <p>
     * 如果一个新的一日程能够被安排
     * 那么就是当前日程开始时间比列表中结束日期大 或者当前日志结束时间比列表中任何的日程都小
     * 不满足这两个条件  都不可能添加到新日程中
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean book(int startTime, int endTime) {
        if (booked.isEmpty()) {
            booked.add(new int[]{startTime, endTime});
            return true;
        } else {
            for (int[] temp : booked) {
                int inStart = temp[0];
                int inEnd = temp[1];
                // 已经占用了预定位置的开始时间与结束时间不允许占用
                // 快速判断是不是位置被占用
//                if ((startTime > inStart && startTime < inEnd) || (endTime > inStart && endTime < inEnd)) {
//                    return false;
//                }
                if (!(startTime >= inEnd || endTime <= inStart)) {
                    return false;
                }
            }
        }
        // 允许占用的话就添加到预定列表中
        booked.add(new int[]{startTime, endTime});
        return true;
    }

    /**
     * 使用二分进行优化
     * <p>
     * 1.将列表中的已经安排的日程按照开始时间进行排序
     * 2.那么如果一个日程想要被安排，只有一种情况
     * 找到开始时间比endTime大的的日程   找到开始时间在之前的一个日程
     * <p>
     * 只有一种情况能被安排：
     * startTime>=上一个开时间所在位置的结束时间 && endTime<=下一个开始时间所在位置的开始时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean book2(int startTime, int endTime) {
        if (treeSet.isEmpty()) {
            treeSet.add(new int[]{startTime, endTime});
            return true;
        }else {
            // 找到开始时间大于等于结束时间的日程
            int[] ceiling = treeSet.ceiling(new int[] {endTime, 0});
            int[] pre = ceiling == null ? treeSet.last() : treeSet.lower(ceiling);
            // treeSet.lower 严格小于
            if (ceiling == treeSet.first() || treeSet.lower(ceiling)[1] <= startTime) {
                treeSet.add(new int[]{startTime, endTime});
                return true;
            }

        }
        return true;
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public boolean bookRight(int startTime, int endTime) {
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

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.bookRight(10, 20));
        System.out.println(myCalendar.bookRight(15, 25));
        System.out.println(myCalendar.bookRight(20, 30));
    }
}
