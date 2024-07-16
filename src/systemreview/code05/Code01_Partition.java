package systemreview.code05;

/**
 * 数组切分问题
 */
public class Code01_Partition {
    /**
     * 将一个数组小于某一个数放在数组的左边 其他的数放在数组的右边
     * 要求：时间复杂度O(N) 空间复杂度O(1)
     * 过程如下：
     */
    public void partition1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int leftIndex = -1;
        int index = 0;
        while (index < arr.length) {
            if (arr[index] < num) {
                swap(arr, index++, ++leftIndex);
            } else {
                index++;
            }
        }
    }

    /**
     * 讲一个数组分成小于某一个数放在左边 等于某一个数放在中间 大于某一个数放在右半
     * 过程如下：
     * 定义两个变量 一个是leftIndex 一个是rightIndex
     * leftIndex的的初始值是-1  rightIndex的初始值是arr.length
     * 当前值小于num的话 leftIndex向右扩大 同时index扩大 为什么可以扩大 是因为在leftIndex到index中间的位置的数不可能被右边界吸收
     * 当前值与给定值相等的话 index++
     * 当前值比给定值大的话 rightIndex向左扩大 同时index不变 为什么index不变是因为当前值与右边界交换过来的值不一定是比给定值大的
     *
     * @param arr
     * @param num
     */
    public void partition2(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int leftIndex = -1;
        int rightIndex = arr.length;
        int index = 0;
        while (index < rightIndex) {
            if (arr[index] < num) {
                // 如果当前值比给定的num要小的话 左边界扩大 将两边的数进行交换
                swap(arr, ++leftIndex, index++);
            } else if (arr[index] > num) {
                // 如果当前值比给定的num要大的话 右边界缩小 将两边的数进行交换
                swap(arr, --rightIndex, index);
            } else {
                // 如果当前值等于给定的num的话 直接将index++
                index++;
            }
        }

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
