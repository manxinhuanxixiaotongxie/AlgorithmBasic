package systemimprove.code34;

public class ManacherTest {
    /**
     * manacher算法解决的问题是：在一个字符串中找到最长的回文子串
     */

    public int findLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] newStr = getString(str);
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < newStr.length; i++) {
            // 使用暴力时段进行扩张
            int index = i;
            while (index > 0 && index < newStr.length) {
                if (newStr[index] == newStr[index]) {
                    index++;
                } else {
                    break;
                }
            }
            ans = Math.max(ans, index - i);
        }
        return ans;
    }

    public int findLength2(String str) {
        /**
         * 使用manacher算法解决上述问题
         */

        if (str == null || str.length() == 0) {
            return 0;
        }
        // 加工字符串
        char[] newStr = getString(str);
        // 回文半径数组
        int[] pArr = new int[newStr.length];
        // 回文半径
        int c = 0;
        // 扩张的能到达的最右的位置
        int R = -1;
        // 分三种情况进行讨论
        // 第一种情况
        // i在R外 没有优化措施 只能暴力扩张
        // 第二种情况
        // i在R内
        // 小情况1：i的对称点的回文半径在L内 这个L是到达R情况下的回文半径的长度
        //  这种情况有没有可能会扩大  情况是不会扩大
        /**
         * 证明这种情况：假设能扩大
         * 对称点i'的回文半径的左边界的字符是a 右边界的字符是b
         * i的做左边界为x 右边界是y
         * 因为都在边界L-R内 那么a==y b=b 能扩大的话 x==y 继而证明a==y  a==b
         * 那么i的对称点位置的回文的半径就求错了 长度发生了变化
         */
        // 小情况2：i的对称点的回文半径在L外
        /**
         * 证明这种情况的回文半径是多少
         * i的回文半径的左边界假设是x R+1位置的数是y
         * i的对称点i‘左边界为a 右边界是b 假设能够扩张 那么必然意味着x==y
         * 同时由于对称性 x==b a ==b 继而得到a==y  回文半径的长度会扩大 与预期不符合
         */
        // 小情况3：i的对称点的回文半径刚好在L上 有可能会扩大 需要验证
        /**
         * 分析一下上述三种情况长度的变化
         * 1.i在R内 只能暴力扩张
         * 2.i'在L-R内
         * 3.i'在L外
         * 4.i'在L上
         */
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < newStr.length; i++) {
            // 如果i与R位置相等的话 对称点i'刚好压在L上 需要进行验证才能确认是否能进行扩张
            pArr[i] = R > i ? Math.min(R - i, pArr[2 * c - i]) : 1;
            while (i + pArr[i] < newStr.length && i - pArr[i] > -1) {
                if (newStr[i + pArr[i]] == newStr[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                c = i;
            }
            max = Math.max(max, pArr[i]);

        }
        return max - 1;
    }

    private char[] getString(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] newStr = new char[str.length() * 2 + 1];
        for (int i = 0; i < newStr.length; i++) {
            newStr[i] = (i % 2 == 0) ? '#' : str.charAt(i / 2);
        }
        return newStr;
    }
}
