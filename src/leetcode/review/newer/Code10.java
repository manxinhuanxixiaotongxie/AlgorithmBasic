package leetcode.review.newer;

public class Code10 {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n == (n & (~n + 1)));
    }
}
