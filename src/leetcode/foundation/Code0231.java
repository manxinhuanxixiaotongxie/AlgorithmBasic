package leetcode.foundation;

public class Code0231 {

    /**
     * 一个数如果是2的某次幂
     * 那么以二进制表达的话 意味着的只有一位是1 其他的位数斗都是0
     * <p>
     * 最后一个1求解方式：
     * int mostRight = n & (~n + 1)
     * 取反再加1
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n - (n & -n) == 0;
    }
}
