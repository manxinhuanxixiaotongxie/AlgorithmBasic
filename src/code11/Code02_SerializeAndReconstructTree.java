package code11;

/**
 * @Description
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
     *
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

    public static Queue<Node> preSerial(Node head) {
        Queue<Node> queue = new LinkedList<>();
        pre2(head,queue);
        return queue;
    }

    /**
     * 按层遍历
     * @param head
     * @param ans
     */
    public static void pre1(Node head,Queue<Node> ans) {
//        queue.add(head);
//        while (!queue.isEmpty()) {
//            Node peek = queue.peek();
//            if (peek != null) {
//                queue.add(peek.left);
//                queue.add(peek.right);
//            }
//        }

        if (head != null) {
            ans.add(head);
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                    ans.add(poll.left);
                }else {
                    queue.add(null);
                }

                if (poll.right != null) {
                    queue.add(poll.right);
                    ans.add(poll.right);
                }else {
                    queue.add(null);
                }

            }

        }else {
            ans.add(null);
        }

    }

    /**
     * 先序序列化
     * @param head
     * @param queue
     */
    public static void pre2(Node head,Queue<Node> queue) {

        if (head == null) {
            queue.add(null);
        }else {
            queue.add(head);
            pre2(head.left,queue);
            pre2(head.right,queue);
        }

    }

    /**
     * 后序序列化
     * @param head
     * @return
     */
    public static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    public static void poss(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            poss(head.left, ans);
            poss(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    /**
     * 先序序列反序列化
     *
     *  1 2 3 4 null null 5 null null  6 null null 7 null null
     */
    public static Node preb(Queue<Node> queue) {
        Node poll = queue.poll();
        if (poll == null) {
            return null;
        }
        poll.left = preb(queue);
        poll.right = preb(queue);

        return poll;
    }
    /**
     * 后序进行转换
     */
    public static Node buildByPosQueue(Queue<String> poslist) {
        if (poslist == null || poslist.size() == 0) {
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
        Node head = new Node(Integer.valueOf(value));
        head.right = posb(posstack);
        head.left = posb(posstack);
        return head;
    }





}
