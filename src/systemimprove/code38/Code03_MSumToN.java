package systemimprove.code38;

/**
 * 定义一种数：可以表示成若干（数量>1）连续正数和的数
 * 比如:
 * 5 = 2+3，5就是这样的数
 * 12 = 3+4+5，12就是这样的数
 * 1不是这样的数，因为要求数量大于1个、连续正数和
 * 2 = 1 + 1，2也不是，因为等号右边不是连续正数
 * 给定一个参数N，返回是不是可以表示成若干连续正数和的数
 */
public class Code03_MSumToN {

    /**
     * 暴力递归
     * 每一个开头的数字都尝试
     *
     * @param num
     * @return
     */
    public boolean isNums(int num) {
        if (num <= 2) {
            return false;
        }
        for (int i = 1; i < num; i++) {
            if (process(i, num)) {
                return true;
            }
        }
        return false;
    }

    public boolean process(int cur, int rest) {
        if (rest == 0) {
            return true;
        }
        if (rest < 0) {
            return false;
        }
        return process(cur + 1, rest - cur);
    }


    public boolean isNums2(int num) {
        if (num <= 2) {
            return false;
        }
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                if (sum + j > num) {
                    break;
                }
                if (sum + j == num) {
                    return true;
                }
                sum += j;
            }
        }
        return false;
    }

    /**
     * 最终版本 找到一个规律
     *
     * 只要某个数是2的幂次方，那么这个数一定不是这样的数
     * 2的幂次方的数的二进制表示中，只有一个1
     * 2的幂次方减1的二进制表示中，所有的1都变成0，最后一个0变成1
     * 2的幂次方和2的幂次方减1进行与运算，结果一定是0
     * @param num
     * @return
     */
    public boolean isNums3(int num) {
        return (num & (num - 1)) != 0;
    }


    public static void main(String[] args) {
        Code03_MSumToN code03_mSumToN = new Code03_MSumToN();
        int testTimes = 100000;
        for (int i = 1; i < testTimes; i++) {
            boolean ans1 = code03_mSumToN.isNums(i);
            boolean ans2 = code03_mSumToN.isNums2(i);
            boolean ans3 = code03_mSumToN.isNums3(i);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
    }
}
