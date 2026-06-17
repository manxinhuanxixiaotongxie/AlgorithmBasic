package leetcode.week.code506;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个整数数组 nums。
 * <p>
 * 定义 频率平衡 子数组 如下：
 * <p>
 * 如果子数组只包含 一个 元素，则它是频率平衡的。
 * 否则，必然存在一个正整数 f，使得子数组中的每个不同值出现的次数要么是 f，要么是 2 * f，并且这两种 频率 都在不同值中出现。
 * 返回一个整数，表示 最长 频率平衡子数组的长度。
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^3
 * 1 <= nums[i] <= 10^9
 */
public class Code02 {
    /**
     * 超时
     *
     * @param nums
     * @return
     */
    public int getLength(int[] nums) {
        int ans = 1; // 单个元素必然是 1，所以初始值为 1
        int len = nums.length;

        // i 是左边界，j 是右边界
        for (int i = 0; i < len; i++) {
            // 注意：j 应该从 i + 1 开始枚举，因为单个元素（j == i）直接符合条件（长度为1）
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int j = i; j < len; j++) {
                // 统计子数组 [i, j] 的元素频数
                countMap.put(nums[j], countMap.getOrDefault(nums[j], 0) + 1);

                // 快速校验频数是否满足严格的 f 和 2f 关系
                Set<Integer> uniqueFreqs = new HashSet<>(countMap.values());

                if (uniqueFreqs.size() == 2) {
                    List<Integer> freqList = new ArrayList<>(uniqueFreqs);
                    int f1 = freqList.get(0);
                    int f2 = freqList.get(1);

                    int f = Math.min(f1, f2);
                    int doubleF = Math.max(f1, f2);

                    if (doubleF == 2 * f) {
                        // 满足条件，更新最大长度（注意是子数组长度：j - i + 1）
                        ans = Math.max(ans, j - i + 1);
                    }
                } else if (countMap.size() == 1) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 思路：
     * 由于n <1000 我们可以枚举子数组的左右断点
     * 外层循环枚举左端点 内层循环枚举右端点 不断向右扩大子数组长度
     * 在扩大子数组的过程中 维护子数组的元素出现次数cnt 以及元素的出现次数的出现次数cc 后者用来表达【多重集】
     * 比如【1,2,2】
     * 1.子数组只包含一种元素，等价于cnt的大小为1
     * 2.子数组恰好有两种不同的出现次数，等价于cc的大小为2 此外 这两种出现次数 一个必须是另外一个的两倍
     *
     * @param nums
     * @return
     */
    public int getLength2(int[] nums) {
        int ans = 0; // 单个元素必然是 1，所以初始值为 1
        int len = nums.length;
        // 窗口
        // O(n ^2)解法
        // 怎么在O（1）判断窗口是否有效
        // 当前值的数量是多少个 key 1 1   值为1 的数量为1个

        // 同时维护频数分布（频数：出现该频数的个数）O（1）判断是否满足条件
        // [1,2,2,3,3]  ==> countMap [1:1,2:2,3:2]  cc ==>[2:2,1:1]
        // 含义：1 出现了一次 2 出现了2次 3出现了2次 那么出现了2次的数有两个 一个是2 一个是3 出现了次数为1 只有一个 那就是1

        // 怎么判断是否是符合要求的
        for (int i = 0; i < len; i++) {
            // 当前位置开头
            Map<Integer, Integer> countMap = new HashMap<>();
            // 出现1次的数有多少个 出现2次的数有多少个 出现3次的数有多少个
            /**
             * 用点名进行举例
             * 小明举了三次手   countMap 小明：3
             * 小李举了三次手   countMap 小李：3
             *
             * 举了三次手 有两个人：小李 小明
             * 现在要知道窗口：所有人的举手次数只能是f次 或者是2*f次 并且两种都要有人
             */
            Map<Integer, Integer> cc = new HashMap<>();
            for (int j = i; j < len; j++) {
                // j出现的次数
                int x = nums[j];
                // 这个值出现了多少次
                Integer c = countMap.get(x);
                if (c != null) {
                    // 3出现的次数已经是2次，那么出现次数是2的数就存在 这次犹豫3的出现次数会变成3次 那么这个数对应的次数的次数就会变成4
                    // 要清除之前数的影响
                    if (cc.merge(c, -1, Integer::sum) == 0) {
                        cc.remove(c);
                    }
                }
                c = countMap.merge(x, 1, Integer::sum);
                // 次数的次数
                cc.merge(c, 1, Integer::sum);
                if (countMap.size() == 1) {
                    ans = Math.max(ans, j - i + 1);
                } else if (cc.size() == 2) {
                    Iterator<Integer> it = cc.keySet().iterator();
                    int c1 = it.next();
                    int c2 = it.next();
                    if (Math.min(c1, c2) * 2 == Math.max(c1, c2)) {
                        ans = Math.max(ans, j - i + 1);
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 如果以i开头  n-i的长度小于ans 根本就不需要遍历
     *
     * @param nums
     * @return
     */
    public int getLength3(int[] nums) {
        int n = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        if (set.size() == n) {
            // 没有相同的数字
            return 1;
        }
        // 有相同的数字
        int ans = 0;
        int same = 0;
        for (int i = 0; i < n; i++) {
            same++;
            if (i == n-1 || nums[i] != nums[i+1]) {
                ans = Math.max(ans, same);
                same = 0;
            }
        }
        // 经过这一个步骤处理之后 不需要再处理相同的数字了

        for (int i = 0; i < n - ans; i++) {
            // 维持窗口
            // 出现的次数
            Map<Integer, Integer> countMap = new HashMap<>();
            // 次数的次数
            Map<Integer, Integer> countCountMap = new HashMap<>();
            for (int j = i; j < n; j++) {
                int x = nums[j];
                Integer c = countMap.get(x);
                if (c != null) {
                    // 清除影响
                    if (countCountMap.merge(c, -1, Integer::sum) == 0) {
                        countCountMap.remove(c);
                    }
                }
                c = countMap.merge(x, 1, Integer::sum);
                countCountMap.merge(c, 1, Integer::sum);
                if (countCountMap.size() == 2) {
                    Iterator<Integer> it = countCountMap.keySet().iterator();
                    int c1 = it.next();
                    int c2 = it.next();
                    if (Math.min(c1, c2) * 2 == Math.max(c1, c2)) {
                        ans = Math.max(ans, j - i + 1);
                    }
                }
            }
        }

        return ans;
    }

    static void main() {
        int[] nums = new int[]{1, 2, 2, 1, 2, 3, 3, 3};
        int length = new Code02().getLength2(nums);
        System.out.printf("%d\n", length);
    }
}
