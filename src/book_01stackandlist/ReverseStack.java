package book_01stackandlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-10-13 19:14
 */

import java.util.Stack;

/**
 * 实现栈中元素的逆序
 * 只能使用递归函数
 */

/**
 * 实现思路：
 * 手下一个原则：
 * 一次递归并不能完全倒装一个栈
 * 设计两个函数：
 * 第一个函数，找到栈底元素
 * 第二个函数 翻转栈
 * @param <T>
 */
public class ReverseStack<T> {

    public static void main(String[] args) {
        Stack<Integer> testStack = new Stack<>();
        testStack.push(1);
        testStack.push(2);
        testStack.push(3);
//        while (!testStack.isEmpty()) {
//            System.out.println(testStack.pop());
//        }

        getAndRemoveLastElement2(testStack);

        while (!testStack.isEmpty()) {
            System.out.println(testStack.pop());
        }
//        System.out.println(testStack.pop());

    }

    /**
     * 将栈底元素返回并移除
     * @param stack
     * @return
     */
    public T getAndRemoveLastElement(Stack<T> stack) {
        T pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            T t = getAndRemoveLastElement(stack);
            stack.push(pop);
            return t;
        }
    }


    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int last = getAndRemoveLastElement2(stack);
        reverse(stack);
        stack.push(last);

    }

    /**
     * 将栈底元素返回并移除
     * @param stack
     * @return
     */
    public static int getAndRemoveLastElement2(Stack<Integer> stack) {
        int pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        } else {
            int t = getAndRemoveLastElement2(stack);
            stack.push(pop);
            return t;
        }
    }


}
