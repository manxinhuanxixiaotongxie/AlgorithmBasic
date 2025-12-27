package leetcode.classic150;

public class Code151 {
    public String reverseWords(String s) {
        String trim = s.trim();
        if (trim.length() == 0) {
            return "";
        }
        String[] words = trim.split(" ");

        int left = 0, right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(word).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
