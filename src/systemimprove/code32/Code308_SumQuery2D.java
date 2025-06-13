package systemimprove.code32;

/**
 * indextree 2D应用
 *
 */
public class Code308_SumQuery2D {

    private int[][] tree;
    private int[][] nums;
    private int N;
    private int M;

    Code308_SumQuery2D(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        N = matrix.length;
        M = matrix[0].length;
        nums = new int[N][M];
        tree = new int[N + 1][M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nums[i][j] = matrix[i][j];
                add(i + 1, j + 1, matrix[i][j]);
            }
        }
    }

    private void add(int row, int col, int val) {
        int add = val - nums[row - 1][col - 1];
        nums[row - 1][col - 1] = val;
        for (int i = row; i <= N; i += i & (-i)) {
            for (int j = col; j <= M; j += j & (-j)) {
                tree[i][j] += add;
            }
        }
    }

    private int sum(int row, int col) {
        int sum = 0;
        for (int i = row + 1; i > 0; i -= i & (-i)) {
            for (int j = col + 1; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (N == 0 || M == 0) {
            return 0;
        }
        return sum(row2, col2) + sum(row1 - 1, col1 - 1) - sum(row1 - 1, col2) - sum(row2, col1 - 1);
    }
}
