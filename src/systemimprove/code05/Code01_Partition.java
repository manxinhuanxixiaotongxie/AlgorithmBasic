package systemimprove.code05;

/**
 * 给定一个数组arr，和一个整数num。请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
 *
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 */
public class Code01_Partition {

    public void partition(int[] arr,int num) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int leftIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            // 从左到右的遍历过程中 如果当前数小于等于num
            if (arr[i] <= num) {
                // 将当前数与左边界的下一个数进行交换
                swap(arr,++leftIndex,i);
            }
        }
    }

    public void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
