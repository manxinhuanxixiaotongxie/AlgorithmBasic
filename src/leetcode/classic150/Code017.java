package leetcode.classic150;

import java.util.List;

public class Code017 {
    private static final char[][] help = {
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new java.util.ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        char[] digStr = digits.toCharArray();
        // 使用char数组更快
        process(digStr, 0, res, new char[digStr.length]);
        return res;
    }

    public void process(char[] digStr, int index, List<String> res, char[] path) {
        if (index == digStr.length) {
            res.add(String.valueOf(path));
            return;
        }
        int digit = digStr[index] - '0';
        char[] letters = help[digit];
        for (char letter : letters) {
            path[index] = letter;
            process(digStr, index + 1, res, path);
        }
    }
}
