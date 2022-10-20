package book_01stackandlist;

/**
 * @Description 用一个栈实现另一个栈的排序
 * @Author Scurry
 * @Date 2022-10-19 14:52
 */

import java.util.Stack;

/**
 * 一个栈中的元素的类型为整形
 * 从栈顶到栈底按照从大到小的顺序排序
 * 只允许申请一个栈，可以申请新的变量 不能申请额外的数据结构
 * 完成排序
 */
public class UseStackToOrder {

    private static Stack<Integer> stack;
    private static Stack<Integer> help = new Stack<>();

    public static void main(String[] args) {
        Stack<Integer> data = new Stack<>();
        data.push(2);
        data.push(3);
        data.push(3);
        orderStack1(data);
        System.out.println(data);
        while (!data.isEmpty()) {
            System.out.println(data.pop());
        }
    }

    /**
     * 1
     * 3    2
     * 2    1
     *
     * @param stack
     */
    public static void orderStack1(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            if (help.isEmpty()) {
                help.push(pop);
            }
            while (!help.isEmpty() && (pop > help.peek())) {
                    Integer helpPop = help.pop();
                    stack.push(helpPop);
            }
            help.push(pop);

        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }

    }


    /**
     * 1
     * 3    2
     * 2    1
     *
     * @param stack
     */
    public static void orderStack2(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            while (!help.isEmpty() && (pop > help.peek())) {
                stack.push(help.pop());
            }
            help.push(pop);

        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }

    }


}
