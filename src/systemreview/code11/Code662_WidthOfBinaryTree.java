package systemreview.code11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * <p>
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * <p>
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的
 * null 节点，这些 null 节点也计入长度。
 * <p>
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 */
public class Code662_WidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
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

    class Node {
        TreeNode treeNode;
        int index;

        Node(TreeNode treeNode, int index) {
            this.treeNode = treeNode;
            this.index = index;
        }
    }
}
