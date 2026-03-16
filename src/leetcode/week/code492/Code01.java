package leetcode.week.code492;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 给你一个整数数组 capacity，其中 capacity[i] 表示第 i 个箱子的容量，以及一个整数 itemSize，表示一个物品的大小。
 *
 * 如果第 i 个箱子的容量满足 capacity[i] >= itemSize，那么该箱子可以存放该物品。
 *
 * 要求返回可以存放该物品的容量 最小 的箱子的下标。如果有多个这样的箱子，返回下标 最小 的一个。
 *
 * 如果没有任何箱子可以存放该物品，则返回 -1。
 *
 */
public class Code01 {
    public int minimumIndex(int[] capacity, int itemSize) {
        int ans = -1;
        Integer[][] arr = new Integer[capacity.length][2];
        for (int i = 0; i < capacity.length; i++) {
            arr[i][0] = capacity[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, (o1, o2) -> Objects.equals(o1[0], o2[0]) ? o1[1] - o2[1] : o1[0] - o2[0]);
        for (int i = 0; i < capacity.length; i++) {
            if (arr[i][0] >=itemSize) {
                ans = arr[i][1];
                break;
            }
        }
        return ans;
    }
}
