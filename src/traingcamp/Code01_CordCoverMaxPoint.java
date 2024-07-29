package traingcamp;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {

    public int maxPoint1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // 以i位置开头
            int index = i;
            while (index < arr.length && arr[index] - arr[i] <= k) {
                index++;
            }
            ans = Math.max(ans, index - i);
        }
        return ans;
    }


    public int maxPoint2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 以i位置结尾
        /**
         * 进行转换 以i位置结尾 就意味着在0-i位置的数组找到《=arr[i]-k的最右侧的位置
         *
         *
         * 这个过程可以使用二分查找
         *
         */
        int ans = 0;
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            index = findRightIndex(arr, index, i, arr[i] - k);
            ans = Math.max(ans, i - index + 1);
        }
        return ans;
    }

    private int findRightIndex(int[] arr, int L, int R, int value) {
        int left = L;
        int right = R;
        int ans = L;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] >= value) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }


    public int minPoint3(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int leftIndex = 0;
        int rightIndex = 0;
        int ans = 0;
        while (rightIndex < arr.length) {
            while (rightIndex < arr.length && arr[rightIndex] - arr[leftIndex] <= k) {
                rightIndex++;
            }
            ans = Math.max(ans, rightIndex - leftIndex);
            leftIndex++;
        }
        return ans;
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        Arrays.sort(arr);
        return arr;
    }

    public void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code01_CordCoverMaxPoint cordCoverMaxPoint = new Code01_CordCoverMaxPoint();
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = cordCoverMaxPoint.generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * maxValue) + 1;
            int ans1 = cordCoverMaxPoint.maxPoint1(arr, k);
            int ans2 = cordCoverMaxPoint.maxPoint2(arr, k);
            int ans3 = cordCoverMaxPoint.minPoint3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                succeed = false;
                System.out.println("Oops!");
                cordCoverMaxPoint.printArr(arr);
                System.out.println(k);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }


}
