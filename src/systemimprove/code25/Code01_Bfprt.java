package systemimprove.code25;

import java.util.Arrays;

/**
 * 在无序数组中求第K小的数
 * <p>
 * <p>
 * 两种思路：
 * <p>
 * 1、改写快排
 * 2、bfprt算法
 * 该算法于1973年由Blum、Floyd、Pratt、Rivest和Tarjan提出，故称为bfprt算法。
 * 3.n * logn的方式
 * 4.n*logk的方式 维护一个大小为k的堆 这个堆代表目前选出的前K个最小的数 在堆中K个元素堆顶的元素就是最小的K个数最大的那个 大跟堆
 *
 * @author Scurry
 * @date 2024/12/26 2024/12/26
 */
public class Code01_Bfprt {

    /**
     * 改写快排 递归写法
     *
     * @return
     */
    public int findK1(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, k - 1);
    }

    /**
     * 非递归版本
     * <p>
     * 找到第K小的数
     *
     * @param nums
     * @param k
     * @return
     */
    public int findK2(int[] nums, int k) {
        int index = k - 1;
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int[] partition = partition(nums, left, right);
            if (index >= partition[0] && index <= partition[1]) {
                return nums[index];
            } else if (index < partition[0]) {
                right = partition[0] - 1;
            } else {
                left = partition[1] + 1;
            }
        }
        return nums[left];

    }

    /**
     * 找到第K小的数
     * <p>
     * bfprt
     * <p>
     * 这个算法是五个人的名字的首字母
     * 解决的问题就是在无序数组中找到第K小的数
     * <p>
     * 过程：
     * 1.将数组进行分组 每五个数一组 不足的部分 让他不足
     * <p>
     * 2.对每个拆分出来的字数组进行排序
     * <p>
     * 3.针对拆分出来的子数组获取一个中位数
     * 说明：这么做的好处是：当求出中位数之后
     * 原题意求的是第K小的数
     * 有了中位数之后 ：
     * 下面每一列对应的是原数组中的五个数
     * o o o o o o o o
     * o o o o o o o o
     * o o o o o o o o   中位数
     * o o o o o o o o
     * o o o o o o o o
     * <p>
     * <p>
     * 将中位数组成一个新的数组 这个数组的长度是n/5
     * 接下来  在这个新数组中找到这个数组的中位数  bfprft(marr,marr.length/2)
     * 这样做有什么好处呢？
     * <p>
     * 我们看看在这个新数组中 求得的中位数
     * 4 5 5 5 5
     * 假设新数组是这个样子
     * 4 5 5 5 5   新数组
     * ^
     * 0 0 0 0 0
     * 0 0 0 0 0
     * * *
     * 观察上面箭头位置的数 说明至少找到了N/5 *2个数是大于这个中位数的
     * <p>
     * <p>
     * 数组分组之后 每个组五个数  总共有n/5组
     *
     * @param nums
     * @param k
     * @return
     */
    public int findK3(int[] nums, int k) {
        return bfprt(nums, 0, nums.length - 1, k - 1);
    }


    /**
     * 在left到right范围上找到第k小的数
     *
     * @param nums
     * @param left
     * @param right
     * @param k
     * @return
     */
    private int bfprt(int[] nums, int left, int right, int k) {
        if (left >= right) {
            return nums[left];
        }
        // 分组之后找到一个天选的p位置
        int median = findMedian(nums, left, right);
        // 进行partition操作
        int[] partition = partitionForBfprt(nums, left, right, median);
        // 后续的过程与快排一样
        if (k >= partition[0] && k <= partition[1]) {
            return nums[k];
        } else if (k < partition[0]) {
            right = partition[0] - 1;
        } else {
            left = partition[1] + 1;
        }
        return bfprt(nums, left, right, k);
    }

    /**
     * 根据bfprt的过程
     * 我们需要找到一个严格位置中间的数
     * 这个方法就是在在一个范围内 找到这个中间的数
     *
     * @return
     */
    private int findMedian(int[] nums, int left, int right) {
        // 将数组拆分成5个一组的数组
        // 新数组的长度是多少
        // 假设nums的长度是7 那么新数组的长度就是2
        // 最后一个数组的长度只有2
        // 判断一个数是否能否能够整除
        int length = right - left + 1;
        int add = ((length % 5) == 0) ? 0 : 1;
        int[] newArr = new int[(length / 5) + add];
        // 构建这个新数组
        // 每个位置都对应原数组的5位数的一个中位数
        // newArr[0] =  原始数组  0-4的位置
        // newArr[1] =  原始数组  5-9的位置
        // 分组进行构建
        for (int i = 0; i < newArr.length; i++) {
            // i代表组数
            // i组对应的原始数组的位置就是i*5-i*5+4
            // 最后一组可能不足五个数
            // 要与最后一个数取最小值
            int leftIndex = (i * 5) + left;
            // 在5个一组的小数组中进行插入排序 并返回中位数
            // 可能最后一组不够五个数
            newArr[i] = getFiveMid(nums, leftIndex, Math.min(leftIndex + 4, right));
        }
        // 针对新数组进行bfprt newArr是中位数数组
        return bfprt(newArr, 0, newArr.length - 1, newArr.length / 2);
    }

    /**
     * 下中位数
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int getFiveMid(int[] arr, int left, int right) {
        // 在left-right位置上进行排序
        // 插入排序的常数时间复杂度最好
        for (int i = left + 1; i <= right; i++) {
            int j = i;
            while (j > left && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
        // 返回下中位数的位置
        return arr[((left + right) / 2)];
    }

    /**
     * 快速排序的过程：
     * 在一次partition的过程中可以将数组拆分成左边小 中间相等 右边大的三个部分
     * 如果k int[0]-int[1]之间，那么第k小的数一定是这个
     * 如果k比int[0]还小，那么在int[0]左边找
     * 如果k比int[1]还大，那么在int[1]右边找
     * <p>
     * 时间复杂度怎么定义：
     * <p>
     * 递归写法
     *
     * @param nums
     * @param left
     * @param right
     * @param index
     * @return
     */
    private int process(int[] nums, int left, int right, int index) {
        // 递归结束的条件
        if (left >= right) {
            return nums[left];
        }

        /**
         * 这里说明下
         * 如果是要求第6小的数值 实际是求的排序在的下标在第五位的数
         * 已经求出来了相等的区间是5-7
         * 如果相等的区间是是5-7
         * 那么说明前面0-4都是小于这个相等区间的  这个相等区间的数就是第5小的数
         */
        int[] partition = partition(nums, left, right);
        if (index >= partition[0] && index <= partition[1]) {
            return nums[index];
        } else if (index < partition[0]) {
            return process(nums, left, partition[0] - 1, index);
        } else {
            return process(nums, partition[1] + 1, right, index);
        }

    }

    private int[] partitionForBfprt(int[] nums, int left, int right, int num) {
        // 传入的一个随机数
        int leftIndex = left - 1;
        int righrIndex = right + 1;
        int i = left;
        while (i < righrIndex) {
            if (nums[i] > num) {
                swap(nums, i, --righrIndex);
            } else if (nums[i] < num) {
                swap(nums, i++, ++leftIndex);
            } else {
                i++;
            }
        }
        return new int[]{leftIndex + 1, righrIndex - 1};

    }

    /**
     * 改写快排
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int[] partition(int[] nums, int left, int right) {
        // 随机选择一个数字
        int random = left + (int) Math.random() * (right - left + 1);
        int num = nums[random];
        // 找到随机数之后将当前数作为一个基准进行比较
        // 小于num[random]放在左边
        // 等于nums[random]放在中间
        // 大于nums[random]放在右边
        // 交换random位置的值与最后一位的值
        int leftIndex = left - 1;
        int righrIndex = right + 1;
        int i = left;
        while (i < righrIndex) {
            if (nums[i] > num) {
                swap(nums, i, --righrIndex);
            } else if (nums[i] < num) {
                swap(nums, i++, ++leftIndex);
            } else {
                i++;
            }
        }
        // 经过这个步骤之后 数组已经变成了左边小 右边大 中间相等的状态
        return new int[]{leftIndex + 1, righrIndex - 1};
    }

    /***
     *
     * 交换数组中两个数
     *
     * @author Scurry
     * @date 2024/12/26 2024/12/26
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    private int[] copyArr(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }


    public int findKRight(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[k - 1];
    }

    public static void main(String[] args) {
        Code01_Bfprt code01_bfprt = new Code01_Bfprt();
        int maxValue = 100;
        int maxLength = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01_bfprt.generateArr(maxValue, maxLength);
            int[] arr2 = code01_bfprt.copyArr(arr);
            int[] arr3 = code01_bfprt.copyArr(arr);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = code01_bfprt.findK1(arr, k);
            int ans2 = code01_bfprt.findKRight(arr2, k);
            int ans3 = code01_bfprt.findK2(arr, k);
            int ans4 = code01_bfprt.findK3(arr3, k);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("Oops1!");
            }
        }
        System.out.println("finish!");
    }
}
