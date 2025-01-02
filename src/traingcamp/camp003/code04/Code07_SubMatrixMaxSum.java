package traingcamp.camp003.code04;

/**
 * 给定一个整型矩阵，返回子矩阵的最大累计和。
 * <p>
 * 1.暴力解法
 * 暴力枚举所有子矩阵
 * <p>
 * 2.最优解
 * <p>
 * 第六个问题已经求解了一个子数组的最大累加和
 * <p>
 * 这个问题的思路：
 */
public class Code07_SubMatrixMaxSum {

    /**
     * 纯暴力解法
     *
     * @param m
     * @return
     */
    public static int maxSum1(int[][] m) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                // 第一个点 i j
                // 第二个点
                for (int i1 = i; i1 < m.length; i1++) {
                    for (int j1 = j; j1 < m[0].length; j1++) {
                        // i j  i1 j1
                        // 第二个点
                        // 求和第一个节点到第二个节点的和
                        // 求和
                        int tempI = i;
                        // i到i1行
                        int tempSum = 0;
                        while (tempI <= i1) {
                            for (int tempJ = j; tempJ <= j1; tempJ++) {
                                tempSum += m[tempI][tempJ];
                            }
                            ans = Math.max(ans, tempSum);
                            tempI++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 利用问题6的求解一个数组中最大累加和问题进行加速
     * <p>
     * 思路：
     * 求解0-0行最大累加和
     * 求解0-1行最大累加和
     * 求解0-2行最大累加和
     * 求解0-3行最大累加和
     * 求解0-4行最大累加和
     * 。。。
     * 求解0-n行最大累加和
     * <p>
     * 继续求解1-1行最大累加和
     * 继续求解1-2行最大累加和
     * 继续求解1-3行最大累加和
     * 。。
     * 继续求解1-n行最大累加和
     * <p>
     * 继续求解2-2行最大累加和
     * 继续求解2-3行最大累加和
     * <p>
     * <p>
     * 。。。
     * <p>
     * 答案必在其中
     * <p>
     * 已经枚举了所有的子矩阵
     *
     * @param m
     * @return
     */
    public static int maxSum2(int[][] m) {
        int ans = Integer.MIN_VALUE;
        int row = m.length;
        int col = m[0].length;
        for (int c = 0; c < row; c++) {
            int[] help = new int[col];
            for (int c1 = c; c1 < row; c1++) {
                int cur = 0;
                // 依次求解0-0 0-1的子矩阵的最大累加和
                for (int k = 0; k < col; k++) {
                    // 在一个数组上滚动更新
                    help[k] += m[c1][k];
                    cur += help[k];
                    ans = Math.max(ans, cur);
                    cur = Math.max(cur, 0);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] m = {
//                {1, 2, 3},
//                {0, -1, 2},
//                {1, 1, 1}
//        };
        int[][] m = {{-90, 48, 78}, {64, -40, 64}, {-81, -7, 66}};

        System.out.println(maxSum1(m));
        System.out.println(maxSum2(m));
    }
}
