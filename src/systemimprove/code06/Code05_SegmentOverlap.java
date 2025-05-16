package systemimprove.code06;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 * <p>
 * 暴力解法：
 * 1. 找到所有线段的最大值和最小值
 * 2. 从最小值到最大值，每次+0.5，看看有多少条线段包含这个点
 * 3. 选出最大的那个
 * <p>
 * 使用堆进行处理：
 * <p>
 * 1. 将所有线段按照start进行排序
 * 2. 从左到右遍历，每次遍历到一个线段，将这个线段的end放入堆中
 * 3. 每次遍历到一个线段，将堆中所有小于当前start的点弹出
 */
public class Code05_SegmentOverlap {

    class Segment {
        private int start;
        private int end;

        public Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 暴力解法
     *
     * @param segments
     * @return
     */
    public int getMaxSegment(List<Segment> segments) {
        if (segments == null || segments.size() == 0) {
            return 0;
        }
        int max = 0;
        int min = 0;
        for (Segment segment : segments) {
            max = Math.max(max, segment.end);
            min = Math.min(min, segment.start);
        }
        int ans = 0;
        for (double i = min + 0.5; i < max; i++) {
            int count = 0;
            for (Segment segment : segments) {
                if (segment.start < i && segment.end > i) {
                    count++;
                }
            }
            ans = Math.max(ans, count);
        }

        return ans;
    }

    /**
     * 用堆来解决
     * 1. 将所有线段按照start进行排序
     * 2. 从左到右遍历，每次遍历到一个线段，将这个线段的end放入堆中
     * 3. 每次遍历到一个线段，将堆中所有小于当前start的点弹出
     *
     * @param segments
     * @return
     */

    public int getMaxSegment2(List<Segment> segments) {
        if (segments == null || segments.size() == 0) {
            return 0;
        }

        Arrays.sort(segments.toArray(), (o1, o2) -> {
            Segment s1 = (Segment) o1;
            Segment s2 = (Segment) o2;
            return s1.start - s2.start;
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int ans = 0;
        for (Segment segment : segments) {
            // 将堆中所有小于当前start的点弹出
            while (!heap.isEmpty() && heap.peek() <= segment.start) {
                heap.poll();
            }
            heap.add(segment.end);
            ans = Math.max(ans, heap.size());
        }

        return ans;
    }


}
