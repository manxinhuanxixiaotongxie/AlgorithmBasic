package book_01stackandlist;

import java.util.Stack;

/**
 * @Description 设计一个又有getMin功能的栈
 * 特殊的栈 实现栈的基本功能，又实现返回栈中最小元素的功能
 * @Author Scurry
 * @Date 2022-10-12 19:48
 */
public class GetMinStack {

    public class MyStack {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        MyStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public Stack<Integer> getDataStack() {
            return dataStack;
        }

        public void push(Integer num) {
            if (minStack.isEmpty()) {
                minStack.push(num);
                dataStack.push(num);
            } else {
                Integer peek = getMin();
                if (peek > num) {
                    minStack.push(num);
                } else {
                    minStack.push(peek);
                }
                dataStack.push(num);
            }
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                System.out.println("stack is empty");
                return 0;
            } else {
                return minStack.peek();
            }

        }

        public int pop() {
            minStack.pop();
            return dataStack.pop();
        }
    }

    public class MyStack2 {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        MyStack2() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public Stack<Integer> getDataStack() {
            return dataStack;
        }

        public void push(Integer num) {
            if (minStack.isEmpty()) {
                minStack.push(num);
            } else {
                Integer peek = getMin();
                if (peek >= num) {
                    minStack.push(num);
                }
            }
            dataStack.push(num);
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                System.out.println("stack is empty");
                return 0;
            } else {
                return minStack.peek();
            }

        }

        public int pop() {
            Integer pop = dataStack.pop();
            if (pop == getMin()) {
                minStack.pop();
            }
            return pop;
        }
    }

}
