package systemimprove.code04;

public class Code02_NiXuDui {
    /**
     * 返回数组中的所有的逆序对
     * 逆序对：左边的数比右边的数大
     */

    /**
     * 暴力解法
     *
     * @return
     */
    public int niXuDui1(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 逆序对，右边的数比左边的数小
     * <p>
     * 1 5 7 7  | 4 4 6 6
     * <p>
     * 得出一个结论：
     */

    public int niXuDui2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        /**
         * 解释一下这里的递归
         * 1.先求左边的小和
         * 2.再求右边的小和
         * 3.再求左右merge的小和
         * 4.将左边的小和 + 右边的小和 + merge的小和
         */
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);

    }

    public int merge(int[] arr, int L, int mid, int R) {
        int leftIndex = L;
        int rightIndex = mid + 1;
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int ans = 0;
        // 1 5 7 7  | 4 4 6 6
        while (leftIndex <= mid && rightIndex <= R) {
            if (arr[leftIndex] > arr[rightIndex]) {
                ans += (mid - leftIndex + 1);
                help[helpIndex++] = arr[rightIndex++];
            } else {
                help[helpIndex++] = arr[leftIndex++];
            }
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
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
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
        Code02_NiXuDui test = new Code02_NiXuDui();
        int testTime = 500000;
        int maxLength = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = test.generateArray(maxLength, maxValue);
            int[] copyArr = test.copyArray(arr);
            int baoLi = test.niXuDui1(arr);
            int merge = test.niXuDui2(copyArr);
//            System.out.printf("暴力解法：%d,归并解法：%d",baoLi,merge);
            if (baoLi != merge) {
                System.out.println("Oops!");
                test.printArray(copyArr);
            }
        }
    }
}
