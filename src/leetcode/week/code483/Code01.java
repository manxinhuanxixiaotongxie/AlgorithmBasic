package leetcode.week.code483;

public class Code01 {
    /**
     * 给你一个仅由字符'1'和'2'组成的字符串s。
     *
     * 你可以删除字符串s中的任意数量的字符，但必须保持剩余字符的顺序不变。
     *
     * 返回可以表示 偶数 整数的 最大结果字符串 。如果不存在这样的字符串，则返回空字符串""。©leetcode
     * @param s
     * @return
     */
    public String largestEven(String s) {

        char[] arr = s.toCharArray();
        int index = arr.length - 1;
        while (index >=0) {
            // 获取字符
            if (arr[index] == 49) {
                index--;
            }else {
                break;
            }
        }
        if (index == -1) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <=index; i++) {
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "221";
        Code01 c = new Code01();
        System.out.println(c.largestEven(s));
    }
}
