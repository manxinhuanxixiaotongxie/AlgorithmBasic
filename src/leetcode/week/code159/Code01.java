package leetcode.week.code159;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个由互不相同的整数组成的数组 nums 。
 *
 * 在一次操作中，你可以交换任意两个 相邻 元素。
 *
 * 在一个排列中，当所有相邻元素的奇偶性交替出现，我们认为该排列是 有效排列。这意味着每对相邻元素中一个是偶数，一个是奇数。
 *
 * 请返回将 nums 变成任意一种 有效排列 所需的最小相邻交换次数。
 *
 * 如果无法重排 nums 来获得有效排列，则返回 -1。
 *
 */
public class Code01 {
    /**
     * 灵神的题解：
     * 把偶数视为空位 奇数视作汽车
     *
     * 怎么交换 只需要关注汽车 当所有的汽车都移动到偶数(奇数)下标 所有空位就一定位于奇数(偶数)下标
     *
     * 比如汽车的位置为 a=[3,4,5]，目标位置为 b=[1,3,5]。贪心地，最左边的汽车移动到最左边的目标位置，比移动到其他位置更优。
     * 如果最左边的汽车没有移动到最左边的目标位置，那么其他更靠右的汽车就要移动到最左边，这样移动的总距离是更大的。
     *
     * 计算每辆车 a[i] 到其目标位置 b[i] 的距离 ∣a[i]−b[i]∣，累加即为交换次数。
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/minimum-adjacent-swaps-to-alternate-parity/solutions/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * @param nums
     * @return
     */
    public int minSwaps(int[] nums) {
        int n = nums.length;
        List<Integer> pos1 = new ArrayList<>(); // 车（奇数）的下标
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 != 0) {
                pos1.add(i);
            }
        }

        int ans = Math.min(calc(0, pos1, n), calc(1, pos1, n));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // start=0 表示车要去偶数下标，start=1 表示车要去奇数下标
    // 车子要去的位置可能是 0 2 4 6 ... 或者 1 3 5 7 ...
    private int calc(int start, List<Integer> pos1, int n) {
        // (n-start+1)/2 表示偶数（奇数）下标的个数
        // pos1.size() 表示车子（奇数）的个数
        // 奇数的个数一定要是原始数组长度的一半
        // 如果不是一半 那么就无法重排成有效排列
        // 分情况讨论：
        // 第一种情况：
        // n是偶数 奇数要去的下标是0 2 4 那么奇数的长度 要么是长度的一半
        // 第二种情况：
        // n是奇数 奇数的下标要去的位置是 1 3 5 7.。。 那么奇数的常数是长度的一半+1
        if ((n - start + 1) / 2 != pos1.size()) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        // 当前汽车将要去的位置的下标：
        // 当前车子的下标是pos1.get(i)，
        // 将要去位置是  如果start是0 那么将要去的位置的0 2 4 6 ... 如果start是1 那么将要去的位置的1 3 5 7 ...
        // 移动的距离是 |i * 2 + start - pos1.get(i)|
        for (int i = 0; i < pos1.size(); i++) {
            res += Math.abs(i * 2 + start - pos1.get(i));
        }
        return res;
    }

}
