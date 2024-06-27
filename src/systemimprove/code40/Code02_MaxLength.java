package systemimprove.code40;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和等于K，并且是长度最大的
 * 返回其长度
 */
public class Code02_MaxLength {

    public int maxlength1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
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

    public int maxLength2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 转化一下 子数组要求的累加和是k 转化成一下
        // 0-x位置的累加和是y 0<b <x b在0-x中间
        // 现在 要满足b-x的累加和是k 那么0-b的累加和是y-k
        // 那么最长的长度就是第一次出现的累加和是y-k的位置到x的位置
        Map<Integer, Integer> indexMap = new HashMap<>();
        indexMap.put(0, -1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (indexMap.containsKey(sum - k)) {
                int preIndex = indexMap.get(sum - k);
                ans = Math.max(ans, i - preIndex);
            }
            if (!indexMap.containsKey(sum)) {
                indexMap.put(sum, i);
            }
        }
        return ans;
    }


    public int[] generateArr(int maxValue, int maxLength) {
        int[] ans = new int[(int) (Math.random() * maxLength + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
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
        int maxValue = 100;
        int maxLength = 20;
        int testTimes = 100000;
        Code02_MaxLength maxLength1 = new Code02_MaxLength();
        for (int i = 0; i < testTimes; i++) {
            int[] arr = maxLength1.generateArr(maxValue, maxLength);
            int k = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            int ans1 = maxLength1.maxlength1(arr, k);
            int ans2 = maxLength1.maxLength2(arr, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
