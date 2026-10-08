package leetcode.review.newer;

public class Code09 {
    public int subtractProductAndSum(int n) {
        int mul = 1;
        int sum = 0;
        while (n != 0) {
            int cur = n % 10;
            mul = mul * cur;
            sum = sum + cur;
            n /= 10;
        }
        return mul - sum;
    }
}
