package systemlearning.code02;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-09-14 19:45
 */
public class Code01_ExclusiveOrOperation {

    public void swap(int i, int j) {
        int temp = i;
        i = j;
        j = temp;
    }

    public void swap2(int i, int j) {
        i = i ^ j;
        j = i ^ j;
        i = i ^ j;
    }

    /**
     * 有一个数组 只有一个数出现了奇数次 其他数都出现了偶数次
     *
     * @param arr
     * @return
     */
    public int findNum(int[] arr) {
        if (arr == null) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i];
        }
        return ans;
    }

    /**
     * 找到最后一个数的二进制中最右边的1
     *
     * @param num
     * @return
     */
    public int findRightestNum(int num) {
        int ans = 0;
        ans = num & (~num + 1);
        return ans;
    }


    /**
     * 有两个数出现了奇数次 其他数都出现了偶数次
     *
     * @param arr 思路:
     *            1.先将所有数异或得到eor
     *            2.将eor与(~eor + 1)做与运算得到最右边的1
     *            3.将数组中所有数与最右边的1做与运算
     *            4.得到的结果一定是两个奇数次的其中一个
     *            5.将数组中所有数与得到的结果做异或运算得到另一个奇数次的数
     *            <p>
     *            为什么要这么处理？
     *            1.因为两个奇数次的数不相同 所以他们的二进制一定有一位不同
     *            2.将所有数异或得到的结果就是这两个奇数次的数的异或结果，对于出现了偶数次的数来说 两个数的异或是0
     *            3.将这个结果与(~eor + 1)做与运算得到的结果一定是这两个奇数次的数的其中一个
     *            4.将这个结果与数组中所有数做异或运算得到的结果一定是另一个奇数次的数
     * @return
     */
    public int[] findTwoNum(int[] arr) {
        int[] ans = new int[2];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        int rightestNum = eor & (~eor + 1);
        int eor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightestNum) == 0) {
                eor2 ^= arr[i];
            }
        }
        ans[0] = eor2;
        ans[1] = eor2 ^ eor;
        return ans;
    }

    /**
     * 给定一个数，求这个数的二进制中1的个数
     * 不断找到最后给定数的最后一个1
     * 每找到一个1就将这个1变成0
     * 直到给定数变成0
     *
     * @param N
     * @return
     */
    public int getCount(int N) {
        int ans = 0;
        while (N != 0) {
            int rightestNum = N & (~N + 1);
            ans++;
            N ^= rightestNum;
        }
        return ans;
    }


    public int getKNums2(int[] arr, int k, int m) {

        int[] help = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 31; j >= 0; j--) {
                help[j] += (arr[i] >> j) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < help.length; i++) {
            if (help[i] % k == 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }


    /**
     * K> 1 K<M
     * 要求额外空间复杂度O(1)
     * 时间复杂度O(N)
     * K<M
     *
     * @param arr
     * @return
     */
    public int getKNums3(int[] arr, int k, int m) {

        int[] help = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 31; j >= 0; j--) {
                help[j] += (arr[i] >> j) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < help.length; i++) {
            if (help[i] % k == 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = 1;
        int j = 2;
        System.out.println("i = " + i + ",j = " + j);
        Code01_ExclusiveOrOperation code01_exclusiveOrOperation = new Code01_ExclusiveOrOperation();
        code01_exclusiveOrOperation.swap(i, j);
        System.out.println("i = " + i + ",j = " + j);
        code01_exclusiveOrOperation.swap2(i, j);
        System.out.println("i = " + i + ",j = " + j);
    }
}
