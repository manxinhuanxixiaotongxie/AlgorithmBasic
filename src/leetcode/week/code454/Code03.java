package leetcode.week.code454;

public class Code03 {
    public long maximumProduct(int[] nums, int m) {
        long ans = Long.MIN_VALUE;
        int mn = Integer.MAX_VALUE;
        int mx = Integer.MIN_VALUE;
        for (int i = m - 1; i < nums.length; i++) {
            // 维护左边 [0,i-m+1] 中的最小值和最大值
            int y = nums[i - m + 1];
            mn = Math.min(mn, y);
            mx = Math.max(mx, y);
            // 枚举右
            long x = nums[i];
            ans = Math.max(ans, Math.max(x * mn, x * mx));
        }
        return ans;
    }

    // 在0-index范围上选m个数 组成子序列 最大首尾乘机是多少
//    public int process(int[] nums,int index,int rest) {
//        if (index == 0) {
//            if (rest == 0) {return 1;}
//            return Integer.MIN_VALUE ;
//        }else {
//            int ans = Integer.MIN_VALUE;
//            // 当前位置要跟不要
//            ans = Math.max(process(nums,index-1,rest),nums[index] * process(nums,index-1,rest-1));
//            return ans;
//        }
//    }
}
