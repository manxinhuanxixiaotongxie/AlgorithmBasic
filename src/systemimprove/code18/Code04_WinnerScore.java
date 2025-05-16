package systemimprove.code18;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 * <p>
 * 零和博弈的问题：
 * 1.玩家都绝顶聪明 从这个角度入手
 * 两个玩家都会按照最优的策略来拿牌
 * 那么对于先手玩家来说 他的最佳策略就是拿到最大的分数
 * 怎么拿到这个最佳分数呢 如果先手能拿到的范围是L-R那么 先手的策略有两种  一种是arr[L] + 后手（L+1，R）  一种数arr[R] + 后手（L，R-1）
 * 对于先手玩家来说 他有这两种选择 那么他肯定会选择这两者的最大值最为自己的选择
 * <p>
 * 对于后说来说
 */
public class Code04_WinnerScore {

    public int winnerScore1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        /**
         * 主函数为什么这么调用 是不是可以这么理解
         * f1是先手函数 在范围内能拿到最大分数
         * g1是后手函数 在范围内能拿到最大分数
         * 要求的是先手在L-R范围内能拿到的最大分数
         */
        System.out.printf("f1(0, arr.length - 1, arr) = %d\n", f1(0, arr.length - 1, arr));
        System.out.printf("g1(0, arr.length - 1, arr) = %d\n", g1(0, arr.length - 1, arr));
        return Math.max(f1(0, arr.length - 1, arr), g1(0, arr.length - 1, arr));
    }

    /**
     * 作为先手 在L+R范围上 能获取到的最好的分数
     * <p>
     * 从这个题目是不是可以这么进行处理暴力递归
     * <p>
     * 其实就是站在上帝视角 进行任务的划分
     * <p>
     * 以这个题目为例：
     * 在选择的过程中 怎么体现绝顶聪明 实际就是说 作为一个先手函数 我应该怎么选择对我比较有利
     *
     * @param L
     * @param R
     * @param arr
     * @return
     */
    public int f1(int L, int R, int[] arr) {
        // 对于先手来说 当L==R的时候  作为先手会将剩下的一个拿走  能得到的分数为arr[L]或者arr[R]
        if (L == R) {
            return arr[L];
        }
        // 站在上帝视角看 从L-R怎么进行选择
        // 第一种方案 选择L+g1(L+1,R) 作为先手 将L位置选走  那么后序就是后手在L+1-R上的最好分数
        int p1 = arr[L] + g1(L + 1, R, arr);
        // 第二种方案 选择R+g1(L,R-1) 作为先手 将R位置选走  那么后序就是后手在L-R-1上的最好分数
        int p2 = arr[R] + g1(L, R - 1, arr);
        // 由于先手函数绝顶聪明 他一定会选择最大的那个
        return Math.max(p1, p2);
    }

    /**
     * 作为后手在L-R上能获取到的最好的分数
     *
     * @return
     */
    public int g1(int L, int R, int[] arr) {
        // 作为后手在L==R的时候 无法进行选择 这个时候先手已经将最后一个拿走了
        if (L == R) {
            return 0;
        }
        // 站在上帝视角上 作为一个后手函数 如果要在一个给定的L+R上进行选择
        // 那么作为一个后手函数 实际上是被先手已经挑剩了的
        // 那么后手只能选择先手留下的两种情况
        // 1.先手选择了L  那么后手只能选择L+1-R
        int p1 = f1(L + 1, R, arr);
        // 2.先手选择了R  那么后手只能选择L-R-1
        int p2 = f1(L, R - 1, arr);
        // 作为后手函数 没得选 因为先手函数会留下最差的情况给后手
        return Math.min(p1, p2);
    }

    public int winnerScore2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] g = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                f[i][j] = -1;
                g[i][j] = -1;
            }
        }
        return Math.max(f2(0, N - 1, arr, f, g), g2(0, N - 1, arr, f, g));
    }


    public int f2(int L, int R, int[] arr, int[][] f, int[][] g) {
        if (f[L][R] != -1) {
            return f[L][R];
        }
        int ans;
        if (L == R) {
            ans = f[L][R];
        } else {
            int p1 = arr[L] + g2(L + 1, R, arr, f, g);
            int p2 = arr[R] + g2(L, R - 1, arr, f, g);
            ans = Math.max(p1, p2);
        }
        f[L][R] = ans;
        return ans;

    }

    public int g2(int L, int R, int[] arr, int[][] f, int[][] g) {
        if (g[L][R] != -1) {
            return g[L][R];
        }
        int ans;
        if (L == R) {
            ans = 0;
        } else {
            int p1 = f1(L + 1, R, arr);
            int p2 = f1(L, R - 1, arr);
            ans = Math.min(p1, p2);
        }
        g[L][R] = ans;
        return ans;
    }

    public int winnerScore3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 分析严格意义的依赖关系
        // 两张表 一张是f表 一张是g表 分别使用L做行 R做列
        // 按照依赖关系 f函数依赖g函数对应位置的下方位置以及左边的位置
        // g函数依赖f函数的对应位置的下方位置以及左位置
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] g = new int[N][N];
        for (int i = 0; i < N; i++) {
            f[i][i] = arr[i];
        }

        // 实现一个功能是为了对角线向下爬的过程
        for (int i = 1; i < N; i++) {
            int row = 0;
            int col = i;
            while (col < N) {
                f[row][col] = Math.max(arr[row] + g[row + 1][col], arr[col] + g[row][col - 1]);
                g[row][col] = Math.min(f[row + 1][col], f[row][col - 1]);
                row++;
                col++;
            }
        }
        return Math.max(f[0][N - 1], g[0][N - 1]);
    }


    public static void main(String[] args) {
        Code04_WinnerScore winnerScore = new Code04_WinnerScore();
//        int[] arr = {100, 2, 100, 4};
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(winnerScore.winnerScore1(arr));
        System.out.println(winnerScore.winnerScore2(arr));
        System.out.println(winnerScore.winnerScore3(arr));
    }


}
