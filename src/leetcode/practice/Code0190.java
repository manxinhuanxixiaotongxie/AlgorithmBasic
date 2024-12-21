package leetcode.practice;

public class Code0190 {
    public int reverseBits(int n) {
        int ans = 0;
        int bit = 0;
        int temp = 1;
        while (bit <= 32) {
            if ((n & temp) != 0) {
                ans |= 1 << (31 - bit);
            }
            bit++;
            temp <<= 1;
        }
        return ans;
    }
}
