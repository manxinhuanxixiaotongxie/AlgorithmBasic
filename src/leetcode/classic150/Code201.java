package leetcode.classic150;

/**
 * 所以有数字按位与
 */
public class Code201 {
    public int rangeBitwiseAnd(int left, int right) {
        int m = 32 - Integer.numberOfLeadingZeros(left ^ right);
        return left & ~((1 << m) - 1);
    }
}
