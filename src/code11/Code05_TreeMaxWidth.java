package code11;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-23 16:32
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点：按层遍历，
 * 定义一个当前层结束的结尾节点变量
 * 定义一个下一层结束的结尾节点变量
 */
public class Code05_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int maxWidthNoMap(Node head) {

        if (head == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        // 当前层，最右节点是谁
        Node curEnd = head;
        // 下一层，最右节点是谁
        Node nextEnd = null;
        // 当前层有多少节点
        int curLevelNodes = 0;
        // 最大节点数
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;

            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;

            if (cur == curEnd) {
                max = Math.max(max,curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }

        }

        return max;
    }

    public static int maxWidthwWithMap(Node head) {

        if (head == null) {
            return 0;
        }

        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 当前层
        int curLevel = 1;
        // 当前层有多少节点
        int curLevelNodes = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                levelMap.put(cur.left,levelMap.get(cur)+1);
                queue.add(cur.left);
            }

            if (cur.right != null) {
                levelMap.put(cur.right,levelMap.get(cur)+1);
                queue.add(cur.right);
            }

            // 不是当前层
            if (levelMap.get(cur) != curLevel) {
                max = Math.max(max,curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            } else {
                curLevelNodes++;
            }
        }

        return max;
    }

}
