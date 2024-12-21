package leetcode.practice;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 arr。你可以从中选出一个整数集合，并删除这些整数在数组中的每次出现。
 * <p>
 * 返回 至少 能删除数组中的一半整数的整数集合的最小大小。
 */
public class Code1338 {

    public int minSetSize(int[] arr) {

        Map<Integer, Integer> freq = new HashMap<>();
        for (int x : arr) {
            freq.merge(x, 1, Integer::sum); // freq[x]++
        }

        List<Integer> cnt = new ArrayList<>(freq.values());
        cnt.sort((a, b) -> b - a);

        int s = 0;
        for (int i = 0; ; i++) {
            s += cnt.get(i);
            if (s >= arr.length / 2) {
                return i + 1;
            }
        }
    }


    public static void main(String[] args) {
        Code1338 code1338 = new Code1338();
        int[] arr = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7};
        System.out.println(code1338.minSetSize(arr));
    }
}
