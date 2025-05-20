package leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Code017 {

    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[][] str = new char[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        char[] dig = digits.toCharArray();
        List<String> ans = new ArrayList<>();
        process(dig, str, 0, "", ans);
        return ans;
    }

    // 深度优先遍历
    private void process(char[] dig, char[][] str, int index, String path, List<String> ans) {
        if (index == dig.length) {
            ans.add(path);
            return;
        }
        for (char cha : str[dig[index] - '2']) {
            // 针对按键的每一个字符进行深度优先遍历
            process(dig, str, index + 1, path + cha, ans);
        }
    }

    public List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[][] str = new char[][]{{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        char[] dig = digits.toCharArray();
        List<String> ans = new ArrayList<>();
        char[] path = new char[dig.length];
        process2(dig, str, 0, path, ans);
        return ans;
    }

    // 深度优先遍历
    private void process2(char[] dig, char[][] str, int index, char[] path, List<String> ans) {
        if (index == dig.length) {
            ans.add(String.valueOf(path));
            return;
        }
        for (char cha : str[dig[index] - '2']) {
            // 针对按键的每一个字符进行深度优先遍历
            path[index] = cha;
            process2(dig, str, index + 1, path, ans);
        }
    }

    public static void main(String[] args) {
        Code017 code017 = new Code017();
        List<String> strings = code017.letterCombinations1("23");
        for (String string : strings) {
            System.out.println(string);
        }
    }

}
