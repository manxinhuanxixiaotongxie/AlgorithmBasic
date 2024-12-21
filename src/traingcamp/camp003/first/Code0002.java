package traingcamp.camp003.first;

import java.util.Stack;

/**
 * 括号有效配对是指：
 * 1）任何一个左括号都能找到和其正确配对的右括号
 * 2）任何一个右括号都能找到和其正确配对的左括号
 * 有效的：    (())  ()()   (()())  等
 * 无效的：     (()   )(     等
 * 问题一：怎么判断一个括号字符串有效？
 * 问题二：如果一个括号字符串无效，返回至少填几个字符能让其整体有效
 */
public class Code0002 {

    /**
     * 判断一个字符串是否有效
     */

    public boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        // 使用栈
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (char cha : chars) {
            if (cha == '(') {
                stack.push(')');
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 使用count模拟栈
     *
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        // 使用栈
        int count = 0;
        char[] chars = s.toCharArray();
        for (char cha : chars) {
            if (cha == '(') {
                count++;
            } else {
                count--;
                if (count < 0) {
                    return false;
                }
            }
        }
        return count == 0;
    }

    /**
     * 最少补几个括号能让括号字符串有效
     *
     * @param s
     * @return
     */
    public static int needParentheses(String s) {
        // 使用栈进行处理
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int ans = 0;
        for (char cha : chars) {
            if (cha == '(') {
                stack.push(cha);
            } else {
                // 如果是)
                if (stack.isEmpty()) {
                    ans++;
                } else {
                    stack.pop();
                }
            }
        }
        return ans + stack.size();
    }

    /**
     * 使用数字来模拟栈
     *
     * @param s
     * @return
     */
    public static int needParentheses2(String s) {
        // 使用栈进行处理
        char[] chars = s.toCharArray();
        int count = 0;
        int need = 0;
        for (char cha : chars) {
            if (cha == '(') {
                count++;
            } else {
                // 如果是)
                if (count == 0) {
                    need++;
                } else {
                    count--;
                }
            }
        }
        return count + need;
    }

}
