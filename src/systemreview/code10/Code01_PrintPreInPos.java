package systemreview.code10;

import sun.management.snmp.jvminstr.JvmMemPoolEntryImpl;

import java.util.Stack;

/**
 * 树的深度优先遍历
 * 先序遍历：头左右
 * 中序遍历：左头右
 * 后序遍历：左右头
 */
public class Code01_PrintPreInPos {

    /**
     * 递归方式实现树的先序遍历
     *
     * @param head
     */
    public void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);

    }

    /**
     * 使用迭代方式实现二叉树的先序优先遍历
     *
     * @param head
     */
    public void pre2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.value);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 递归方式实现树的中序遍历
     *
     * @param head
     */
    public void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    /**
     * 使用迭代方式实现二叉树的中序优先遍历
     */
    public void in2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                System.out.println(cur.value);
                cur = cur.right;
            }
        }
    }

    /**
     * 递归方式实现树的后序遍历
     *
     * @param head
     */
    public void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }
}

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }
}
