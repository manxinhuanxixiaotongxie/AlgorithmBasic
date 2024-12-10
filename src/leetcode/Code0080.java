package leetcode;

public class Code0080 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int ans = nums.length;
        int l = 0;
        int r = 0;
        while (r < ans - 1) {
            while (r < ans - 1 && nums[r + 1] == nums[r]) {
                r++;
            }
            if (r < ans && r - l >= 2) {
                // 开始裁减数组
                int temp = r + 1;
                int tempL = l + 2;
                ans -= (r - l - 1);
                for (int i = temp; i < n; i++) {
                    nums[tempL++] = nums[i];
                }
                r = l+1;
            }
            r++;
            l = r;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code0080 solution = new Code0080();
//        int[] nums = {1, 1, 1};
//        int[] nums = {0,0,1,1,1,1,2,3,3};
//        int[] nums = {1, 1, 1, 2, 2, 2, 3, 3};
        int[] nums = {0,1,2,2,2,2,2,3,4,4,4};
        System.out.println(solution.removeDuplicates(nums));
    }
}
