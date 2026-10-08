package leetcode.review.newer;

public class Code15 {
    public int maxScore(String s) {
        // 计算两个非空子字符串所能获得的最大得分
        // 左边0的数量 右边1的数量
        int count0 = 0;
        int len = s.length();
        if (len == 0) return 0;
        char[] str = s.toCharArray();
        for (int i = 0; i < len; i++) {
            if (str[i] == '0') count0++;
        }
        // 有了0的数量之后
        int count1 = str.length - count0;
        // 0 1的数量都有了
        // 从左到右进行滑动
        int ans = 0;
        int leftCount0 = 0;
        int rightCount1 = count1;
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '0') {
                // 当前字符是0 那么意味着左边的0的数量要多一个
                leftCount0++;
            } else {
                rightCount1--;
            }
            ans = Math.max(ans, leftCount0 + rightCount1);
        }
        return ans;
    }
}
