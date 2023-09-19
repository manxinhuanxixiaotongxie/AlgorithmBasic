package systemlearning.code04;

import java.util.Arrays;

/**
 * @Description:
 * 归并排序以及由归并排序引起的扩展
 * 1.归并排序的实现 mergeSort
 * 2.小和问题
 * 3.逆序对问题 逆序提供两种思路
 * 4，小于两倍问题
 * @Author Scurry
 * @Date 2023-09-18 11:13
 */
public class Code01_MergeSortAndExport {

    /**
     * 归并排序怎么处理？
     * 第一步，将数组一分为2
     * 注意分的方式：
     *      1.int mid = (R + L) / 2;
     *      2.int mid = L+((R-L)>>1) 这种方式可以防止越界
     * 第二步：左边merge
     * 第三步：右边merge
     * 第四部：将左右两边的数进行merge
     * 第五步：merge函数
     *
     * 经过上述两步之后，左边部分与右边部分已经是有序的了  只需要将左右两部分进行合并
     *过程：
     * 当左边的数小于右边的数时，将左边的数放入help数组中，左边的指针向右移动
     * 当右边的数小于左边的数时，将右边的数放入help数组中，右边的指针向右移动
     * 当左右两边的数相等时，将左边的数放入help数组中，左边的指针向右移动
     * @param arr
     * @return
     */
    public int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        process(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * @param arr
     * @param L
     * @param R
     */
    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);

        process(arr, L, mid);
        process(arr, mid + 1, R);

        merge(arr, L, R, mid);

    }

    public void merge(int[] arr, int L, int R, int mid) {


        int[] help = new int[R - L + 1];

        int p1 = L;
        int p2 = mid + 1;
        int index = 0;

        // 谁小放谁
        while (p1 <= mid && p2 <= R) {
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }

        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null || arr1 != null && arr2 == null) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }


    /**
     * 在一个数组中，一个数的左边比他小的数的总和，叫做这个数的小和，所有数的小和累加起来，叫做数组的小和，求数组的小和
     *
     * 暴力解法，用作对数器
     *
     * @param arr
     * @return
     */
    public int getMinSum1(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j >= 0) {
                if (arr[j] < arr[i]) {
                    ans += arr[j];
                }
                j--;
            }
        }
        return ans;
    }

    /**
     * 优雅写法，利用归并排序的特性
     * 在help数组进行组合时
     * 转换一下思路，左边有多少数比我小 与右边有多少数比我大对等
     * 怎么求右边比我大的数呢？
     * 在归并排序的过程中   左组与右组都是有序的
     * 1.当左组的数比右组的数小，意味着当前右组的index位置到R位置的数都小 产生小和R-index+1
     * 2.相等时复制右组
     * @param arr
     * @return
     */
    public int getMinSum2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process2(arr, 0, arr.length - 1);
    }

    public int process2(int[] arr, int L, int R) {

        if (L == R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);
        return process2(arr, L, mid) + process2(arr, mid + 1, R) + merge2(arr, L, R, mid);
    }


    /**
     * 小和问题思路：
     * 右边有多少数比他小   转化成左边有多少数比它大
     * 在归并排序的过程中 左右两边都是有序的
     * 【456】【】
     * 在右边遍历的过程中，如果出现了arr[p1]<arr[p2]意味着从p2-R的数都是大于arr[p1]的
     * <p>
     * 左边有多少数比我小  如果是归并排序从大到小排序能行吗？
     * 从右开始向左计算
     * 987 876
     *
     * @param arr
     * @param L
     * @param R
     * @param mid
     * @return
     */
    public int merge2(int[] arr, int L, int R, int mid) {
        if (L == R) {
            return 0;
        }
        int[] help = new int[R - L + 1];
        int index = 0;
        int p1 = L;
        int p2 = mid + 1;
        int ans = 0;

        while (p1 <= mid && p2 <= R) {
            // 总共产生了多少个小和
            // 归并排序的过程中，如果左边的数小于右边的数，这个数产生了多少小和？右边的数还剩下多少个就产生了多少个小和
            // 为什么可以使用归并排序的过程计算小数和？
            // 找到一个数的右边有多少个数比他小，转达成左边有多少数比他大
            ans += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }


    /**
     * 逆序对
     * （a,b）a > b
     *
     * 提供了两种思路：
     * 1.依然是merge过程，只是在merge过程中，要进行转化
     * 从help数组的右边开始进行回复  当左边的数大于右边的数的时候，产生逆序对，逆序对的个数是p2-m个
     * 相等时 先复制右边的数不产生小和
     * 小于左边向右移动不产生逆序对
     *
     * @param arr
     * @return
     */
    public int getMinSum3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process3(arr, 0, arr.length - 1);
    }

    public int process3(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process3(arr, L, mid) + process3(arr, mid + 1, R) + merge3New(arr, L, R, mid);
    }

    /**
     * 有多少逆序对
     * （a,b）是逆序的
     * 逆序对问题思路：
     *
     * @param arr
     * @param L
     * @param R
     * @param mid
     * @return
     */
    public int merge3(int[] arr, int L, int R, int mid) {
        int[] help = new int[R - L + 1];

        int index = R - L;
        int p1 = mid;
        int p2 = R;
        int ans = 0;
        // 逆序对 后面有多少数比当前数小
        while (p1 >= L && p2 >= mid + 1) {
            // 逆序对的问题  --》 左边出现了一个数比右边的数大的话  意味着从mid+1到p2位置的数都是小于p1的  产生了多少个逆序对
            ans += arr[p1] > arr[p2] ? (p2 - mid) : 0;
            help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= L) {
            help[index--] = arr[p1--];
        }
        while (p2 >= mid + 1) {
            help[index--] = arr[p2--];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    /**
     * merge3  merge3New都是对的
     *
     * @param arr
     * @param L
     * @param R
     * @param mid
     * @return
     */
    public int merge3New(int[] arr, int L, int R, int mid) {
        int[] help = new int[R - L + 1];

        int index = 0;
        int p1 = L;
        int p2 = mid + 1;
        int ans = 0;
        // 逆序对 后面有多少数比当前数小
        while (p1 <= mid && p2 <= R) {
            // 逆序对的问题  --》 左边出现了一个数比右边的数大的话  意味着从mid+1到p2位置的数都是小于p1的  产生了多少个逆序对
            ans += arr[p1] > arr[p2] ? (R - p2 + 1) : 0;
            help[index++] = arr[p1] > arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }



    public int getRightLessThanTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process4(arr, 0, arr.length - 1);
    }

    public int process4(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process4(arr, L, mid) + process4(arr, mid + 1, R) + merge4(arr, L, R, mid);
    }

    /**
     * 右边有多少数*2《当前数
     * @param arr
     * @param L
     * @param R
     * @param mid
     * @return
     */
    public int merge4(int[] arr, int L, int R, int mid) {
        int[] help = new int[R - L + 1];

        int left = L;
        int right = mid + 1;
        int index = 0;
        int ans = 0;
        // 目前囊括进来的数，是从[M+1, windowR)
        int windowR = mid + 1;
        for (int i = L; i <= mid; i++) {
            while (windowR <= R && (long) arr[i] > (long) arr[windowR] * 2) {
                windowR++;
            }
            ans += windowR - mid - 1;
        }

        while (left <= mid && right <= R) {

            help[index++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }

        while (left <=mid) {
            help[index++] = arr[left++];
        }

        while (right <= R) {
            help[index++] = arr[right++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public  int comparatorForTwice(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code01_MergeSortAndExport code01_mergeSortAndExport = new Code01_MergeSortAndExport();
        int maxVlaue = 100;
        int maxLength = 6;
        int testTimes = 100000;
        int[] test = new int[]{-2, -20, 11, 11, -18};
        System.out.println(code01_mergeSortAndExport.comparator(test));
        System.out.println(code01_mergeSortAndExport.getMinSum3(test));
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01_mergeSortAndExport.generateArr(maxVlaue, maxLength);
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);

            code01_mergeSortAndExport.mergeSort(arr1);
            Arrays.sort(arr2);

            if (!code01_mergeSortAndExport.isEqual(arr1, arr2)) {
                System.out.println("merge sort oops!");
            }

            int[] arr3 = Arrays.copyOf(arr, arr.length);
            int[] arr4 = Arrays.copyOf(arr, arr.length);

            if (code01_mergeSortAndExport.getMinSum1(arr3) != code01_mergeSortAndExport.getMinSum2(arr4)) {
                System.out.println("min sum oops!");
            }

            int[] arr5 = Arrays.copyOf(arr, arr.length);
            int[] arr6 = Arrays.copyOf(arr, arr.length);
            if (code01_mergeSortAndExport.getMinSum3(arr5) != code01_mergeSortAndExport.comparator(arr6)) {
                System.out.println("nixu oops!");
            }


            int[] arr7 = Arrays.copyOf(arr, arr.length);
            int[] arr8 = Arrays.copyOf(arr, arr.length);
            if (code01_mergeSortAndExport.getRightLessThanTwice(arr7) != code01_mergeSortAndExport.comparatorForTwice(arr8)) {
                System.out.println("windows oops!");
            }
        }


    }


}
