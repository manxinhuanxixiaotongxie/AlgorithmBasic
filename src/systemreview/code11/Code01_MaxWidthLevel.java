package systemreview.code11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 给定一个二叉树 按层进行遍历 求最宽的个数
 */
public class Code01_MaxWidthLevel {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int curLevel = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevel++;

            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }
            if (node == curEnd) {
                ans = Math.max(ans, curLevel);
                curLevel = 0;
                curEnd = nextEnd;
            }
        }
        return ans;
    }

    /**
     * leetcode题目  求最大宽度
     * @param root
     * @return
     */
    public int widthOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int curLevel = 0;
        // 记录每个节点所处的index位置
        Map<TreeNode,Integer> indexMap = new HashMap<>();
        indexMap.put(root,1);
        // 记录每一层开始节点的位置
        Map<Integer,TreeNode> startedMap = new HashMap<>();
        startedMap.put(0,root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
                indexMap.put(node.left,2*indexMap.get(node));
                if (startedMap.get(curLevel+1) == null) {
                    startedMap.put(curLevel + 1, node.left);
                }
            }

            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
                indexMap.put(node.right,2*indexMap.get(node)+1);
                if (startedMap.get(curLevel+1) == null) {
                    startedMap.put(curLevel+1,node.right);
                }
            }
            // 当前层结束
            if (node == curEnd) {
                ans = Math.max(ans, indexMap.get(curEnd) - indexMap.get(startedMap.get(curLevel)) + 1);
                curLevel ++;
                curEnd = nextEnd;
            }
        }
        return ans;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
