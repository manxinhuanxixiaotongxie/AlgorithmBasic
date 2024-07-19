package systemreview.code11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的宽度优先遍历
 * 使用队列
 * 队列 先进先出
 */
public class Code01_ScanInLevel {

    public void inLevel(Node head) {
        if (head == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }
}


class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
        value = v;
    }
}
