package leetcode;

public class Code0202 {

    /**
     * 编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」 定义为：
     *
     * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     * 如果这个过程 结果为 1，那么这个数就是快乐数。
     * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     *
     * 示例 1：
     *
     * 输入：n = 19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * 示例 2：
     *
     * 输入：n = 2
     * 输出：false
     *
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        // 获取n的位数
        int bit = 0;
        int temp = n;
        while (temp != 0) {
            temp /= 10;
            bit++;
        }
        int count = 0;
        for (int i = 0; i < bit; i++) {
            int rest = (int) (n % (Math.pow(10, i + 1)));
            count += (int) (rest/(Math.pow(10, i)));
            n = n - rest;
        }
        if (count % 10 == 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 19;
        Code0202 code0202 = new Code0202();
        System.out.println(code0202.isHappy(n));
    }
}
