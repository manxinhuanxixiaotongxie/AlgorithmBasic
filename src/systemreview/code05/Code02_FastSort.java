package systemreview.code05;

/**
 * 快排
 */
public class Code02_FastSort {
    public void fastSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private void process(int[] arr, int L, int R) {
        // 这里的basecase不能是L==R
        // 在荷兰国旗的问题上 当时L==R的时候 荷兰国旗返回的数组 L可能会与左边界相等 会导致L比R大的情况出现
//        if (L == R) {
//            return;
//        }
        if (L >= R) {
            return;
        }
        int[] equal = neitherLandFlag(arr, L, R);
        process(arr, L, equal[0] - 1);
        process(arr, equal[1] + 1, R);
    }

    // 在L-R范围上进行荷兰国旗问题的处理
    // 该方法的返回值返回的是相等的区间 ans[0]是相等的左边界 ans[1]是相等的右边界
    private int[] neitherLandFlag(int[] arr, int L, int R) {
        // 荷兰国旗问题 将数组拆分成三部分 小的数放在左边 等于的数放在中间 大于的数放在右边
        // 随机选择一个作为基础比较
        int randomIndex = L + (int) (Math.random() * (R - L + 1));
        // 进行交换 交换之后用这个随机数作为比较的基准
        swap(arr, randomIndex, R);
        int leftIndex = L - 1;
        int rightIndex = R;
        int index = L;
        while (index < rightIndex) {
            if (arr[index] < arr[R]) {
                swap(arr, ++leftIndex, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --rightIndex, index);
            } else {
                index++;
            }
        }
        swap(arr, rightIndex, R);
        int[] ans = new int[2];
        ans[0] = leftIndex + 1;
        ans[1] = rightIndex;
        return ans;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] generateRandomArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
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

    public void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void comparator(int[] arr) {
        java.util.Arrays.sort(arr);
    }

    public static void main(String[] args) {
        Code02_FastSort fastSort = new Code02_FastSort();
        int testTime = 500000;
        int maxValue = 100;
        int maxLength = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = fastSort.generateRandomArr(maxValue, maxLength);
            int[] arr2 = fastSort.copyArr(arr1);
            fastSort.fastSort(arr1);
            fastSort.comparator(arr2);
            if (!fastSort.isEqual(arr1, arr2)) {
                succeed = false;
                fastSort.printArr(arr1);
                fastSort.printArr(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }
}
