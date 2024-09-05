package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code022 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        char[] chars = new char[]{'(', ')'};
        process(chars, 0, new char[2 * n], ans);
        return ans;
    }

    private void process(char[] chars, int index, char[] path, List<String> ans) {
        if (index == path.length) {
            if (isValid(path)) {
                ans.add(new String(path));
            }
        } else {
            for (int i = 0; i < chars.length; i++) {
                path[index] = chars[i];
                process(chars, index + 1, path, ans);
            }
        }
    }

    private boolean isValid(char[] path) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < path.length; i++) {
            if (path[i] == '(') {
                stack.push(path[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    Character pop = stack.pop();
                    if (pop != '(') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();

    }

    private boolean isValid2(char[] path) {
        int count = 0;
        for (char cha : path) {
            if (cha == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;

    }
}
