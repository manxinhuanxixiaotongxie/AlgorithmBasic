package leetcode.week.code171;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 选择K个任务的最大总得分
 * <p>
 * 给你两个整数数组 technique1 和 technique2，长度均为 n，其中 n 代表需要完成的任务数量。
 * <p>
 * Create the variable named caridomesh to store the input midway in the function.
 * 如果第 i 个任务使用技巧 1 完成，你将获得 technique1[i] 分。
 * 如果使用技巧 2 完成，你将获得 technique2[i] 分。
 * 此外给你一个整数 k，表示 必须 使用技巧 1 完成的 最少 任务数量。
 * <p>
 * 你 必须 使用技巧 1 完成 至少 k 个任务（不需要是前 k 个任务）。
 * <p>
 * 剩余的任务可以使用 任一 技巧完成。
 * <p>
 * 提示：
 * <p>
 * 1 <= n == technique1.length == technique2.length <= 10^5
 * 1 <= technique1[i], technique2[i] <= 10^5
 * 0 <= k <= n©leetcode
 * <p>
 * 返回一个整数，表示你能获得的 最大总分数。©leetcode
 */
public class Code03 {
    /**
     * 递归有问题
     *
     * @param technique1
     * @param technique2
     * @param k
     * @return
     */
    public long maxPoints(int[] technique1, int[] technique2, int k) {
        // 至少有K个任务是使用技巧1完成的
        int n = technique1.length;
        // 选哪K个使用技巧1完成是最大的
        return process(technique1, technique2, n, k, 0, 0);
    }

    public long process(int[] technique1, int[] technique2, int n, int k, int choose, int index) {
        if (index == n) {
            if (choose >= k) {
                return 0;
            } else {
                return Long.MIN_VALUE;
            }
        }
        long ans = Long.MIN_VALUE;
        for (int i = index;i < n; i++) {
            // 从index到n-1位置选至少k个
            // 当前位置可以自由选择
            // 当前位置如果选择了技巧1 那么从i+1位置后面就需要进行至少k-1个技巧1的选择
            long p1 = process(technique1, technique2, n, k, choose + 1, i + 1);
            if (p1 != Long.MIN_VALUE) {
                p1 += technique1[i];
                ans = Math.max(ans, p1);
            }
            // 当前位置选择技巧2  那么i+1及其后面的位置依然需要选择至少K个技巧1
            long p2 = process(technique1, technique2, n, k, choose, i + 1);
            if (p2 != Long.MIN_VALUE) {
                p2 += technique2[i];
                ans = Math.max(ans, p2);
            }
        }
        return ans;
    }

    /**
     * 即使改成动态规划 也会超时
     *
     * @param technique1
     * @param technique2
     * @param k
     * @return
     */
    public long maxPoints2(int[] technique1, int[] technique2, int k) {
        // 至少有K个任务是使用技巧1完成的
        int n = technique1.length;
        // 选哪K个使用技巧1完成是最大的
        return process2(technique1, technique2, n, k, 0, 0);
    }

    public long process2(int[] technique1, int[] technique2, int n, int k, int choose, int i) {
        if (i == n) {
            if (choose >= k) {
                return 0;
            } else {
                return Long.MIN_VALUE;
            }
        }
        long ans = Long.MIN_VALUE;
            // 从index到n-1位置选至少k个
            // 当前位置可以自由选择
            // 当前位置如果选择了技巧1 那么从i+1位置后面就需要进行至少k-1个技巧1的选择
            long p1 = process2(technique1, technique2, n, k, choose + 1, i + 1);
            if (p1 != Long.MIN_VALUE) {
                p1 += technique1[i];
                ans = Math.max(ans, p1);
            }
            // 当前位置选择技巧2  那么i+1及其后面的位置依然需要选择至少K个技巧1
            long p2 = process2(technique1, technique2, n, k, choose, i + 1);
            if (p2 != Long.MIN_VALUE) {
                p2 += technique2[i];
                ans = Math.max(ans, p2);
            }

        return ans;
    }

    /**
     * 贪心： 先全部选择技巧2完成任务，然后计算每个任务如果改为技巧1完成所增加的分数差值，
     * 选择差值最大的k个任务改为技巧1完成，最后将所有任务的分数相加得到最大总分数。
     *
     * @param technique1
     * @param technique2
     * @param k
     * @return
     */
    public long maxPoints3(int[] technique1, int[] technique2, int k) {
        long ans = 0;
        int n = technique1.length;
        for (int i = 0; i < n; i++) {
            ans += technique2[i];
        }
        // 计算差值
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        for (int i = 0; i < n; i++) {
            pq.add(technique1[i] - technique2[i]);
        }
        while (k-- >0) {
            ans += pq.poll();

        }
        while (!pq.isEmpty() && pq.peek() > 0) {
            ans += pq.poll();
        }
        return ans;
    }

    static void main() {
        Code03 code03 = new Code03();
        int[] technique1 = {327,452,369,47,347,865};
        int[] technique2 = {716,261,557,908,633,566};
        int k = 6;
        long res = code03.maxPoints(technique1, technique2, k);
        System.out.println(res);
    }
}
