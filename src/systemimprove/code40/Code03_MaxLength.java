package systemimprove.code40;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
 * <p>
 * 返回其长度
 */
public class Code03_MaxLength {

    public int maxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        /**
         * 暴力解法
         */
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum <= k) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    /**
     * O(N)解法
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxLength2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        /**
         * 流程：
         * 1.两个数组
         * minSum：含义是以i开头的子数组 能够取到的最小的累加和是多少
         * minSumEnd 含义是以i开头的子数组能够取到最小累加和的最右侧位置
         *
         * 有了这两个数组之后 我们接下来的流程是：
         * 遍历数组 将数组的end扩到不能扩的位置
         * 根据范围向右扩
         */
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] <= 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int ans = 0;
        int end = 0;
        int sum = 0;
        // 在这个过程中end是不会回退的
        // 要注意 end所处的位置可能是会与i相等
        // 当前数的大小就已经比K要大 end就不能往右扩大
        for (int i = 0; i < arr.length; i++) {
            // 如果当前位置最小的累加和比k要小的话 那么end进行右扩
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            ans = Math.max(ans, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                // 完全扩不动了 从下一个位置开始
                // 重新开始扩 看能不能扩大范围
                end = i + 1;
            }
        }
        return ans;
    }

    public int right(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum <= k) {
                    ans = Math.max(ans, j - i + 1);
                }
            }

        }
        return ans;
    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] res = new int[(int) (Math.random() * maxLength + 1)];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
        }
        return res;
    }

    public boolean isEuqal(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
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

    public int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int maxValue = 100;
        int maxLength = 10;
        int testTimes = 100000;
        Code03_MaxLength code = new Code03_MaxLength();
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code.generateArr(maxValue, maxLength);
            int k = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            int ans1 = code.maxLength1(code.copyArr(arr), k);
            int ans2 = code.maxLength2(code.copyArr(arr), k);
            int ans3 = code.right(code.copyArr(arr), k);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
