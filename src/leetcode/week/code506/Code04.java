package leetcode.week.code506;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 你可以对数组执行 至多 k 次交换操作。
 * <p>
 * 在一次交换操作中，你可以选择任意两个下标 i 和 j 并交换 nums[i] 和 nums[j]。
 * <p>
 * 返回一个整数，表示在执行交换后 可能的最大 子数组 和。
 * 提示：
 * <p>
 * 1 <= nums.length <= 1500
 * -10^5 <= nums[i] <= 10^5
 * 0 <= k <= nums.length
 */
public class Code04 {
    public long maxSum(int[] nums, int k) {
        long ans = Long.MIN_VALUE;
        for (int i = 0; i <= k; i++) {
            ans = Math.max(ans, process(nums, i));
        }

        return ans;
    }

    /**
     * 刚好执行k次交换
     *
     * @param nums
     * @param k
     * @return
     */
    public long process(int[] nums, int k) {
        // 可以任意交换
        if (k == 0) {
            return getMaxSubSum(nums);
        } else {
            long sum = getMaxSubSum(nums);
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    // 交换
                    swap(nums, i, j);
                    sum = Math.max(sum, process(nums, k - 1));
                    swap(nums, i, j);
                }
            }
            return sum;
        }
    }

    // 交换
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 最大子数组和
    public long getMaxSubSum(int[] nums) {
        // 必须以i位置开头
        int n = nums.length;
        long ans = nums[n - 1];
        long before = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (before > 0) {
                before += nums[i];
            } else {
                before = nums[i];
            }
            ans = Math.max(ans, before);
        }
        return ans;
    }


    /**
     * 使用indexTree优化
     * <p>
     * indexTree本质是求范围的和
     * 这道题目进行转换：
     * <p>
     * 枚举子数组的左右两个端点
     * 外层循环枚举左端点 内层循环枚举右端点 不断向右扩大子数组的长度
     * 贪心：把子数组内较小的数字与子数组外较大的数交换 用两个数据结构维护 分别维护：
     * （1）子数组内前K小元素和
     * （2）子数组外前k大元素和
     *
     */
    static class FenwickTree {
        private final int highBit;
        private final int[] sorted;
        private final int[] cnt;
        private final long[] sum;

        public FenwickTree(int[] sorted) {
            int n = sorted.length;
            highBit = 1 << (31 - Integer.numberOfLeadingZeros(n));
            this.sorted = sorted;
            cnt = new int[n + 1];
            sum = new long[n + 1];
        }

        // 添加 num 个 val，其中 val 离散化后的值为 i（i 从 1 开始）
        // 如果 num < 0，表示减少 -num 个 val
        public void update(int i, int num, int val) {
            for (; i < cnt.length; i += i & -i) {
                cnt[i] += num;
                sum[i] += val;
            }
        }

        // 返回第 k 小的数（k 从 1 开始）
        public int kth(int k) {
            int i = 0;
            for (int b = highBit; b > 0; b >>= 1) {
                int nxt = i | b;
                if (nxt < cnt.length && cnt[nxt] < k) {
                    k -= cnt[nxt];
                    i = nxt;
                }
            }
            return sorted[i];
        }

        // 返回前 k 小的数之和（k 从 1 开始）
        public long preSum(int k) {
            long s = 0;
            int i = 0;
            for (int b = highBit; b > 0; b >>= 1) {
                int nxt = i | b;
                if (nxt < cnt.length && cnt[nxt] < k) {
                    k -= cnt[nxt];
                    s += sum[nxt];
                    i = nxt;
                }
            }
            // 加上等于第 k 小的数
            return s + (long) sorted[i] * k;
        }

        public FenwickTree copy() {
            FenwickTree f = new FenwickTree(sorted);
            System.arraycopy(cnt, 0, f.cnt, 0, cnt.length);
            System.arraycopy(sum, 0, f.sum, 0, sum.length);
            return f;
        }
    }

    /**
     * 灵神题解
     * indexTree存的是次数  数字1出现了5次  tree[1] = 5
     *
     * @param nums
     * @param k
     * @return
     */
    public long maxSum2(int[] nums, int k) {
        // 离散化
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int[] rank = new int[n]; // rank[i] 是 nums[i] 离散化后的值（从 1 开始）
        FenwickTree allTree = new FenwickTree(sorted); // 包含所有元素的树状数组
        long total = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            rank[i] = Arrays.binarySearch(sorted, x) + 1;
            allTree.update(rank[i], 1, x);
            total += x;
        }

        long ans = Long.MIN_VALUE;

        // 枚举子数组左端点
        for (int left = 0; left < n; left++) {
            FenwickTree inTree = new FenwickTree(sorted);
            FenwickTree outTree = allTree.copy();
            int needSwap = 0;
            long subSum = 0;

            // 枚举子数组右端点
            for (int right = left; right < n; right++) {
                // x 从子数组外移到子数组内
                int x = nums[right];
                int rk = rank[right];
                subSum += x;
                inTree.update(rk, 1, x);
                outTree.update(rk, -1, -x);

                boolean inc = false;
                int sz = right - left + 1;
                if (needSwap < k && needSwap < sz && needSwap < n - sz) {
                    // 能否多交换一次
                    if (inTree.kth(needSwap + 1) < outTree.kth(n - sz - needSwap)) {
                        inc = true;
                        needSwap++;
                    }
                }

                if (!inc && needSwap > 0) {
                    // 是否要减少交换次数
                    if (inTree.kth(needSwap) >= outTree.kth(n - sz - needSwap + 1)) {
                        needSwap--;
                    }
                }

                // 计算通过交换导致的元素和的增量
                long delta = 0;
                if (needSwap > 0) {
                    long inSum = inTree.preSum(needSwap);
                    long outSum = total - subSum - outTree.preSum(n - sz - needSwap);
                    delta = outSum - inSum;
                }

                ans = Math.max(ans, subSum + delta);
            }
        }

        return ans;
    }


    static void main() {
        Code04 c = new Code04();
        int[] arr = {-1, -2};
        System.out.printf(String.valueOf(c.maxSum(arr, 0)));
    }
}
