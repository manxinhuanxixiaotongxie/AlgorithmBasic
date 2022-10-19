package code12;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-08-01 15:02
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.Inflater;

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


    public static boolean isCBT1 (Node head) {

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
            if (l == null || r == null) {
                isLeaf = true;
            }
        }

        return true;

    }

    public static boolean isCBT2 (Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    public static class Info {
        private boolean isCBT;
        private boolean isFull;
        private  int height;

        Info(boolean isCBT,boolean isFull,int height) {
            this.isCBT = isCBT;
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(true,true,0);
        }

        Info left = process(X.left);
        Info right = process(X.right);
        int height = Math.max(left.height,right.height)+1;
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isCBT = false;

        if (left.isFull && right.isFull && left.height == right.height) {
            isCBT = true;
        }else if (left.isCBT && right.isFull && left.height == right.height+1) {
            isCBT = true;
        }else if (left.isFull && right.isFull && left.height == right.height +1) {
            isCBT = true;
        }else if (left.isFull && right.isCBT && left.height == right.height) {
            isCBT = true;
        }
        return new Info(isCBT,isFull,height);


    }





}