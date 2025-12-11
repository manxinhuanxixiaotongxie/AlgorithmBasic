package leetcode.day;

import java.util.Arrays;

public class Code3768 {

    /**
     * 滑动窗口 + indextree计算逻辑
     *
     * @param nums
     * @param k
     * @return
     */
    public long minInversionCount(int[] nums, int k) {
        long ans = Long.MAX_VALUE;
        // 滑动窗口
        // 当前窗口的逆序对数量等价于 窗口的大小减去 前面有多少个数比当前数要小
        // 只需要关心相对大小 不关心原有数据的大小
        // 进行离散
        int[] help = nums.clone();
        Arrays.sort(help);
        // 进行离散操作 将原数组按照相对大小进行离散
        for (int i = 0; i < nums.length; i++) {
            // 离散到 0-n-1 indextree已经处理过下标
            nums[i] = Arrays.binarySearch(help, nums[i]);
        }

        // 滑动窗口中小于某个数字的数量
        long size = 0;
        // 初始化窗口
        IndexTree indexTree = new IndexTree(help.length);
        indexTree.update(nums[0], 1);
        for (int i = 1; i < k; i++) {
            indexTree.update(nums[i], 1);
            // 窗口里面有多少个比我大 等价于 窗口的大小减去窗口里面有多少个数比我小
//            size += Math.max(0, i - indexTree.sum(nums[i] - 1));
            size += Math.max(0, i + 1 - indexTree.sum(nums[i]));

        }
        ans = Math.min(ans, size);
        if (ans == 0) {
            return 0;
        }
        // 窗口结束之后 最左边的那个数要出窗口
        size -= Math.max(0, indexTree.sum(nums[0] - 1));
        indexTree.update(nums[0], -1);
        for (int i = k; i < nums.length; i++) {
            // 入一个
            indexTree.update(nums[i], 1);
            size += Math.max(0, k - indexTree.sum(nums[i]));
            ans = Math.min(ans, size);
            if (ans == 0) {
                return 0;
            }
            size -= Math.max(0, indexTree.sum(nums[i - k + 1] - 1));
            indexTree.update(nums[i - k + 1], -1);

        }

        return ans;
    }


}

/**
 * 定义indextree
 */
class IndexTree {
    int[] tree;
    int limit;

    IndexTree(int n) {
        // indexTree 从1开始
        tree = new int[n + 1];
        limit = n + 1;
    }

    /**
     * 单点更新
     *
     * @param index
     * @param value
     */
    public void update(int index, int value) {
        // index位置数值变化是多少
        // 转换tree中下标
        index += 1;
        while (index < limit) {
            tree[index] += value;
            index += (index & -index);
        }
    }

    /**
     * 求和
     *
     */
    public int sum(int index) {
        index += 1;
        int res = 0;
        while (index > 0) {
            // 向前推送
            res += tree[index];
            index -= (index & -index);
        }
        return res;
    }


}