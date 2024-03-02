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
        for (int i = 0; i < ans.length; i++) {
            ans[i][i] = 1;
        }
        int[][] temp = base;
        for (; x != 0; x >>= 1) {
            if ((x & 1) != 0) {
                ans = muliMatrix(ans, temp);
            }
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
        for (int i = 0; i < 40; i++) {
            if (fiberArr.fiber(i) != fiberArr.fiber2(i)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish");
    }
}
