package leetcode.week.code427;

public class Code001 {
    public int smallestNumber(int n) {
        // 5 101
        // 10 1010 1111
        // 0011 0011
        // 给你一个正整数n
        // 返回大于等于n且二进制表示仅包含置位位的最小整数X
        // 移动位数
        int bit = 0;
        int ans = n;
        int temp = 0;
        while (temp < n) {
            if ((n & (1 << bit)) == 0) {
                ans += 1 << bit;
            } else {
                temp += 1 << bit;
            }
            bit++;
        }

        return ans;
    }

    public static void main(String[] args) {
        Code001 solution = new Code001();
        System.out.println(solution.smallestNumber(5));
        System.out.println(solution.smallestNumber(10));
    }
}
