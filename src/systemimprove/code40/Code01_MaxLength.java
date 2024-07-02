package systemimprove.code40;

/**
 * 给定一个正整数组成的无序数组arr，给定一个正整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
public class Code01_MaxLength {

    public int maxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == k) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    public int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }
        int L = -1;
        int R = -1;
        int ans = 0;
        int sum = 0;
        while (R < arr.length) {
            // R小于k的话 R向右移动
            if (sum < k) {
                R++;
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else if (sum == k) {
                ans = Math.max(ans, R - L);
                R++;
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else {
                // 如果sum的值已经比k大了，那么L向右移动
                // 正数
                sum -= arr[++L];
            }
        }
        return ans;
    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] ans = new int[(int) (Math.random() * maxLength + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue + 1);
        }
        return ans;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
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

    public static void main(String[] args) {
        int maxValue = 20;
        int maxLength = 10;
        int testTimes = 100000;
        Code01_MaxLength code01_maxLength = new Code01_MaxLength();
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01_maxLength.generateArr(maxValue, maxLength);
            int k = (int) (Math.random() * maxValue + 1);
            int ans1 = code01_maxLength.maxLength1(arr, k);
            int ans2 = code01_maxLength.maxLength(arr, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
