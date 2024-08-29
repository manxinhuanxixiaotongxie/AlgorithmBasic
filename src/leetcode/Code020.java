package leetcode;

import java.util.Stack;

public class Code020 {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        // 使用栈
        // 流程
        /**
         * 1. 遍历字符串
         * 遇到左括号 入栈
         * 遇到右括号 出栈
         */
        boolean ans = true;
        Stack<Character> stack = new Stack<>();
        char[] chas = s.toCharArray();
        for (char cha : chas) {
            if (cha == '(' || cha == '[' || cha == '{') {
                stack.push(cha);
            } else {
                if (stack.isEmpty() || stack.peek() == '(' && cha != ')' || stack.peek() == '[' && cha != ']' || stack.peek() == '{' && cha != '}') {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
