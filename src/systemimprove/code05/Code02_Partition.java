package systemimprove.code05;

/**
 * 给定一个数组arr，和一个整数num。请把小于num的数放在数组的左边，等于num的数放在中间，大于num的数放在数组的右边。
 *
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 */
public class Code02_Partition {

    public void partition(int[] arr,int num) {
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
        for (int i = 0; i < N;i++) {
            if (arr[i] < num) {
                swap(arr,++leftIndex,i);
            } else if (arr[i] > num) {
                swap(arr,--rightIndex,i--);
            }

        }
    }

    public void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
