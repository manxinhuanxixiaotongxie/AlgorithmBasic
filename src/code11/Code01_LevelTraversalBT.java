package code11;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-23 14:49
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现二叉树的按层遍历
 * 宽度优先遍历
 */
public class Code01_LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void level(Node head) {
        Queue<Node> nodes = new LinkedList<>();

        nodes.add(head);
        while (!nodes.isEmpty()) {
            Node poll = nodes.poll();
            System.out.println(poll.value);
            if (poll.left != null) {
                nodes.add(poll.left);
            }
            if (poll.right != null) {
                nodes.add(poll.right);
            }
        }
    }


}
