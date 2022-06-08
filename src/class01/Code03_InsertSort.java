package class01;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-05-31 19:19
 */
public class Code03_InsertSort {

    public static void main(String[] args) {

        int[] arr = new int[] {6,7,9,3,4,2,8,3,1};
        insertSort(arr);
        System.out.println(arr);
    }


    /**
     * 选择排序过程
     * [ 6 7 9 3 4 2 8 3 1 ]
     * 先0-0位置  有序
     *  0-1位置  有序
     *   0-2位置有序
     *   0-3位置有序
     *   0-N位置有序
     * @param arr
     */
    private static void insertSort(int[] arr) {
        for (int i=0;i<arr.length-1;i++) {
//            for (int j = i+1;j>=0;j--) {
//                if (arr[j-1] < arr[j]) {
//                    swap(arr,j-1,j);
//                }
//            }

            for (int j= i+1;j>=1&& arr[j-1]<arr[j];j--) {
                swap(arr,j-1,j);
            }
        }
    }

    private static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
