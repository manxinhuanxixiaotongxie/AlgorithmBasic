package book_04recusionanddynamicprogramming;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-04 10:33
 */
public class FibonacciProblem {

    /**
     * F（0）=1
     * F（1）=1
     * F（2）=2
     * F（3）=3
     * 。。。。
     */
    /**
     * 给定整数N 返回斐波那契数列第N项
     *
     * 补充问题1：给定整数N代表台阶数 一次可以跨2个或者1个台阶 返回有多少种走法
     * 补充问题2：假设农场中成熟的母牛每年只会生1头小母牛，并且永远不会死
     * 第一年农场有1头母牛 第二年开始 母牛开始生小母牛
     * 每只小母牛3年之后成熟又可以生小母牛
     * 给定整数N 求出N年后牛的数量
     *
     * 例子：
     * N=6
     * 第一年第一头小母牛记为a 牛总数1
     * 第二年a生了小母牛记为B 牛总数为2
     * 第三年：a生了心的小母牛，记为C  牛总数为3
     * 第四年：a生了心的小母牛记为D 牛总数为4
     * 第五年 b成熟了，a b返回了新的小母牛 牛总数为6
     * 第六年c也成熟了 a b c生了新的小母牛 总牛数为9
     *
     *
     *
     * 对于以上的问题。实现时间复杂度为O（logn）解法
     *
     */

    /**
     * 时间复杂度O（2^n）
     * @param n
     * @return
     */
    public int f1(int n) {
        if (n <=0 ) {
            return 0;
        }
        if (n == 1 || n ==2) {
            return 1;
        }
        return f1(n-1)+f1(n-2);
    }

    /**
     * 时间复杂度O（N）
     * @param n
     * @return
     */
    public int f2(int n) {
        if (n<1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        int pre = 1;
        int res = 1;
        int temp = 0;

        for (int i = 3;i <= n;i++) {
            temp = res;
            res = pre+res;
            pre = res;
        }

        return res;

    }

    /**
     * 牛1
     * O(N^2)
     */
    public int c1(int n) {
        if (n<1) {
            return 0;
        }
        if (n==1 || n ==2 || n == 3) {
            return n;
        }

        return c1(n-1)+c1(n-3);
    }

    /**
     * 牛2
     * O(N)
     */
    public int c2(int n) {
        if (n<1) {
            return 0;
        }
        if (n==1 || n ==2 || n == 3) {
            return n;
        }

        int pre = 2;
        int prePre = 1;
        int temp1 = 0;
        int temp2 = 0;
        int res = 3;
        for (int i =4;i<=n;i++) {
            temp1 = res;
            temp2 = pre;
            res = res + prePre;
            pre = temp1;
            prePre = temp2;
        }
        return res;
    }








}
