package leetcode.practice;

public class Code0152 {
    public int maxProduct(int[] nums) {
        // 必须一nums[i]开头的子数组最大乘积
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            max = Math.max(max, temp);
            for (int j = i + 1; j < nums.length; j++) {
                temp *= nums[j];
                max = Math.max(max, temp);
            }
        }
        return max;
    }

//    public int maxProduct2(int[] nums) {
//        // 必须一nums[i]开头的子数组最大乘积
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        int max = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            max = Math.max(max, nums[i]*max);
//        }
//        return max;
//    }

}
