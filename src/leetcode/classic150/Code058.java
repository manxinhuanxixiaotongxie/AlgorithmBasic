package leetcode.classic150;

/**
 * 给你一个字符串 s ，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。
 *
 * 单词 是指仅由字母组成、不包含任何空格的最大子字符串。
 */
public class Code058 {
    public int lengthOfLastWord(String s) {
        String[] split = s.split("\\ ");
        int len = split.length;
        for (int i = len - 1; i >= 0; i--) {
            // 判断字符串是否全是空格
            if(split[i].contains(" ")) {
                continue;
            }
            return split[i].length();
        }
        return 0;
    }

    static void main() {
        Code058 code058 = new Code058();
        int len = code058.lengthOfLastWord("luffy is still joyboy");
        System.out.println(len);
    }
}
