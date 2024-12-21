package traingcamp.camp003.first;

import java.util.Map;
import java.util.Stack;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 返回一个括号字符串中，最长的括号有效子串的长度
 */
public class Code0003 {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 使用栈
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                // 如果是)
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }else {
                    ans = Math.max(ans, i - stack.peek());
                }
            }
        }

        return ans;
    }
}
