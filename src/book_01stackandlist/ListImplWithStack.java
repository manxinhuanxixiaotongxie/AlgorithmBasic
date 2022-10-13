package book_01stackandlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-10-13 9:07
 */

import java.util.Stack;

/**
 * 编写一个类 用两个栈实现队列
 * 支持队列的基本操作add poll peek
 */
public class ListImplWithStack {

    public class MyList<T> {
        private Stack<T> dataStack;
        private Stack<T> helpStack;
        MyList () {
            dataStack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void add(T t) {
            dataStack.push(t);
            if (helpStack.isEmpty()) {
                while (!dataStack.isEmpty()) {
                    helpStack.push(dataStack.pop());
                }
            }
        }

        public T poll() {
            if (helpStack.isEmpty()) {
                while (!dataStack.isEmpty()) {
                    helpStack.push(dataStack.pop());
                }
            }
            return helpStack.pop();

        }

        public T peek() {
            if (helpStack.isEmpty()) {
                while (!dataStack.isEmpty()) {
                    helpStack.push(dataStack.pop());
                }

            }
            return helpStack.peek();

        }
    }
}
