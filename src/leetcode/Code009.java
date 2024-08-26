package leetcode;

public class Code009 {
    public boolean isPalindrome(int x) {
        // 获取一个数值的位数
        int maxIndex = getIndex(x);
        int minIndex = 0;
        boolean ans = true;
        int maxValue = x;
        int minVlaue = x;
        while (maxIndex > minIndex) {
            int max = getNum(maxValue, maxIndex);
            int min = minVlaue % 10;
            if (max != min) {
                ans = false;
                break;
            }
            maxValue -= max * (int) Math.pow(10, maxIndex);
            minVlaue /= 10;
            maxIndex--;
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
