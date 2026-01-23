package leetcode.classic150;

public class Code066 {
    public int[] plusOne(int[] digits) {
        int[] ans = new int[digits.length + 1];
        int add = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int curNum= digits[i] + add;
            if (curNum >= 10) {
                add = 1;
                curNum -= 10;
            }else {
                add = 0;
                curNum = curNum;
            }
            ans[i + 1] = curNum;
        }
        if (add == 1) {
            ans[0] = 1;
            return ans;
        } else {
            int[] res = new int[digits.length];
            System.arraycopy(ans, 1, res, 0, digits.length);
            return res;
        }
    }
}
