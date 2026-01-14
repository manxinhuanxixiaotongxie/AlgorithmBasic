package leetcode.classic150;

public class Code125 {
    public boolean isPalindrome(String s) {
        String lowerCase = s.toLowerCase();
        char[] str = lowerCase.toCharArray();

        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (!isChar(str[left])){
                left++;
            }else if (!isChar(str[right])){
                right--;
            }else if (str[left] == str[right]){
                left++;
                right--;
            }else {
                return false;
            }
        }
        return true;
    }

    public boolean isChar(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }
}
