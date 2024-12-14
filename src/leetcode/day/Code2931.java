package leetcode.day;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code2931 {
    public long maxSpending(int[][] values) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> values[o.i][o.j]));
        for (int i = 0; i < values.length; i++) {
            Node node = new Node(i, values[i].length - 1);
            queue.add(node);
        }
        long ans = 0;
        int index = 1;
        while (!queue.isEmpty()) {
            Node pop = queue.poll();
            int r = pop.i;
            int c = pop.j;
            ans += (long) values[r][c] * (index++);
            if (c != 0) {
                queue.add(new Node(r, c - 1));
            }
        }
        return ans;
    }


    class Node {
        int i;
        int j;

        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
