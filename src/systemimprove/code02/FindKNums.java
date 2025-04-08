package systemimprove.code02;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 一个数出现了K次 其他数出现了M次
 * 要求找到这个数
 * 时间复杂度O(N) 空间复杂度O(1)
 * m > 1 k < m
 */
public class FindKNums {

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public static int right(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == k) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 使用二进制位运算
     *
     * @param arr
     * @param k
     * @param m
     * @return
     */
    public static int findNum2(int[] arr, int k, int m) {
        int[] t = new int[32];
        // t[i]表示第i位的数字出现了多少次
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                t[i] += ((num >> i) & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static int[] randomArray(int k, int n, int type) {
        HashSet<Integer> set = new HashSet<>();
        while (set.size() != type - 1) {
            set.add((int) (Math.random() * 10000) - (int) (Math.random() * 10000));
        }
        int knum = 0;
        do {
            knum = (int) (Math.random() * 10000) - (int) (Math.random() * 10000);
        } while (set.contains(knum));
        int[] arr = new int[(type - 1) * n + k];
        int index = 0;
        for (int num : set) {
            for (int i = 0; i < n; i++) {
                arr[index++] = num;
            }
        }
        for (int i = 0; i < k; i++) {
            arr[index++] = knum;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    public static boolean check(int[] arr, int k, int n, int type) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        int kcount = 0;
        int ncount = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key) == n) {
                ncount++;
            } else if (map.get(key) == k) {
                kcount++;
            } else {
                return false;
            }
        }
        return map.size() == type && kcount == 1 && ncount == type - 1;
    }

    public static void main(String[] args) {
        int knMax = 20;
        int typeMax = 50;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int k = 0;
            int n = 0;
            do {
                k = (int) (Math.random() * knMax) + 1;
                n = (int) (Math.random() * knMax) + 2;
            } while (k >= n);
            int type = (int) (Math.random() * typeMax) + 5;
            int[] arr = randomArray(k, n, type);
            if (!check(arr, k, n, type)) {
                System.out.println("random arr error!");
            }
            int ans1 = right(arr, k, n);
            int ans2 = findNum2(arr, k, n);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }

}
