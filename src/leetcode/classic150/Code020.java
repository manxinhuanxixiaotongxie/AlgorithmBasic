package leetcode.classic150;

import java.util.Stack;

public class Code020 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
