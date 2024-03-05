package systemimprove.code17;

public class Code01_FiberArr {

    /**
     * 斐波那契数列的递推式
     * 使用递归的方式
     *
     * @param N
     * @return
     */
    public int fiber(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        return fiber(N - 1) + fiber(N - 2);
    }

    /**
     * 使用递推公式
     * 斐波那契数列遵从严格的递推公式
     * f(n) = f(n-1) + f(n-2)
     * 中间没有任何的条件限制
     * 因此使用
     * 利用线性代数的方式：
     * <p>
     * 即如果存在F(n) = F(n-1) + F(n-2)这种严格意义的关系的话
     * 那么他们的关系一定满足
     * | F(n) ,F(n-1) | = | F(n-1), F(n-2) | * | {}1, 1}，{1,0}|
     * <p>
     * 我们利用这种关系找到一个快速的计算方式：
     * | F(n) ,F(n-1) | = | F(n-1), F(n-2) | * | {}1, 1}，{1,0}|
     * | F(n-1), F(n-2) | = | F(n-2), F(n-3) | * | {}1, 1}，{1,0}|
     * | F(n-2), F(n-3) | = | F(n-3), F(n-4) | * | {}1, 1}，{1,0}|
     * | F(n-3), F(n-4) | = | F(n-4), F(n-5) | * | {}1, 1}，{1,0}|
     * | F(n-4), F(n-5) | = | F(n-5), F(n-6) | * | {}1, 1}，{1,0}|
     * 。
     * 。
     * 。
     * | F(2), F(1) | = | F(1), F(0) | * | {}1, 1}，{1,0}|
     * <p>
     * 那么可以得到| F(n) ,F(n-1) | = | F(1), F(0) | * | {}1, 1}，{1,0}| ^ (n-2)
     * <p>
     * <p>
     * 现在就剩下一个问题：怎么快速求出| {}1, 1}，{1,0}| ^ (n-2)次方
     * <p>
     * 采用二进制二分的方式
     * 见muliMatrix方法 与 getMatrix方法  需要注意的是单位矩阵是右边斜对角线为1的矩阵 其他位置为0
     * <p>
     * |
     */
    public int fiber2(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] ans = getMatrix(base, N - 2);
        return 1 * ans[0][0] + 1 * ans[1][0];
    }

    public int[][] getMatrix(int[][] base, int x) {
        int[][] ans = new int[base.length][base[0].length];
        // 单位矩阵 此矩阵与任何矩阵相乘都是原矩阵
        for (int i = 0; i < ans.length; i++) {
            ans[i][i] = 1;
        }
        int[][] temp = base;
        for (; x != 0; x >>= 1) {
            if ((x & 1) != 0) {
                ans = muliMatrix(ans, temp);
            }
            // temp原地相乘
            temp = muliMatrix(temp, temp);
        }
        return ans;
    }

    public int[][] muliMatrix(int[][] ans, int[][] temp) {
        // 这两个相乘生成的新的数组 行与ans数组的行相同，列与temp数组的列相同
        int[][] multi = new int[ans.length][temp[0].length];
        // 他们的计算方式是 ans数组的行的每一个数 乘以temp数组相同列的每一个数
        for (int i = 0; i < ans.length; i++) {
            // 列
            for (int j = 0; j < temp[0].length; j++) {
                // 行与列依次相乘之后的和就是新数组 i j位置的值
                for (int k = 0; k < temp.length; k++) {
                    multi[i][j] += ans[i][k] * temp[k][j];
                }
            }
        }
        return multi;
    }

    public static void main(String[] args) {
        Code01_FiberArr fiberArr = new Code01_FiberArr();
        for (int i = 0; i < 48; i++) {
            if (fiberArr.fiber(i) != fiberArr.fiber2(i)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish");
    }
}
