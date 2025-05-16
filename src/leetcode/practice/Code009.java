package leetcode.practice;

public class Code009 {
    /**
     * 使用的是进阶解法 不使用整数转成字符串的方式
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        // 获取给定数值的位数
        int maxIndex = getIndex(x);
        // 最后一位数
        int minIndex = 0;
        boolean ans = true;
        int maxValue = x;
        int minVlaue = x;
        // 最大的位数向后移动
        // 最小的位数向左移动
        while (maxIndex > minIndex) {
            // 获取当前位数的数值
            int max = getNum(maxValue, maxIndex);
            // 最后一位数的数值
            int min = minVlaue % 10;
            // 每一位数值进行比较
            if (max != min) {
                ans = false;
                break;
            }
            // 计算结束之后 将最大位数减掉
            maxValue -= max * (int) Math.pow(10, maxIndex);
            // 最小位数移除掉
            minVlaue /= 10;
            // 位数向中间移动
            maxIndex--;
            // 位数向中间移动
            minIndex++;
        }
        return ans;
    }

    private int getNum(int origin, int index) {
        return origin / (int) Math.pow(10, index);
    }

    /**
     * 获取整数的位数
     *
     * @param num
     * @return
     */
    private int getIndex(int num) {
        int index = 0;
        while (num != 0) {
            num /= 10;
            index++;
        }
        return index - 1;
    }

    public static void main(String[] args) {
        Code009 code009 = new Code009();
        System.out.println(code009.isPalindrome(1000021));
    }
}
