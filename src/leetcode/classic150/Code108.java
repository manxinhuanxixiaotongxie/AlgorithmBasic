package leetcode.classic150;

/**
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 *
 */
public class Code108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return process(nums, 0, nums.length - 1);
    }

    // 构建二叉平衡搜索树
    public TreeNode process(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }
        // 求中点
        int mid = l + ((r - l) >> 1);
        // 构建头节点
        TreeNode head = new TreeNode(nums[mid]);
        head.left = process(nums, l, mid - 1);
        head.right = process(nums, mid + 1, r);
        return head;
    }
}
