package systemimprove.code24;

/**
 * 假设字符串str长度为N，想返回最长回文子串的长度
 * <p>
 * 时间复杂度O(N)
 * <p>
 * manacher算法是1975年manacher发现的
 */
public class Code01_Manacher {

    // 求最长回文子串的长度

    /**
     * 注意：实际上 下面介绍的暴力解法以及manacher解法在生成新的字符串的过程中 不管添加什么字符都不会影响最终的结果
     * 因为对新串来说 并不会出现新增的字符与原始字符进行比较的情形
     * <p>
     * 1.暴力解法
     * 1.将字符串加工一个 在每个字符的前后都加一个特殊的字符
     * 2.遍历index位置 以i位置为基准 向左右两边扩展
     * 3.找到最大的回文子串
     * <p>
     * 2.manacher算法流程：
     * 先介绍几个概念：
     * 1.回文半径：举个例子ababc   回文半径就是abc 长度是3
     * 2.回文直径：举个例子ababc  回文直径就是ababc 长度是5
     * 3.最右侧R：回文半径最右侧的位置  之前遍历的所有回文半径中，最右即将到达的位置
     * 4.取得最右回文边界的中心点C 最后一次更新R时 回文中心的位置
     * <p>
     * 流程：
     * 1，将原始字符串加工成一个新的字符串 在每个字符前后都加上一个特殊的字符
     * 2.R一开始在-1的位置（以下i‘都是在中心点求得的）
     * 1.如果i在R的外面 以i位置为中心 向左右两边扩展 没有优化 只能使用暴力手段向右扩展
     * 2.如果i的R内部 有两种情况
     * 1.i位置的对称点i'的回文半径在L-R的范围内  那么i的回文半径就是i'的回文半径
     * i'位置的左侧为a 右侧为b   i左侧是x 右侧是y a==y
     * 假设能扩大 那么 x==y 由于是关于C对称 那么 b=x 得到 a=b 那么就意味着 之前i'的回文半径求错了 与预期不符合
     * <p>
     * 2.i位置的对称点i'的回文半径超过L 那么i的回文半径就是R-i的距离
     * 如果对称点i'的回文半径超过L  那么i的回文半径至少是R-i的距离 为什么不能变的更大呢？
     * 假设能变变得更大 i左边的字符为a i'右侧的字符为 b i的左侧位置是x R的右侧位置是y
     * 在这样的假设下 能够得到x ==y 由于 L R关于C对称  那么 x必然等于b  i'的回文半径超出了L的范围 那么 a= b
     * 继而退出a==y 但是这很明不成立  如果 a==y的话 那么开始的时候的LR范围就会扩大 与预定不符合
     * <p>
     * 3.i位置的对称点i'的回文半径在L上
     * i位置的回文半径最小是R-i 有没有可能会扩大
     * 有可能扩大 需要验证
     *
     * @param s
     * @return
     */
    public int manacher(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        // 回文半径
        int c = 0;
        // 在C作为回文半径的情况下  能够到达的最右侧的位置R
        int R = -1;
        // 此处的str已经是处理串了
        char[] str = getDouble(s);
        int[] pArr = new int[str.length];
        int max = Integer.MIN_VALUE;
        // 总共有三种情况
        // i在R外  暴力扩大
        // i在R内  i'在L内
        // i在R内  i'在L外
        // i在R内  i'的回文刚好在L‘上 有可能能扩大 需要验证

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * c - i], R - i) : 1;
            // 有可能存在暴力扩的情况
            // while(i >0 && R < strArr.length) {
            //if(strArr[i--] == strArr[R++]) {
            //pArr[i]++;
            //}else {
            // break;
            // }
            //}
            // 上述写法是有问题的 i位置不能回退
            // 分析一下 什么情况下会扩大范围
            // 第一种情况下 i在R外的时候  对比的位置就是前后位置  就是i-1 i+1  上面的时候i在R外已经处理
            // 第二种情况下 i在R内 但是i'刚好压在了L位置上 当左右位置相等的时候能够扩大 对比的位置是i+pArr[i] i-pArr[i]
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            /**
             * R 与 c同步更新
             * 当R能够被扩大的时候 就进行扩大
             */
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                c = i;
            }
            max = Math.max(max, pArr[i]);
        }
        // 处理串之后的回文半径的长度-1就是原始串的回文的长度
        return max - 1;
    }


    public static char[] getDouble(String str) {
        if (str == null || str.isEmpty()) {
            return new char[0];
        }
        char[] newStr = new char[str.length() * 2 + 1];
        for (int i = 0; i < newStr.length; i++) {
            newStr[i] = (i & 1) == 0 ? '#' : str.charAt(i >> 1);
        }
        return newStr;
    }

    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    // for test 暴力解法 作为对数器
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = getDouble(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int size = 10;
        int testTime = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str = getRandomString(possibilities, size);
            if (right(str) != new Code01_Manacher().manacher(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }


}
