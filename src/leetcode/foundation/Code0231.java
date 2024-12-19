package leetcode.foundation;

public class Code0231 {

    public boolean isPowerOfTwo(int n) {
        return n- (n & -n) == 0;
    }
}
