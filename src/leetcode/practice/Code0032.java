package leetcode.practice;

import java.util.Stack;

public class Code0032 {

    public int longestValidParentheses(String s) {
        // 使用栈
        // (()())
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int ans = 0;
        stack.push(-1);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                stack.push(i);
            } else {
                // 如果是返括号的话
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        // 如果栈为空，说明之前的都是匹配的
                        stack.push(i);
                    } else {
                        // 如果栈不为空，说明之前的都是匹配的
                        ans = Math.max(ans, i - stack.peek());
                    }
                }

            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code0032 solution = new Code0032();
        System.out.println(solution.longestValidParentheses(")()())"));
    }
}
