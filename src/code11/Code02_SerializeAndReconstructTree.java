package code11;

/**
 * @Description 二叉树的序列化与反序列化
 * <p>
 * 提供先序 后续序列化以及按层序列化方式
 * <p>
 * code297
 * @Author Scurry
 * @Date 2022-07-23 14:55
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 实现二叉树的序列化与反序列化
 */
public class Code02_SerializeAndReconstructTree {

    /**
     * 二叉树可以通过先序 后序 或者按层遍历的方式实现序列化和发序列化
     * <p>
     * 注意：二叉树无法按照中序遍历实现序列化与反序列化
     * 不同的两个树可能得到相同中序序列
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 先序序列化
     *
     * @param head
     * @return
     */
    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    /**
     * 先序序列化
     *
     * @param head
     * @param queue
     */
    public static void pres(Node head, Queue<String> queue) {
        /**
         *
         * 序列化的过程中 空节点也要进行序列化
         *
         */
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.value));
            pres(head.left, queue);
            pres(head.right, queue);
        }
    }

    /**
     * 后序序列化
     *
     * @param head
     * @return
     */
    public static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    public static void poss(Node head, Queue<String> ans) {
        // 空节点也要进行序列化
        if (head == null) {
            ans.add(null);
        } else {
            poss(head.left, ans);
            poss(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    /**
     * 利用先序序列化的结果进行反序列化
     *
     * @param preList
     * @return
     */
    public static Node buildByPreQueue(Queue<String> preList) {
        if (preList == null || preList.isEmpty()) {
            return null;
        }
        return preb(preList);
    }

    /**
     * 先序序列反序列化
     * <p>
     * 1 2  4 null null null 3 5 6 null null 7 null null null
     */
    public static Node preb(Queue<String> queue) {
        String poll = queue.poll();
        if (poll == null) {
            return null;
        }
        Node node = new Node(Integer.parseInt(poll));
        node.left = preb(queue);
        node.right = preb(queue);
        return node;
    }

    /**
     * 先序的结果是头左右
     * <p>
     * 后序是左右头
     * <p>
     * 将后序的结果进行翻转就是头右左
     */
    public static Node buildByPosQueue(Queue<String> poslist) {
        if (poslist == null || poslist.isEmpty()) {
            return null;
        }
        // 左右中  ->  stack(中右左)
        Stack<String> stack = new Stack<>();
        while (!poslist.isEmpty()) {
            stack.push(poslist.poll());
        }
        return posb(stack);
    }

    public static Node posb(Stack<String> posstack) {
        String value = posstack.pop();
        if (value == null) {
            return null;
        }
        Node head = new Node(Integer.parseInt(value));
        head.right = posb(posstack);
        head.left = posb(posstack);
        return head;
    }


    /**
     * 按层序列化
     *
     * @param head
     */
    public static Queue<String> levelSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    ans.add(String.valueOf(poll.left.value));
                    queue.add(poll.left);
                } else {
                    ans.add(null);
                }
                if (poll.right != null) {
                    ans.add(String.valueOf(poll.right.value));
                    queue.add(poll.right);
                } else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static Node buildByLevelQueue(Queue<String> levelList) {
        if (levelList == null || levelList.isEmpty()) {
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        Node node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.parseInt(val));
    }


}
