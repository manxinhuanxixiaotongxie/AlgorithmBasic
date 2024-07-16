package systemreview.code06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定很多线段 每个线段都有两个数[start, end]
 * 表示线段开始位置和结束位置 左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中 包含了几条线段
 */
public class Code04_SegmentOverLap {
    /**
     * 暴力解法
     * 用一个小数 进行判断
     * <p>
     * 因为要判断是否重叠 比如使用0.5 那么包含0.5的线段 一定会重叠
     */

    public int segment(int[][] segment) {
        if (segment == null || segment.length == 0 || segment[0].length != 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < segment.length; i++) {
            max = Math.max(max, segment[i][1]);
            min = Math.min(min, segment[i][0]);
        }
        int ans = 0;
        for (double i = min + 0.5; i < max; i++) {
            int count = 0;
            for (int j = 0; j < segment.length; j++) {
                if (segment[j][0] < i && segment[j][1] > i) {
                    count++;
                }
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public int segment2(int[][] segment) {
        if (segment == null || segment.length == 0 || segment[0].length != 2) {
            return 0;
        }
        Arrays.sort(segment, Comparator.comparingInt(a -> a[0]));
        // 这个方法使用堆进行改写
        /**
         * 过程如下：
         * 1.对start进行排序
         * 2.使用一个小根堆 堆中放的元素是线段的结束位置
         *
         * 为什么可以这么做：
         * 如果两个线段要重合的话 有个结论 第二个线段的开始一定要比前面线段的结束要小
         *
         * 必须要以某一个线段作为开始值  其实就是在求前面有多少个数的end的位置当前的start要大
         */
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i < segment.length; i++) {
            while (!heap.isEmpty() && heap.peek() <= segment[i][0]) {
                heap.poll();
            }
            heap.add(segment[i][1]);
            ans = Math.max(ans, heap.size());
        }
        return ans;
    }
}
