package leetcode.classic150;

public class Code137 {

    public int singleNumber(int[] nums) {
        int[] help = new int[32];
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & (1 << i)) != 0) {
                    help[i]++;
                }
            }
        }

        for (int i = 0; i < 32; i++) {
            if (help[i] % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
