package leetcode.practice;

public class Code0394 {
    /**
     * 嵌套结构
     * 递归写法
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        return process(s.toCharArray(), 0).ans;
    }

    /**
     * 返回嵌套里面字符串以及嵌套结束的位置
     *
     * @param chars
     * @param index
     * @return
     */
    private Info process(char[] chars, int index) {
        StringBuilder sb = new StringBuilder();
        while (index < chars.length && chars[index] != ']') {
            if (chars[index] >= '0' && chars[index] <= '9') {
                int times = 0;
                while (chars[index] >= '0' && chars[index] <= '9') {
                    times = times * 10 + chars[index] - '0';
                    index++;
                }
                // 上面的循环结束之后index来到了[位置 继续向右走一位 肯定是数字
                index++;
                // 下一个嵌套里面的字符是是什么字符
                Info next = process(chars, index);
                // 最新的字符来到了]的位置 继续向右走一位
                index = next.index + 1;
                // 开始拼接字符
                for (int i = 0; i < times; i++) {
                    sb.append(next.ans);
                }
            } else {
                // 不是数字 不是右括号 是普通字符 直接添加
                sb.append(chars[index++]);
            }
        }
        return new Info(sb.toString(), index);
    }


    class Info {
        String ans;
        int index;

        Info(String ans, int index) {
            this.ans = ans;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Code0394 code0394 = new Code0394();
        System.out.println(code0394.decodeString("3[a]2[bc]"));
    }
}
