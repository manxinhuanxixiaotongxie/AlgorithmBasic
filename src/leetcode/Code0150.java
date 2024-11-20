package leetcode;

import java.util.Stack;

public class Code0150 {
    public int evalRPN(String[] tokens) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i]) || "-".equals(tokens[i]) || "*".equals(tokens[i]) || "/".equals(tokens[i])) {
                stack.push(getNum(stack, tokens[i]));
            } else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    /**
     * 使用数组模拟栈
     *
     * @param tokens
     * @return
     */

    private static int index = 0;

    public int evalRPN2(String[] tokens) {
        int ans = 0;
        index = 0;
        int[] stack = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            if ("+".equals(tokens[i]) || "-".equals(tokens[i]) || "*".equals(tokens[i]) || "/".equals(tokens[i])) {
                int num2 = getNum2(stack, tokens[i]);
                stack[index++] = num2;
            } else {
                stack[index++] = Integer.parseInt(tokens[i]);
            }
            // 0 1 2 3 4
        }
        return stack[0];
    }

    /**
     * 使用数组模拟一个栈
     *
     * @param stack
     * @param ops
     * @return
     */
    private int getNum2(int[] stack, String ops) {
        int y = stack[--index];
        int x = stack[--index];
        switch (ops) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
        }
        return 0;
    }


    private int getNum(Stack<Integer> stack, String ops) {
        int y = stack.pop();
        int x = stack.pop();
        switch (ops) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                return x / y;
        }
        return 0;
    }

    public static void main(String[] args) {
        Code0150 code0150 = new Code0150();
        String[] tokens = new String[]{"2", "1", "+", "3", "*"};
        System.out.println(code0150.evalRPN2(tokens));
    }

}
