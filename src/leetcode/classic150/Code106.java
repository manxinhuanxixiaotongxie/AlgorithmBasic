package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 */
public class Code106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {


        // 中序遍历
        // 后序遍历
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(inorder, postorder, map, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    // 中序 后续
    public TreeNode process(int[] inorder, int[] postorder,Map<Integer,Integer> map,int inL,int inR,int postL,int postR) {
        if (postL > postR) return null;
        if (postL == postR) return new TreeNode(postorder[postR]);
        int index = map.get(postorder[postR]);
        int leftSize = index - inL;
        TreeNode root = new TreeNode(postorder[postR]);
        root.left = process(inorder, postorder, map, inL, index - 1, postL, postL + leftSize - 1);
        root.right = process(inorder, postorder, map, index + 1, inR, postL + leftSize, postR - 1);
        return root;
    }
}
