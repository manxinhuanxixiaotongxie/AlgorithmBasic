package systemreview.code10;

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
     * Morris遍历的特性 所有有左树的节点都会来到两次
     * 先序遍历就是Morris遍历第一次来到节点的时候打印
     *
     * @param head
     */
    public void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.value);
                cur = cur.right;
            } else {
                // 存在左树
                Node leftRighrNode = cur.left;
                while (leftRighrNode.right != null && leftRighrNode != cur) {
                    leftRighrNode = leftRighrNode.right;
                }
                if (leftRighrNode.right == null) {
                    leftRighrNode.right = cur;
                    System.out.println(cur.value);
                    cur = cur.left;
                } else {
                    leftRighrNode.right = null;
                    cur = cur.right;
                }
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
     * 中序遍历就是Morris遍历的时候第二次来到节点的时候打印
     *
     * @param head
     */
    public void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.value);
                cur = cur.right;
            } else {
                Node leftRightNode = cur.left;
                while (leftRightNode.right != null && leftRightNode.right != cur) {
                    leftRightNode = leftRightNode.right;
                }
                if (leftRightNode.right == null) {
                    leftRightNode.right = cur;
                    cur = cur.left;
                } else {
                    leftRightNode.right = null;
                    System.out.println(cur.value);
                    cur = cur.right;
                }

            }
        }
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

    /**
     * 后序遍历
     * 有左树的就会来到节点两次 那么在第二次回到该节点的时候
     * 逆序打印左树的右边界就是二叉树的后序遍历
     * 注意：最后要针对当前节点的左树的右边界进行逆序打印
     *
     * @param head
     */
    public void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
            } else {
                Node leftRightNode = cur.left;
                while (leftRightNode.right != null && leftRightNode.right != cur) {
                    leftRightNode = leftRightNode.right;
                }
                if (leftRightNode.right == null) {
                    leftRightNode.right = cur;
                    cur = cur.left;
                } else {
                    leftRightNode.right = null;
                    printEdge(cur.left);
                    cur = cur.right;
                }
            }
        }
        // 后序遍历要考虑最右侧的一棵树  在Morris的过程中是没有打印的 单独进行处理
        printEdge(head);
    }

    private void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.value);
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private Node reverseEdge(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.right;
            head.right = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 使用Morris遍历的方式 实现二叉树的先序中序后序遍历
     */

    /**
     * Morris遍历
     */

    public void morris(Node head) {
        if (head == null) {
            return;
        }
        // morris遍历的过程 如果当前节点有左孩子 当前节点 来到左孩子 并且将左孩子最右侧节点指向当前节点
        // 如果当前节点左侧的最右侧节点已经指向了当前节点 那么左侧的最右侧节点指向null 并且当前节点来到右孩子
        // 如果当前节点没有左孩子 那么当前节点来到右孩子

        Node cur = head;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.value);
                cur = cur.right;
            } else {
                // 左侧节点不为空
                Node leftRightNode = cur.left;
                while (leftRightNode.right != null && leftRightNode.right != cur) {
                    leftRightNode = leftRightNode.right;
                }
                if (leftRightNode.right == null) {
                    System.out.println(cur.value);
                    leftRightNode.right = cur;
                    cur = cur.left;
                } else {
                    System.out.println(cur.value);
                    leftRightNode.right = null;
                    cur = cur.right;
                }
            }
        }
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
