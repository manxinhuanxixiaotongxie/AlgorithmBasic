package leetcode.day;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 按照第一个元素升序排序
            }
        });
        // 示例数据
        pq.offer(new int[]{3, 1});
        pq.offer(new int[]{1, 2});
        pq.offer(new int[]{2, 3});

        // 输出排序后的结果
        if (!pq.isEmpty()) {
            System.out.println(--pq.peek()[1]);
        }

        System.out.println(pq.peek()[1]);

        System.out.println("PriorityQueue contents after decrementing first element:");
    }
}
