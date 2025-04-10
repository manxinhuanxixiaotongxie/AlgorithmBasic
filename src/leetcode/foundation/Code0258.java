package leetcode.foundation;

/**
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 */
public class Code0258 {

    /**
     * 暴力方法 将数字转化成字符进行转化 直到最后的数字是小于10的
     *
     * @param num
     * @return
     */
    public static int addDigits(int num) {
        String str = String.valueOf(num);
        while (Integer.parseInt(str) >= 10) {
            Integer temp = 0;
            for (int i = 0; i < str.length(); i++) {
                temp += str.charAt(i) - '0';
            }
            str = String.valueOf(temp);
        }
        return Integer.parseInt(str);
    }

    /**
     * 使用暴力方法进行打表
     * 能够得到一个结论
     * 每九个数一组进行输出
     */
    public static int addDigits2(int num) {
        if (num <= 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println("当前的数是:" + i + "," + "转化之后的数是" + addDigits(i));
        }
    }

}
