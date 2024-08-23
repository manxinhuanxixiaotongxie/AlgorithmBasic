package leetcode;


public class Code007 {
    public int reverse(int x) {
        boolean isNeg = x < 0;
        x = isNeg? x : -x;
        int ans = 0;
        int limit = Integer.MIN_VALUE / 10;
        int last = Integer.MIN_VALUE % 10;
        while (x !=0) {
            // 防止溢出
            // 1. ans < limit
            // 一个负数如果比最小值/10还小，那么乘以10之后一定会溢出
            // 2. ans == limit && x % 10 < last
            // 一个负数如果等于最小值/10，那么乘以10之后，如果个位数小于最小值的个位数，那么一定会溢出
            if (ans < limit || (ans == limit && x % 10 < last)) {
                return 0;
            }
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return isNeg ? ans : Math.abs(ans);
    }

    public static void main(String[] args) {
        Code007 solution = new Code007();
        System.out.println(solution.reverse(1534236469));


        System.out.println(Integer.MAX_VALUE *10);

    }
}
