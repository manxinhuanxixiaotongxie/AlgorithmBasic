package systemimprove.dp.fromrecursiontpdp;

/**
 * N皇后问题是指在N*N的棋盘上要摆N个皇后，
 * 要求任何两个皇后不同行、不同列， 也不在同一条斜线上
 * 给定一个整数n，返回n皇后的摆法有多少种。n=1，返回1
 * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
 * n=8，返回92
 */
public class Code22_NQueens {
    public static int nums1(int n) {
        // 返回皇后的摆法有多少种
        if (n < 3) {
            return 0;
        }
        return process(new int[n], 0, n);
    }

    // record标识在多少行的位置上的皇后放在了N列
    // record[5] = 4  第五行的皇后放在了4列 下标从0开始 实际是第六行的皇后放在了第5列
    // i标识当前可选的皇后来到了多少行
    // N是固定值 代表行数
    public static int process(int[] record, int i, int N) {
        if (i == N) {
            // 表示当前选择的方案已经找到了一种有效的摆放方式
            return 1;
        } else {
            // 在i行进行选择
            // 从第0列开始选
            // 但是要满足的条件
            int ans = 0;
            for (int j = 0; j < N; j++) {
                // 如果在i行的j列上可以摆放皇后
                if (isValid(record, i, j)) {
                    record[i] = j;
                    ans += process(record, i + 1, N);
                }
            }
            return ans;
        }
    }

    /**
     * 介绍一种目前为止比较优良的方法
     *
     * @param n
     * @return
     */
    public static int totalNQueens(int n) {
        // 返回皇后的摆法有多少种
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    public static int process2(int limit, int colLimit, int colLeftLimit, int colRightLimit) {
        if (colLimit == limit) {
            return 1;
        }
        // 将三个数取反 是皇后不可以放置的位置
        int fan = colLimit | colLeftLimit | colRightLimit;
        // 将这三个数取反 再与上limit位置为1就意味着是可以放置皇后的
        int pos = limit & (~fan);
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLimit | mostRightOne, (colLeftLimit | mostRightOne) << 1,
                    (colRightLimit | mostRightOne) >>> 1);
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j) {
        // 不能共行
        // 遍历每一行
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 15;

        long start = System.currentTimeMillis();
        System.out.println(nums1(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(totalNQueens(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
