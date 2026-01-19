package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *
 */
public class Code105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 先序遍历
        // 中序遍历

        // 数值 下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return process(preorder, inorder, map, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    // 构建函数
    public TreeNode process(int[] preorder, int[] inorder, Map<Integer, Integer> map, int lPre, int rPre, int lIn, int rIn) {
        if (lPre > rPre) return null;
        if (lPre == rPre) {
            return new TreeNode(preorder[lPre]);
        }
        TreeNode root = new TreeNode(preorder[lPre]);
        // 在中序遍历中找到根节点的位置
        int index = map.get(root.val);
        // 那么左树的大小
        int leftSize = index - lIn;
        // 右树的大小
        int rightSize = rIn - index;
        root.left = process(preorder,inorder,map,lPre+1,lPre+ leftSize,lIn,index - 1);
        root.right = process(preorder,inorder,map,lPre + leftSize + 1,rPre,index + 1,rIn);
        return root;
    }
}
