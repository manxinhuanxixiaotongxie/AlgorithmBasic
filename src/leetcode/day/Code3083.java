package leetcode.day;

public class Code3083 {
    public boolean isSubstringPresent(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        // 获取s的翻转串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            stringBuilder.append(s.charAt(i));
        }
        String reverse = stringBuilder.toString();
        boolean ans = false;
        for (int i = 0; i < s.length() - 1; i++) {
            if (reverse.contains(s.substring(i, i + 2))) {
                ans = true;
                break;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        Code3083 code3083 = new Code3083();
        System.out.println(code3083.isSubstringPresent("abcd"));
    }
}
