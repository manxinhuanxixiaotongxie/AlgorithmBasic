package leetcode.classic150;

/**
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * <p>
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 */
public class Code224 {
    /**
     * 嵌套结构
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        char[] str = s.toCharArray();
        return process(str, 0).value;
    }

    public Info process(char[] str, int index) {
        // 当前子表达式的计算结果
        int res = 0;
        // 临时存储计算中的数字
        int num = 0;
        // 符号 默认+1
        int sign = 1;
        while (index < str.length && str[index] != ')') {
            if (str[index] == ' ') {
                // 跳过空格
                index++;
            } else if (str[index] >= '0' && str[index] <= '9') {
                // 如果是数字 累加
                num = num * 10 + (str[index] - '0');
                index++;
            } else if (str[index] == '(') {
                // 如果是左括号 递归处理括号内的表达式
                Info info = process(str, index + 1);
                num = info.value;
                index = info.index;
            } else if (str[index] == '+' || str[index] == '-') {
                // 如果是运算符 先把之前的数字结算
                res += sign * num;
                num = 0;
                sign = str[index] == '+' ? 1 : -1;
                index++;
            }
        }
        // 处理最后一个数字
        res += sign * num;
        return new Info(res, index + 1);
    }

    class Info {
        int value;
        int index;

        Info(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
