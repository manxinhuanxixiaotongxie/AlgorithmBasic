package leetcode.foundation;

public class Code1422 {
    public int maxScore(String s) {
        int ans = 0;
        char[] chars = s.toCharArray();
        int count1 = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                count1++;
            }
        }
        int coun0 = 0;

        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length - 1 && chars[chars.length - 1] == '0') {
                continue;
            }
            if (chars[i] == '0') {
                coun0++;
            } else {
                count1--;
            }

            ans = Math.max(ans, count1 + coun0);

        }
        return ans;
    }

    public static void main(String[] args) {
        Code1422 code1422 = new Code1422();
        System.out.println(code1422.maxScore("0100"));
    }
}
