package systemimprove.code39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]，背包容量为w。
 * 牛牛想知道在总体积不超过背包容量的情况下,
 * 一共有多少种零食放法，体积为0也算一种放法
 * <p>
 * <p>
 * <p>
 * 1 <= n <= 30, 1 <= w <= 2 * 10^9
 * <p>
 * <p>
 * v[i] (0 <= v[i] <= 10^9）
 */
public class Code03_SnacksWaysMain1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
        PrintWriter out = new PrintWriter(System.out);
        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) streamTokenizer.nval;
            streamTokenizer.nextToken();
            int bag = (int) streamTokenizer.nval;
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                streamTokenizer.nextToken();
                arr[i] = (int) streamTokenizer.nval;
            }
            long ways = ways(arr, bag);
            out.println(ways);
            out.flush();
        }
    }

    public static long ways(int[] arr, int bag) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0] <= bag ? 2 : 1;
        }
        // 二分 int mid = (arr.length ) >> 1;  也对
        int mid = (arr.length - 1) >> 1;
        // 这里一定要使用Long类型   题目已经给定了  食物的体积可能会很大
        // 会导致int越界
        TreeMap<Long, Long> lmap = new TreeMap<>();
        long ways = 0;
        // 这里递归函数的返回 代表总共有多少种选择方式
        // 如果设计成没有返回值的话
        // 就需要遍历数组 然后对所有key的值进行累加
        ways += process(arr, 0, mid, 0, bag, lmap);
        TreeMap<Long, Long> rmap = new TreeMap<>();
        ways += process(arr, mid + 1, arr.length - 1, 0, bag, rmap);

        // 左右两边进行组合
        TreeMap<Long, Long> rpre = new TreeMap<>();
        long pre = 0;
        /**
         * 这么做的含义是
         * 如果map里面有 5,3    6,3
         * 这两组key的话
         * 在遍历之后
         * rpre就会变成 5,3   6 ，6
         * 为什么要这么处理呢
         * 因为集合最终要合并 组成的方式是什么
         * 在o-mid范围上已经搞定了lmap的某个值 假设总共有K种方法的
         * 那么的总共搞定bag就是 需要在bag种找到  bag-lmap的某个值 并且是小于等于这个值的所有个数
         * 两者进行排列组合 得到最终的答案
         */
        for (HashMap.Entry<Long, Long> entry : rmap.entrySet()) {
            pre += entry.getValue();
            rpre.put(entry.getKey(), pre);
        }

        for (HashMap.Entry<Long, Long> entry : lmap.entrySet()) {
            long lweight = entry.getKey();
            long lways = entry.getValue();
            Long floor = rpre.floorKey(bag - lweight);
            if (floor != null) {
                long rways = rpre.get(floor);
                ways += lways * rways;
            }
        }

        /**
         *
         *
         *         如果考虑0的话  就得这么写
         *         long Lpre = 0;
         *         for (HashMap.Entry<Long, Long> entry : lmap.entrySet()) {
         *             Lpre += entry.getValue();
         *         }
         *         return ways - Lpre - pre;
         *
         *
         */

        return ways + 1;
    }

    /**
     * 在index到end范围上找到满足sum的组合数
     *
     * @param arr
     * @param index
     * @param end
     * @param sum
     * @param bag
     */
    public static long process(int[] arr, int index, int end, long sum, long bag, TreeMap<Long, Long> map) {
        if (sum > bag) {
            return 0;
        }
        if (index > end) {
            /**
             * 这里很关键
             * 为什么的要判断0？
             * 不这么判断的话 会导致多算
             * 为什么会多算
             * 本身在左边范围上搞定（0-sum]的方法数已经计算上了单独搞定sum 右边不需要选择的场景
             * 同理在右边进行判断的时候也计算了左边不需要选的时候
             * 如果将0不单独拎出来 在左右两边合并的时候 就会多算 左边单独搞定sum的情形
             * 多算的情况:
             * 1.当左边是搞定 0 的时候  右边搞定sum的时候已经计算过了
             * 在相乘的时候多算了 0-1  0-2 0-3  0-4  0-5  0-6 0-sum
             * 2.左边搞定sum的时候 右边搞定0的时候相加的时候已经算过了
             * 在相乘的时候 会多算 1-0 2-0 3-0 4-0 5-0 6-0 sum-0
             *
             * 两边都是0是在哪里处理的呢？
             *
             * 两边都是0在相加的时候多算了一个0
             * 但是在相乘的时候左边减去了一个00 右边又减去了一个00
             *
             *
             *
             *
             *
             */
            if (sum != 0) {
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1L);
                }
                return 1;
            } else {
                return 0;
            }
        } else {
            // 第一种情况 什么都不选
            long ways = process(arr, index + 1, end, sum, bag, map);
            // 选择当前的零食
            ways += process(arr, index + 1, end, sum + arr[index], bag, map);
            return ways;
        }
    }
}
