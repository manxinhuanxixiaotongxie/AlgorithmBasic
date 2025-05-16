package systemimprove.code09;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code05_FindNextNode {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 二叉搜索树的特性左树比当前节点小 右树比当前节点大
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        // 后继节点 中序遍历的下一个节点
        if (p == null) {
            return null;
        }

        if (p.right != null) {
            return getLeftestNode(p.right);
        }
        // 二叉搜索树 左边的数比当前值要小 右边的数比当前值要大
        // 中序遍历的结果是一个从小到大的有序数组
        // 从头部位置开始遍历的话 如果当前值比p小 那么后继节点一定在右边
        TreeNode cur = root;
        TreeNode ans = null;
        while (cur != null) {
            if (cur.val > p.val) {
                ans = cur;
                cur = cur.left;
            } else if (cur.val < p.val) {
                cur = cur.right;
            } else {
                break;
            }
        }
        return ans;
    }

    /**
     * 暴力解法
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        // 后继节点 中序遍历的下一个节点
        if (p == null) {
            return null;
        }
        List<TreeNode> ansList = new ArrayList<>();
        process(root, ansList);
        for (int i = 0; i < ansList.size(); i++) {
            if (ansList.get(i) == p) {
                return i == ansList.size() - 1 ? null : ansList.get(i + 1);
            }
        }
        return null;

    }

    public void process(TreeNode node, List<TreeNode> ansList) {
        if (node == null) {
            return;
        }
        process(node.left, ansList);
        ansList.add(node);
        process(node.right, ansList);

    }

    /**
     * 使用栈的形式
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        // 后继节点 中序遍历的下一个节点
        if (p == null) {
            return null;
        }
        List<TreeNode> ansList = new ArrayList<>();
        in(root, ansList);
        for (int i = 0; i < ansList.size(); i++) {
            if (ansList.get(i) == p) {
                return i == ansList.size() - 1 ? null : ansList.get(i + 1);
            }
        }
        return null;

    }

    public List<TreeNode> in(TreeNode root, List<TreeNode> ansList) {
        if (root == null) {
            return null;
        }

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        // 中序遍历 左头右
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                // 中序遍历
                ansList.add(cur);
                cur = cur.right;

            }
        }
        return ansList;
    }


    public TreeNode getLeftestNode(TreeNode node) {

        if (node == null) {
            return null;
        }
        TreeNode ans = node;
        TreeNode left = node.left;
        while (left != null) {
            ans = left;
            left = left.left;
        }
        return ans;

    }
}
