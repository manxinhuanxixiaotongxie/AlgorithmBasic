package leetcode.practice;

public class Code0191 {
    public int hammingWeight(int n) {
        int ans = 0;
        int temp = 1;
        int bit = 0;
        while (bit <= 32) {
            if ((n & temp) != 0) {
                ans++;
            }
            temp <<= 1;
            bit++;
        }
        return ans;
    }

    public int hammingWeight2(int n) {
        int ans = 0;
        while (n != 0) {
            int temp = n & (~n + 1);
            if ((temp & 1) != 0) {
                ans++;
            }
            n >>= 1;
        }
        return ans;
    }
}
