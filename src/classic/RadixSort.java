package classic;

/**
 * @Description桶排序
 * @Author Scurry
 * @Date 2023-05-09 16:17
 */
public class RadixSort {
    
    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] help = new int[max+1];

        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }

        int i = 0;
        for (int j = 0; j < help.length; j++) {
//            if (help[i] != 0) {
//                arr[i++] = i;
//            }
            while (help[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    /**
     * 找到最大的数的位数 其余补0
     * 个位进行排序
     * 十位进行排序
     * 百位进行排序
     * N位进行排序
     */

    // arr[L..R]排序  ,  最大值的十进制位数digit
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) { // 有多少位就进出几次
            // 10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是(0和1)的数字有多少个
            // count[2] 当前位(d位)是(0、1和2)的数字有多少个
            // count[i] 当前位(d位)是(0~i)的数字有多少个
            int[] count = new int[radix]; // count[0..9]
            for (i = L; i <= R; i++) {
                // 103  1   3
                // 209  1   9
                j = getDigit(arr[i], d);
                count[j]++;
            }

            /**
             * 经典桶排序实现：
             * 这段代码详细解释：
             * count原始表示的是每个桶对应的是多少个数
             * 比如一个数组个位数为1的有两个，那么count(1)的数量为2
             *
             * 加工一下count数组
             * count(i)为0-i的总和
             * 这样加工之后能得到一个结论：
             * 用1号桶举例，0号桶有1个数  1号桶有两个数
             * 加工之后：
             * 0号桶有一个数  2号桶有三个数
             *
             * 记住我们原始算法的流程，每个桶放的是队列，
             * 1号桶最后出来的数就应该放在加工之后数组的count[j]-1的位置
             */
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

            for (i = R; i >= L; i--) {

                j = getDigit(arr[i], d);

                // 根据位数对数组进行排序
                // 假设现在到了101这个数，个位数是1
                // 这个数根据个位数应该放在什么位置呢?

                help[count[j] - 1] = arr[i];

                count[j]--;
            }

            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    /**
     * 获取数组最大数的位数
     * @param arr
     * @return
     */
    public int getIndex(int[] arr) {

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] > max) {
//                max = arr[i];
//            }
            max = Math.max(max,arr[i]);
        }

        int index = 0;
        while (max != 0) {
            index++;
            max /=10;
        }
        return index;
    }

    /***
     *
     * 912 计算个位数  2 怎么算 912/1 % 10 = 2
     * 912 计算十位数  1 怎么算 912/10 % 10 = 1
     * 912 计算百位数  9 怎么算 912/100 % 10 = 9
     *
     * @param x
     * @param d
     * @return
     */
    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }


}
