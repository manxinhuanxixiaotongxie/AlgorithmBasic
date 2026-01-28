package leetcode.classic150;

public class Code069 {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left <=right) {
            int mid = left + (right - left) / 2;
            long square = (long) mid * mid;
            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    static void main() {
        Code069 code = new Code069();
        int res = code.mySqrt(1085817232);
        System.out.println(res);
    }
}
