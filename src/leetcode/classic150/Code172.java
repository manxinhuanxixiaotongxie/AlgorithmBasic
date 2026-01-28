package leetcode.classic150;

/**
 * 进阶：你可以设计并实现对数时间复杂度的算法来解决此问题吗？
 */
public class Code172 {
    public int trailingZeroes(int n) {
        // 一个5代表一个0 但是要注意 25 125 这样的数
        int count = 0;
        int index = 1;
        while (index * 5 <= n) {
            int num = index * 5;
            while (num % 5 == 0) {
                count++;
                num /= 5;
            }
            index++;
        }

        return count;
    }
}
