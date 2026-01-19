package leetcode.classic150;

import java.util.LinkedList;
import java.util.Queue;

public class Code117 {
    /**
     * 宽度优先遍历
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return null;
        Node cur = root;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(cur);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node pre = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.pollFirst();
                node.next = pre;
                pre = node;
                if (node.right != null) queue.offerLast(node.right);
                if (node.left != null) queue.offerLast(node.left);
            }
        }
        return root;
    }
}
