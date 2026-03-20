package leetcode.classic150;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
    // 中位数 中位数 中位数是什么东西  平均分成两部分  如果是偶数 那么两边一边大小 取一边的最大值与另外一边的最小值的平均值
    // 如果是奇数个 我们规定 较小的那边永远比较大的那边多一个数 取较小一边的最大值作为中位数

    // 大根堆 作为小的一边的数据结构
    PriorityQueue<Integer> maxQueue = null;

    // 小跟堆 作为大的一边的数据结构
    PriorityQueue<Integer> minQueue = null;

    int size;

    public MedianFinder() {
        maxQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        minQueue = new PriorityQueue<>();
        size = 0;
    }

    public void addNum(int num) {
        if (maxQueue.isEmpty() && minQueue.isEmpty()) {
            maxQueue.add(num);
        }else if (maxQueue.size() == minQueue.size()) {
            // 先往小跟堆加
            minQueue.offer(num);
            maxQueue.add(minQueue.poll());
        }else {
            maxQueue.offer(num);
            minQueue.add(maxQueue.poll());
        }
    }

    public double findMedian() {
        if (maxQueue.size() == minQueue.size()) {
            return (maxQueue.peek() + minQueue.peek()) / 2.0;
        }else {
            return maxQueue.peek();
        }
    }
}
