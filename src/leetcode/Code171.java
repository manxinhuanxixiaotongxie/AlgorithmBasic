package leetcode;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-07-24 17:00
 */
public class Code171 {

    /**
     * leetcode170测试通过
     * @param args
     */
    public static void main(String[] args) {
        char a = 'A';
        System.out.println(Integer.valueOf(a));
        System.out.println('A');
    }

    public int titleToNumber(String columnTitle) {

        char[] chars = columnTitle.toCharArray();
        int ans = 0;
        for (int i = chars.length-1;i>=0;i--) {
            ans += (chars[i] - 'A' + 1) * Math.pow(26,chars.length-1-i);
        }
        return ans;
    }
}
