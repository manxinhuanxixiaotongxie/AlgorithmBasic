package code12;

import newerclass.TreeNode;

import java.util.Comparator;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-25 11:28
 */
public class Code04_ConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length < 1) {
            return null;
        }
        return process(nums, 0, nums.length - 1);
    }

    public TreeNode process(int[] nums, int l, int r) {
        // base case
        if (l > r) {
            return null;
        }


        int index = l;
        for (int i = l + 1; i <= r; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        root.left = process(nums, l, index);
        root.right = process(nums, index + 1, r);

        return root;
    }

    public class MyCom implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}
