package leetcode.review.newer;

public class Code03 {
    public int smallestEvenMultiple(int n) {
        // 给你一个正整数 返回2和n的最小公倍数
        return (n % 2 == 0) ? n : n << 1;
    }
}
