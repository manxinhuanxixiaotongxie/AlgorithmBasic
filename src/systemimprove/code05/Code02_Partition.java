package systemimprove.code05;

/**
 * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 * <p>
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code02_Partition {

    /**
     * 过程如下：
     * 定义两个变量 一个是小于的左边界  一个是大于的右边界
     * <p>
     * 当前值比给定的值要小的话 那么当前值与左边界的右侧的值进行交换 同时index++(交换之后的index不可能放在的数组右侧)
     * 当前值比给定的值要大的话  那么当前值与右边界的左侧进行交换 但是此时交换之后index位置的值因为没有进行比较 还无法知道大小
     * 当前值与给定的值相同 当index++
     * 那么leftIndex+1到rightIndex-1之间的数就是等于num的数
     *
     * @param arr
     * @param num
     */
    public void partition(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int leftIndex = -1;
        int N = arr.length;
        int rightIndex = N;
        /**
         * 如果当前数比给定的num小的话，leftindex向右扩大
         * 如果相等的话，不做任何操作
         * 如果大于的话，rightIndex向左扩大
         */
        for (int i = 0; i < N; i++) {
            if (arr[i] < num) {
                swap(arr, ++leftIndex, i);
            } else if (arr[i] > num) {
                swap(arr, --rightIndex, i--);
            }

        }
    }

    /**
     * 与parttion1函数实现的功能相同 流程相同
     * 减少循环次数
     *
     * @param arr
     * @param num
     */
    public void partition2(int[] arr, int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int leftIndex = -1;
        int N = arr.length;
        int rightIndex = N;
        int index = 0;
        while (index < rightIndex) {
            if (arr[index] < num) {
                swap(arr, ++leftIndex, index++);
            } else if (arr[index] > num) {
                swap(arr, --rightIndex, index);
            } else {
                index++;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
