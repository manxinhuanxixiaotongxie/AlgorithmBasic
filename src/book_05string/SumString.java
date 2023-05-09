package book_05string;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-08 19:34
 */
public class SumString {

    public static void main(String[] args) {
        String str = "aaabbadddffc";
        System.out.println(sumString(str));
    }

    /**
     * 统计字符出现的次数
     * 给定一个字符串，统计字符出现的次数
     */
    public static String sumString(String str) {
        char[] chars = str.toCharArray();
        int num = 1;
        String newStr = "";
        for (int i = 0; i < chars.length - 1; i++) {

            if (chars[i + 1] == chars[i]) {
                num++;
            } else {
                newStr += chars[i] + "" + num + "_";
                num = 1;
            }

        }
        if (num != 0) {
            newStr += chars[chars.length - 1] + "" + num;
        }

        return newStr;
    }

    /**
     * 进阶问题：
     * 给定一个字符串 再给定一个整数index 返回string所代表原始字符串上的第index个字符
     *
     * @return
     */
    public char getCharIndex(String str, int index) {

        if (str == null || str == "") {
            return 0;
        }

        // a_100_b_10_c_10 index=105
        char[] chars = str.toCharArray();

        boolean needChange = true;
        int sum = 0;
        int num = 0;
        char cur = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '_') {
                needChange = !needChange;
            } else if (needChange) {
                sum += num;
                if (sum > index) {
                    return cur;
                }
                num = 0;
                cur = chars[i];
            } else {
                num = num + 10 + chars[i] - '0';
            }
        }
        // 边界条件，最后一个数字num变了之后没有对sum进行处理
        return sum + num > index ? cur : 0;
    }
}
