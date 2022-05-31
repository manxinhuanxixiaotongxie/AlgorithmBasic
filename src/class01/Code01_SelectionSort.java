package class01;

import java.util.Arrays;

/**
 * @Description  选择排序
 * @Author Scurry
 * @Date 2022-05-31 10:10
 */
public class Code01_SelectionSort {

    public static void main(String[] args) {

        int size = 100;
        int maxValue = 20000;

        for (int i = 0;i<50000;i++) {


            int[] arr = generateRadomArray(size,maxValue);

            int[] copyArr = new int[arr.length];
            for (int j= 0;j<arr.length;j++) {
                copyArr[j] = arr[j];
            }

            selectionSort(arr);

            Arrays.sort(copyArr);

            for (int k= 0;k<arr.length;k++) {
//                if (copyArr[k] != arr[k]) {
//                    System.out.println("排序失败");
//                    break;
//                }

                if (arr!= null&&copyArr== null || arr == null && copyArr != null) {
                    System.out.println("排序失败");
                }

                if (arr.length != copyArr.length) {

                    System.out.println("排序失败");

                }

                if (copyArr[k] != arr[k]) {
                    System.out.println("排序失败");
                    break;
                }


            }
//            selectionSort(arr);
//            System.out.println(arr);
        }

    }

    /**
     * 过程
     * 0-N-1 位置找到最小值
     * 1-N-1 位置找到最小值
     * 2-N-1 位置找到最小值
     * ...
     * @param arr
     */
    private static void selectionSort(int[] arr){

//        int minIndex = 0;
        for (int i=0;i<arr.length-1;i++) {
            int minIndex = i;
            for (int j= i+1;j<arr.length;j++) {
//                if (arr[j]<arr[minIndex]) {
//                    minIndex = j;
//                }

                minIndex = arr[j] < arr[minIndex]?j:minIndex;


            }

            swap(arr,i,minIndex);

        }

    }

    private static void swap(int[] arr,int i,int j){
        int before = arr[i];
        arr[i] = arr[j];
        arr[j] = before;
    }

    private static void compartor(int[] arr){
        Arrays.sort(arr);
    }

    private static int[] generateRadomArray(int maxSize,int maxValue){

        //此处这么写有问题
        /**
         * Math.random  [0,1)
         * n* Math.random  [0,n)
         *
         * n*MATH.RANDOM +1 [1,n+1)
         *
         * int(n*MATH.RANDOM +1)  [0,n]
         * int(n*MATH.RANDOM) +1 [1,n]
         */
//        int[] arr = new int[(int) (maxSize * Math.random())+1];
        int[] arr = new int[(int) ((maxSize+1) * Math.random())];

        for (int i=0;i<arr.length;i++) {
            arr[i] = (int) ((maxValue+1)*Math.random()) - (int) ((maxValue)*Math.random());
        }
        return arr;

    }
}
