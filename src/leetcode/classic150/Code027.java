package leetcode.classic150;

/**
 * 移除元素
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。
 * 然后返回 nums 中与 val 不同的元素的数量。
 *
 * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
 *
 * 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
 * 返回 k。
 */
public class Code027 {
    public int removeElement(int[] nums, int val) {
        int ans = 0;
        // 双指针
        int n = nums.length;
        int r = n-1;
        for (int i = n-1;i >=0;i--) {
            if (nums[i] == val) {
                // 交换
                swap(nums, i, r--);
                ans++;
            }
        }

        return nums.length-ans;
    }

    private void swap(int[] nums,int l,int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    static void main() {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        Code027 code027 = new Code027();
        int ans = code027.removeElement(nums, val);
        System.out.println(ans);

    }
}
