package code12;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-08-01 15:02
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否是完全二叉树
 * 完全二叉树：全满或者是左边满
 * 1。所有节点没左孩子必须没有右孩子
 * 2。当第一次遇到左右孩子不双全，剩下遍历节点必须是叶子节点
 */
public class Code01_IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static boolean isCBT1(Node head) {

        if (head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node l = null;
        Node r = null;
        boolean isLeaf = false;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            l = cur.left;
            r = cur.right;

            /**
             * 1.左右不双全，必须是叶子节点
             * 2.不能有右树没有左树
             */
            if ((l == null && r != null) || isLeaf && (l != null || r != null)) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }

            // 左右不双全 那么后面的节点都必须是叶子节点
            // 什么意思呢？举个例子
            // 1 2 3 4 null 5 6
            // 上面这棵树 3节点子树已经是左右孩子不双全 那么在树的宽度优先遍历当中 后序节点都不能有孩子，即后序节点都必须是叶子节点
            if (l == null || r == null) {
                isLeaf = true;
            }
        }

        return true;

    }

    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }


    /**
     * 1）假设以X节点为头，假设可以向X左树和X右树要任何信息
     * 2）在上一步的假设下，讨论以X为头节点的树，得到答案的可能性（最重要）
     * 3）列出所有可能性后，确定到底需要向左树和右树要什么样的信息
     * 4）把左树信息和右树信息求全集，就是任何一棵子树都需要返回的信息S
     * 5）递归函数都返回S，每一棵子树都这么要求
     * 6）写代码，在代码中考虑如何把左树的信息和右树信息整合出整棵树的信息
     */

    public static class Info {
        private boolean isCBT;
        private boolean isFull;
        private int height;

        Info(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(true, true, 0);
        }

        Info left = process(X.left);
        Info right = process(X.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isCBT = false;

        /**
         * 完全二叉树的判断条件：
         *1.左树是完全二叉树 右树是满二叉树 并且左树的高度比右树大一个
         * 2.左树右树都是满二叉树  高度相同
         * 3.左树右树是完全二叉树盖度相同
         * 4.左树满右树满  高度+1
         */
        if (isFull) {
            isCBT = true;
        } else {
            if (left.isCBT && right.isCBT) {
                if (left.isCBT && right.isFull && left.height == right.height + 1) {
                    isCBT = true;
                } else if (left.isFull && right.isFull && left.height == right.height + 1) {
                    isCBT = true;
                } else if (left.isFull && right.isCBT && left.height == right.height) {
                    isCBT = true;
                }
            }
        }
        return new Info(isCBT, isFull, height);


    }

}
