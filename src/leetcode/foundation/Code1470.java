package leetcode.foundation;

public class Code1470 {
    public int[] shuffle(int[] nums, int n) {
        int[] helper = new int[2 * n];
        int index = 0;
        int l = 0;
        int r = n;
        while (index <helper.length) {
            if (index %2 == 0) {
                helper[index++] = nums[l++];
            }else {
                helper[index++] = nums[r++];
            }
        }
        return helper;
    }
}
