package systemimprove.code04;

public class Code03_BiggerThanTwice {
    /**
     * 后面有多少数*2还小于当前数
     */
    // 3 4 5 5 | 1 1 2 2
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        return process(nums, 0, nums.length - 1);
    }

    public int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);
    }

    public int merge(int[] arr, int L, int mid, int R) {
        int leftIndex = L;
        int rightIndex = mid + 1;
        int ans = 0;

        while (leftIndex <= mid && rightIndex <= R) {
            if (arr[leftIndex] > (arr[rightIndex] << 1)) {
                ans += (mid - leftIndex + 1);
                rightIndex++;
            } else {
                leftIndex++;
            }
        }

        leftIndex = L;
        rightIndex = mid + 1;
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        while (leftIndex <= mid && rightIndex <= R) {
            help[helpIndex++] = arr[leftIndex] < arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }

        while (leftIndex <= mid) {
            help[helpIndex++] = arr[leftIndex++];
        }

        while (rightIndex <= R) {
            help[helpIndex++] = arr[rightIndex++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public int baoli(int[] arr) {
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

    public int[] generateArray(int maxLength, int maxValue) {
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
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

    public void printArray(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Code03_BiggerThanTwice test = new Code03_BiggerThanTwice();
        int testTime = 100000;
        int maxSize = 10;
        int maxValue = Integer.MAX_VALUE;

        for (int i = 0; i < testTime; i++) {
            int[] arr = test.generateArray(maxSize, maxValue);
            int[] copyArr = test.copyArray(arr);
            if (test.reversePairs(arr) != test.baoli(copyArr)) {
                System.out.println("Oops!");
                test.printArray(arr);
                test.printArray(copyArr);
                break;
            }
        }

    }
}
