package systemimprove.code23;

/**
 * 假设字符串str长度为N，字符串match长度为M，M <= N
 * <p>
 * 想确定str中是否有某个子串是等于match的。
 * <p>
 * 时间复杂度O(N)
 */
public class Code01_KMP {
    /**
     * kmp 算法
     * 找到str中第一次出现match的位置
     * 1.暴力解法
     * 遍历str中每一个位置，看是否以这个位置开头的字符串是不是以match开头
     * <p>
     * 前面的位置已经计算过并不能给后面的数提供参数 整个流程的时间复杂度为O(N*M)
     * <p>
     * 2.使用kmp算法 能达到时间复杂度O(N)
     * <p>
     * <p>
     * <p>
     * 介绍两个概念：
     * 针对match数组 求出next数组  next数组 、
     * next[i]的含义：不包括当前i字符 在i之前的字符串中，有最大长度的相同前缀后缀的长度是多少
     * 举个例子：
     * abcdabce   next[7] = 3  前缀最长是0-2位置的abc 后缀最长是4-6位置的abc 不能包含0-i-1位置的所有字符
     * 有了这个字符数组之后 我们我就可以进行接下来的流程
     * 流程如下：
     * 有了next数组之后 我们能够得到next[i]位置 即不包括i位置的最长前缀最长后缀的长度
     * 既然要加速 我们就在next[i]数组上面想办法
     * 举个例子：
     * 我们现已经知道next[20]=7 那么对应过来就是0-6位置的字段串与13-19位置是相等的
     * 对应回str1位置字符串 我们下一步 就是让str1的13位置去对应macth的开头位置继续进行匹配
     * 跳过原str1的0-13的位置 将原str1的13位置对接上MACTH的0位置 并且对于str2来说就是是从next[i]+1位置与原str1进行匹配
     * <p>
     * 为什么可以加速： 就是因有了next数组之后 原数组的0-（macth.length - next[i]）位置都是可以直接跳过的
     * <p>
     * 现在进行证明的这件事情：
     * 做一种假设 假设 从0-（macth.length - next[i]）位置中间的某个位置K能匹配出MACTH字符串的话
     * 那么我们很容易得到 从k-macth字符串与str1第一个字符串中间的任何一个字符是相等的
     * 这样的话 就意味着我们在0-i位置找到了一个闭next[i]更长相同前缀后缀 这与我们约定的next数组的定义是不相符合的
     * 因此从0-（macth.length - next[i]）位置中间的任何一个位置都是不能匹配出MACTH字符串的
     * 因此 我们直接可以从j位置开始进行匹配
     *
     * @param str
     * @param match
     * @return
     */
    public int indexOf1(String str, String match) {
        if (str == null || match == null || str.length() < match.length()) {
            return -1;
        }
        if (match.isEmpty()) {
            return 0;
        }
        char[] str1 = str.toCharArray();
        char[] str2 = match.toCharArray();
        int ans = -1;
        for (int i = 0; i < str1.length; i++) {
            // 遍历str1的每一个开头的位置 检查是否能找到与match匹配
            int x = i;
            int j = 0;
            // 从i位置开始，str1和str2匹配
            boolean find = true;
            while (x < str1.length && j < str2.length) {
                // 在遍历的过程中 如果找到了一个字符不相等 说从从i开始的位置并不能匹配出match字段
                if (str1[x++] != str2[j++] || x == str1.length && j < str2.length) {
                    find = false;
                    break;
                }
            }
            // 当while循环结束时有两种情况
            // 匹配成功
            // 匹配不成功
            if (find) {
                ans = i;
                break;
            }
        }
        return ans;
    }

    public int indexOf2(String str, String match) {
        if (str == null || match == null || str.length() < match.length()) {
            return -1;
        }
        if (match.isEmpty()) {
            return 0;
        }

        int[] next = getNext(match);
        /**
         * 按照之前介绍的流程
         * 获取得到了next数组之后
         * 比如next[0]位置是2 macth的长度是5 下标是0-4  那么意味着 0-1位置的字符与3-4位置的字符是相等的
         */
        char[] str1 = str.toCharArray();
        char[] str2 = match.toCharArray();
        int x = 0;
        int y = 0;
        while (x < str1.length && y < str2.length) {
            // 如果字符相等的话 那么将下标对应str1 str2的位置都向后推
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }

        }
        return y == str2.length ? x - y : -1;
    }

    /**
     * next数组含义
     * 1.不包含当前字符
     * 2.不能包含前面的所有字符
     * 满足上述两个条件的最长的相同前缀后缀的长度
     * next数组的求解
     * 1.暴力解法
     * 对每个位置进行遍历 找到最长的公共前后缀
     * <p>
     * 2.优化
     * 能不能找到一种优化思路  前面已经求出来的next[]的数组能不能帮助我们求解后面的next[]数组
     * 1.第一种情况 如果已经求出来了next[i-1]位置的值是y的话  如果str2[i-1] == str2[y]的话 那么next[i] = y+1
     * 2.第二种情况 如果已经求出来了next[i-1]位置的值是y的话  如果str2[i-1] != str2[y]的话 那么我们就需要找到next[y]的值 然后将next[y]的值与str2[i-1]进行比较
     *
     * @param match
     * @return
     */
    public int[] getNext(String match) {
        int[] next = new int[match.length()];
        char[] str2 = match.toCharArray();
        if (str2.length == 1) {
            return new int[]{-1};
        }
        next[0] = -1;
        next[1] = 0;
        for (int i = 2; i < next.length; i++) {
            int x = i - 1;
            while (next[x] != -1) {
                if (str2[i - 1] == str2[next[x]]) {
                    next[i] = next[x] + 1;
                    break;
                } else {
                    x = next[x];
                }
            }
        }
        return next;
    }

    /**
     * 获取next数组 更优秀的写法
     * @param match
     * @return
     */
    public int[] getNextArray(String match) {
        int[] next = new int[match.length()];
        char[] str2 = match.toCharArray();
        if (str2.length == 1) {
            return new int[]{-1};
        }
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        // cn变量的含义
        // cn是i位置的下标 同时也是i位置的最长前缀后缀的长度
        int cn = 0;
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public String generateString(int maxLength, int maxValue) {
        int len = (int) (Math.random() * maxLength);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * maxValue) + 'a');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int str1MaxLen = 20;
        int str2MaxLen = 5;
        int maxValue = 5;
        int testTimes = 1000000;
        Code01_KMP test = new Code01_KMP();
        for (int i = 0; i < testTimes; i++) {
            String str1 = test.generateString(str1MaxLen, maxValue);
            String str2 = test.generateString(str2MaxLen, maxValue);
            str1 = "abcdabcd";
            str2 = "abcd";
            if (test.indexOf1(str1, str2) != str1.indexOf(str2) || test.indexOf2(str1, str2) != str1.indexOf(str2)) {
                System.out.println("Oops!");
            }
        }
    }
}
