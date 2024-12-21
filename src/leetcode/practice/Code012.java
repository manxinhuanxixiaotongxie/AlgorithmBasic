package leetcode.practice;

/**
 * 整数转罗马数字
 *
 * I  1
 *
 * V  5
 *
 * x 10
 *
 * l 50
 *
 * c 100
 *
 * d 500
 *
 * m 1000
 *
 *

 */
public class Code012 {
    static char[] value1 = {'I', 'X', 'C', 'M'};
    static char[] value2 = {'V', 'L', 'D'};

    public String intToRoman(int num) {
        if (num <= 0 || num > 3999) {
            return null;
        }
        /**
         * 想办法得到最高位的数字
         */
        int index = getIndex(num);
        // 得到一个数位信息之后进行处理
        StringBuilder ans = new StringBuilder();
        // 假设一个数字是499
        // 那么index就是2
        while (index >= 0) {
            // 从高位往低位进行处理
            // 高位的数值是
            // 假设当前数字是3749
            // index就是3
            // 获取到这个3
            int now = num / (int) Math.pow(10, index);
            num -= (int) (Math.pow(10, index) * now);
            // 拿到了当前数值是3
            if (now == 4 || now == 9) {
                // 当前数值是4
                if (now == 4) {
                    // 假设数值是499
                    // 当前数值是4
                    // 当前的index == 2
                    if (index == 2) {
                        ans.append("CD");
                    } else if (index == 1) {
                        ans.append("XL");
                    } else if (index == 0) {
                        ans.append("IV");
                    }
                }
                if (now == 9) {
                    // 当前数值是9
                    if (index == 2) {
                        ans.append("CM");
                    } else if (index == 1) {
                        ans.append("XC");
                    } else if (index == 0) {
                        ans.append("IX");
                    }
                }

                // 当前数值是9
            }else {
                // 当前数值不是4 也不是9
                if (now <5) {
                    while (now > 0) {
                        ans.append(value1[index]);
                        now--;
                    }
                }else {
                    now -= 5;
                    ans.append(value2[index]);
                    while (now > 0) {
                        ans.append(value1[index]);
                        now--;
                    }
                }
            }

            index--;
        }
        return ans.toString();

    }

    // 得到一个数的位数
    private int getIndex(int num) {
        int ans = 0;
        while (num != 0) {
            ans++;
            num /= 10;
        }
        return ans - 1;
    }

    public static String intToRoman2(int num) {
        String[][] c = {
                { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" },
                { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" },
                { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" },
                { "", "M", "MM", "MMM" } };
        return c[3][num / 1000 % 10] +
                c[2][num / 100 % 10] +
                c[1][num / 10 % 10] +
                c[0][num % 10];
    }

    public static void main(String[] args) {
        Code012 code012 = new Code012();
        System.out.println(code012.intToRoman(3749));
    }
}
