package leetcode.classic150;

import java.util.Stack;

/**
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 * <p>
 * 请你计算该表达式。返回一个表示表达式值的整数。
 * <p>
 * 注意：
 * <p>
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 */
public class Code150 {
    public int evalRPN(String[] tokens) {

        int ans = 0;
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 + num2;
                stack.push(String.valueOf(res));
            } else if ("-".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 - num2;
                stack.push(String.valueOf(res));
            } else if ("*".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 * num2;
                stack.push(String.valueOf(res));
            } else if ("/".equals(tokens[i])) {
                // 弹出两个数进行计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = num1 / num2;
                stack.push(String.valueOf(res));
            } else {
                stack.push(tokens[i]);
            }
        }

        return Integer.parseInt(stack.pop());
    }
}
