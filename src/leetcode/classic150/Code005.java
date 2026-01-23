package leetcode.classic150;

public class Code005 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        char[] str = s.toCharArray();
        String longestStr = s.substring(0,1);
        for (int i = 1;i < str.length -1;i++) {
            int left = i-1;
            int right = i+1;
            while (left >=0 && right < str.length) {
                if (str[left] == str[right]) {
                    left--;
                    right++;
                }else {
                    break;
                }
            }
            // 子串
            if (right - left  > longestStr.length()) {
                longestStr = s.substring(left + 1, right);
            }
        }

        for (int i = 1;i < str.length;i++) {
            int left = i-1;
            int right = i;
            while (left >=0 && right < str.length) {
                if (str[left] == str[right]) {
                    left--;
                    right++;
                }else {
                    break;
                }
            }
            // 子串
            if (right - left  > longestStr.length()) {
                longestStr = s.substring(left + 1, right);
            }
        }
        return longestStr;
    }

    static void main() {
        String s = "a";
        Code005 code005 = new Code005();
        String res = code005.longestPalindrome(s);
        System.out.println(res);
    }
}
