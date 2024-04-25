package systemimprove.code20;

/**
 * 跳马问题
 * 给定一个棋盘，棋盘上有一个马，马要去往目标位置，马只能走日字
 * 最终马的位置必须要落在目标位置上
 * 要走k步，求有多少种走法
 * <p>
 * 请同学们自行搜索或者想象一个象棋的棋盘，
 * 然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
 * 那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
 * 给你三个 参数 x，y，k
 * 返回“马”从(0,0)位置出发，必须走k步
 * 最后落在(x,y)上的方法数有多少种?
 */
public class Code02_HorseJump {

    public int jump(int a, int b, int k) {
        return process(0, 0, k, a, b);
    }

    /**
     * 设计一个递归函数 作用是从x y位置 到达a b位置 总共需要走K步有多少种走法
     *
     * @param x
     * @param y
     * @param k
     * @param a
     * @param b
     * @return
     */
    public int process(int x, int y, int k, int a, int b) {
        // x是横坐标 y是纵坐标 x位置有9条线 那么x的范围是0-8 y的范围是0-9
        if (x < 0 || y < 0 || x > 8 || y > 9) {
            return 0;
        }
        if (k == 0) {
            return x == a && y == b ? 1 : 0;
        }
        int ways = process(x + 1, y + 2, k - 1, a, b);
        ways += process(x + 2, y + 1, k - 1, a, b);
        ways += process(x + 2, y - 1, k - 1, a, b);
        ways += process(x + 1, y - 2, k - 1, a, b);
        ways += process(x - 1, y - 2, k - 1, a, b);
        ways += process(x - 2, y - 1, k - 1, a, b);
        ways += process(x - 2, y + 1, k - 1, a, b);
        ways += process(x - 1, y + 2, k - 1, a, b);
        return ways;
    }

    public int jump2(int a, int b, int k) {
        // 分析位置依赖
        // 变化的的参数有 x y k 三个参数 三维数据
        int[][][] dp = new int[9][10][k + 1];
        dp[a][b][0] = 1;
        for (int i = 1; i <=k;i ++) {
            for (int j = 0;j < 9;j++) {
                for (int l = 0;l < 10;l++) {
                    int ways = pick(dp,j+1,l+2,i-1);
                    ways += pick(dp,j+2,l+1,i-1);
                    ways += pick(dp,j+2,l-1,i-1);
                    ways += pick(dp,j+1,l-2,i-1);
                    ways += pick(dp,j-1,l-2,i-1);
                    ways += pick(dp,j-2,l-1,i-1);
                    ways += pick(dp,j-2,l+1,i-1);
                    ways += pick(dp,j-1,l+2,i-1);
                    dp[j][l][i] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public int pick(int[][][] dp,int x,int y,int k) {
        if (x > 8 || x < 0 || y > 9 || y < 0) {
            return 0;
        }
        return dp[x][y][k];
    }

    public static void main(String[] args) {
        Code02_HorseJump horseJump = new Code02_HorseJump();
        System.out.println(horseJump.jump(7, 7, 10));
        System.out.println(horseJump.jump2(7, 7, 10));
    }
}
