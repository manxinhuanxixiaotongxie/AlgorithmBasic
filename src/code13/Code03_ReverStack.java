package code13;

import java.util.Stack;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-07-02 15:31
 */
public class Code03_ReverStack {

    /**
     * 逆序一个栈，只使用递归
     */
    public static void reversStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        //获取栈最底部的数值
        int bottomResult = getBottomResult(stack);
        reversStack(stack);
        stack.push(bottomResult);
    }

    public static int getBottomResult(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int bottomResult = getBottomResult(stack);
        stack.push(result);
        return bottomResult;
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reversStack(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }
}
