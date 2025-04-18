package code11;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-23 16:32
 */


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点：按层遍历，
 * 定义一个当前层结束的结尾节点变量
 * 定义一个下一层结束的结尾节点变量
 */
public class Code05_TreeMaxWidth {

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int v) {
            value = v;
        }
    }

    public static int maxWidthNoMap(TreeNode head) {

        if (head == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);

        // 当前层，最右节点是谁
        TreeNode curEnd = head;
        // 下一层，最右节点是谁
        TreeNode nextEnd = null;
        // 当前层有多少节点
        int curLevelNodes = 0;
        // 最大节点数
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
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
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }

        }

        return max;


    }


    /**
     * 剑指 Offer II 044. 二叉树每层的最大值
     * <p>
     * https://leetcode.cn/problems/hPov7L/description/
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(newerclass.TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<newerclass.TreeNode> list = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            int max = list.peek().val;
            for (int i = 0; i < size; i++) {
                newerclass.TreeNode pop = list.pop();
                max = Math.max(max, pop.val);
                if (pop.left != null) {
                    list.add(pop.left);
                }
                if (pop.right != null) {
                    list.add(pop.right);
                }
            }
            ans.add(max);


        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(2);

        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(3);

        TreeNode treeNode16 = new TreeNode(9);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = null;
        treeNode3.right = treeNode16;

        widthOfBinaryTree(treeNode1);


    }

    /**
     * 662. 二叉树最大宽度
     * <p>
     * https://leetcode.cn/problems/maximum-width-of-binary-tree/
     *
     * @param root
     * @return
     */
    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int ans = 0;
        LinkedList<Node> queue = new LinkedList<>();
        Node node = new Node(root, 1);
        queue.add(node);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 当前下标是index
            // 当前层的宽度是
            int curLevel = queue.peekLast().index - queue.peekFirst().index + 1;
            ans = Math.max(ans, curLevel);
            for (int i = 0; i < size; i++) {
                Node curNode = queue.poll();
                TreeNode curTreeNode = curNode.treeNode;
                int index = curNode.index;
                if (curTreeNode.left != null) {
                    queue.add(new Node(curTreeNode.left, index << 1));
                }
                if (curTreeNode.right != null) {
                    queue.add(new Node(curTreeNode.right, index << 1 | 1));
                }
            }

        }
        return ans;
    }

    public static class Node {
        TreeNode treeNode;
        int index;

        Node(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }
    

}
