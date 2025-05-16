package systemimprove.code26;

/**
 * Morris遍历能够做到遍历一颗二叉树 时间复杂度是O(N) 空间复杂度是O(1)
 * <p>
 * 流程如下：
 * 1.当前节点没有左树 当前节点来到右树
 * 2.当前节点有左树 找到左树的最右侧节点
 * 2.1 如果左树的最右侧节点的有指针指向null 让其指向当前节点 当前节点来到左树
 * 2.2 如果左树的最右侧节点的有指针指向当前节点 让其指向null 当前节点来到右树
 */
public class Code01_Morris {

    public void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.left == null) {
                System.out.print(cur.value + " ");
                cur = cur.right;
            } else {
                Node mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.printf(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    System.out.print(cur.value + " ");
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 更简洁
     *
     * @param head
     */
    public void morris2(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur != null) {
            Node mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.printf(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    System.out.print(cur.value + " ");
                }
            }
            cur = cur.right;
        }
        System.out.println();
    }

    /**
     * 先序遍历
     * morris遍历的过程中打印节点的时机是在第一次来到节点的时候
     */
    public void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        while (cur != null) {
            if (cur.left == null) {
                // 当前节点没有左树 该节点知会到达一次
                System.out.print(cur.value + " ");
                cur = cur.right;
            } else {
                // 在Morris遍历的过程中 有左树的节点会来到第二次
                /**
                 *怎么判断节点是第一次来还是第二次来
                 * 当左树的最右侧节点的有指针指向null的时候 就是第一次来
                 * 当左树的最右侧节点的有指针指向当前节点的时候 就是第二次来
                 */
                Node mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 这样的说法其实不严谨 严格来说  应该是当前节点如果有左树的话 会来到两次
                    // 这是第第一次来到该节点
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 中序遍历
     * morris遍历的过程中打印节点的时机是在第二次来到节点的时候
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
                // 当前节点没有左树 该节点知会到达一次
                System.out.print(cur.value + " ");
                cur = cur.right;
            } else {
                // 在Morris遍历的过程中 有左树的节点会来到第二次
                /**
                 *怎么判断节点是第一次来还是第二次来
                 * 当左树的最右侧节点的有指针指向null的时候 就是第一次来
                 * 当左树的最右侧节点的有指针指向当前节点的时候 就是第二次来
                 */
                Node mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 第二次来到节点
                    System.out.print(cur.value + " ");
                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历
     * 后序遍历与Morris遍历之间的关系：
     * 根据Morris遍历退出后序遍历的过程：
     * 1.如果当前节点的左树的最右侧节点指向当前节点 那么就让左侧的最右侧节点的右树指向空
     * <p>
     * 并且逆序打印左树的右边界 得到的结果就是后序遍历
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

                /**
                 * 对于morris遍历来说 一个节点 如果有左树
                 * 那么该节点一定会来到两次
                 *
                 *怎么判断节点是第一次来还是第二次来
                 * 当左树的最右侧节点的有指针指向null的时候 就是第一次来
                 * 当左树的最右侧节点的有指针指向当前节点的时候 就是第二次来
                 */
                Node mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // 对于有左树的节点来说 进入这个分支就是第二来到该节点
                    // 对于没有左树的节点来说 进入这个分支就是第一次来到该节点
                    mostRight.right = null;
                    printPos(cur.left);
                    cur = cur.right;
                }
            }
        }
        printPos(head);
        System.out.println();
    }

    // 按照分析Morris遍历与后序遍历之间的关系 其实就是打印左树的右边界（逆序打印）
    private void printPos(Node node) {
        Node tail = reverseEdge(node);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverseEdge(tail);

    }

    private Node reverseEdge(Node node) {
        Node pre = null;
        Node next;
        while (node != null) {
            next = node.right;
            node.right = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        Code01_Morris code01_morris = new Code01_Morris();
        code01_morris.morris(head);
        System.out.println("============");
        code01_morris.morrisPre(head);
        System.out.println("============");
        code01_morris.morrisIn(head);
        System.out.println("============");
        code01_morris.morrisPos(head);
    }
}
