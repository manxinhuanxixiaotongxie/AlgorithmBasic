package leetcode.classic150;

import java.util.Arrays;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子中，评分更高的那个会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class Code135 {
    /**
     * 分组循环
     * 滑动窗口与双指针题单的  分组循环
     *
     * @param ratings
     * @return
     */
    public int candy2(int[] ratings) {
        int n = ratings.length;
        // 题目要求每个孩子至少分1颗糖果 先给每个孩子分一个糖果 后面就不用考虑这个要求了 可以给孩子至少0颗糖果

        /**
         *
         * 题目要求 相邻两个孩子评分更大的孩子会分得更多的糖果
         * 比如【1,2,3,4,5,4,3,2】 由严格递增段【1,2,3,4】 + 峰顶 + 严格递减段【4，3，2】组成
         * 对于严格递增段 最左边的孩子旁边没有评分比他小的孩子 所以给他0颗糖果就行（我们在一开始的时候就给每个孩子分了一颗糖果）
         * 下一个孩子的糖果必须比上一个孩子的多 贪心的 多1就行 所以分得糖果数等差数列 0，1，2，3
         * 对于严格递减段 最右边的孩子旁边没有评分比他小的孩子 所以给他0颗糖果就行（我们在一开始的时候就给每个孩子分了一颗糖果）
         * 向左看 左边孩子的糖果 必须比右边孩子多 贪心地 多1就行   所以分给严格递减段的糖果的数为 2 1 0
         * 最后确认峰顶的糖果数 必须比严格递增段的最大值多 即>=4 也必须比严格递减段的最大值多 即>=3 取max(4,3) = 4作为峰顶的糖果数
         *
         *
         * 把严格递增段 + 峰顶 + 严格递减段叫做一座山
         *
         * 如果数组只有一座山 计算糖果总数总是简单的  但是如果有多坐山呢？如何找到每座山
         *
         * 分组循环：
         * 适用场景：按照题目要求 数组会被分割成如果干段 递增段 + 峰顶 + 递减段 这样的结构 需要对每个结构进行相同的处理
         *
         * 核心思想：
         * （1）外层循环负责遍历组之前的准备工作（记录开始位置） 和遍历组之后的统计工作（更新答案）
         * （2）内层循环负责遍历组 找到这一组最远在哪里结束
         *
         * 这个写法的好处是，各个逻辑块分工明确，也不需要特判最后一组（易错点）。以我的经验，这个写法是所有写法中最不容易出 bug 的，推荐大家记住。
         *
         *
         */
        int ans = n;
        for (int i = 0; i < n; i++) {
            // 谷底会被两组共享 保证每个山底都能被正确处理
            int start = i > 0 && ratings[i - 1] < ratings[i] ? i - 1 : i;

            // 找严格递增段
            while (i + 1 < n && ratings[i] < ratings[i + 1]) {
                i++;
            }

            int top = i; // 峰顶

            // 找严格递减段
            while (i + 1 < n && ratings[i] > ratings[i + 1]) {
                i++;
            }

            int inc = top - start; // start 到 top 严格递增
            int dec = i - top;     // top 到 i 严格递减
            // 0 + 1 + 2 + 3 + top- start -i
            ans += (inc * (inc - 1) + dec * (dec - 1)) / 2 + Math.max(inc, dec);
        }
        return ans;
    }

    public int candy1(int[] ratings) {
        int n = ratings.length;
        int[] help = new int[n];
        // 初始分配
        Arrays.fill(help, 1);
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                help[i] = help[i - 1] + 1;
            }
        }
        // 再处理递减
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                help[i] = Math.max(help[i], help[i + 1] + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += help[i];
        }
        return ans;
    }
}
