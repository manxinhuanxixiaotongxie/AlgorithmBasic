package class02;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-14 17:03
 */

/**
 * 题目：一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数？
 * 题目：一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数？
 * 题目：一个数组中有一种数出现K次，其他数都出现M次，M>1,K>1,找到出现了K次都数，要求：额外空间复杂度O(1)
 */
public class Code02_EvenTimesOddTimes {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 2, 2, 3, 4, 4, 5};
        find2OddNumber(arr);

    }


    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数？
     * <p>
     * 思路：
     * <p>
     * 1.暴力解决  遍历数组  使用Map<Integer,Integer>  key 数值  value：出现的次数</>
     * <p>
     * 2.使用位运算
     * <p>
     * 一个数  偶数次的异或运算是本身
     * 0异或任何数是本身  异或两次就是0
     *
     * @param arr
     * @return
     */
    private static int findOddNumber(int[] arr) {
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            index ^= arr[i];
        }

        return index;

    }


    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数？
     * <p>
     * 上面题目讲了偶数次异或就是本身
     * <p>
     * 要有概念：出现两次奇数的话 那么整个数组的异或的结果就是两个奇数异或的结论
     * <p>
     * int[] arr = new int[] {1,3,2,2,3,4,4,5}
     * <p>
     * 此时返回 1 5
     * <p>
     * 思路：
     *
     * @param arr
     * @return
     */
    private static void find2OddNumber(int[] arr) {

        int index = 0;


        for (int i = 0; i < arr.length; i++) {
            index ^= arr[i];
        }

        /**
         * 这时候数组中所有元素的计算结果就是两个奇数数值异或的结果
         * 假设数组中这两个数为a b  数组的结果就是 a^b
         *
         * 思路：
         *
         * 1.取出最后一位为1 的数据
         * 2.将数组中的偶数分为两类  第一类：最后一位是1   最后一位不是1
         *
         *
         *
         *
         */

        // 取出最后a^B的最后一位
        /**
         *
         * 对原数值取反   然后加1  再进行异或
         *
         */
        int flag = index & (-index + 1);
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            /**
             * flag是a^b的结果的最后一位是1 的数据
             * 数组中的数分为两种
             * 一种是该位上的数值为1
             * 一种是该位上不为1的
             *
             *
             * 为1 的
             *
             *
             *
             */
            if ((arr[i] & flag) != 0) {
                // 最后以为不为1的数 异或之后的结果   要么是奇数 要么是偶数
                a = arr[i] ^ flag;
            }
        }
        System.out.println(a + "    " + (a ^ flag));


    }

}
