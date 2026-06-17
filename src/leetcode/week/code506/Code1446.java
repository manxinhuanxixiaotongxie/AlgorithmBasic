package leetcode.week.code506;

/**
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * <p>
 * 请你返回字符串 s 的 能量。
 *
 */
public class Code1446 {
    public int maxPower(String s) {
        char[] arr = s.toCharArray();
        int ans = 0;
        int n = arr.length;
        int same = 0;
        for (int i = 0; i < n; i++) {
            same++;
            if (i == n - 1 || arr[i] != arr[i + 1]) {
                ans = Math.max(ans, same);
                same = 0;
            }
        }
        return ans;
    }

    public int maxPower2(String s) {
        char[] arr = s.toCharArray();
        int ans = 0;
        int n = arr.length;
        for (int i = 0; i < n; ) {
            int start = i;
            while (start < n - 1 && arr[start + 1] == arr[start]) {
                start++;
            }
            ans = Math.max(ans, start - i + 1);
            i = start + 1;
        }
        return ans;
    }
}
