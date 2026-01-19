package leetcode.classic150;

import java.util.Stack;

public class MinStack {
    Stack<Integer> dataStack ;
    Stack<Integer> minStack ;
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        if (minStack.isEmpty()) {
            dataStack.push(val);
            minStack.push(val);
        }else {
            dataStack.push(val);
            minStack.push(Math.min(minStack.peek(), val));
        }

    }

    public void pop() {
        // 弹出
        int ans = dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
