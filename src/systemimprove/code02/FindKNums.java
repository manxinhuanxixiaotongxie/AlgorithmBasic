package systemimprove.code02;

import java.util.HashMap;
import java.util.Map;

/**
 * 一个数出现了K次 其他数出现了M次
 * 要求找到这个数
 * 时间复杂度O(N) 空间复杂度O(1)
 * m > 1 k < m
 */
public class FindKNums {

    public static HashMap<Integer, Integer> map = new HashMap<>();

    public int findKNum(int[] arr, int k, int m) {
        if (map.size() == 0) {
            mapCreater(map);
        }

        int[] t = new int[32];

        while (k != 0) {
            int rightOne = k & (~k + 1);
            t[map.get(rightOne)]++;
            k ^= rightOne;
        }

        int ans = 0;

        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                ans |= (1 << i);
            }
        }

        return ans;

    }

    // 进行位数转换
    // 给一个数进行位数转换
    public void mapCreater(Map<Integer, Integer> map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
    }

    public int findNum2(int[] arr, int k, int m) {
        int[] t = new int[32];
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

}
