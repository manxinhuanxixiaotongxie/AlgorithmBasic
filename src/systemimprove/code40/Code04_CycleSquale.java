package systemimprove.code40;

/**
 * 给定一个正方形矩阵matrix，原地调整成顺时针90度转动的样子
 * a  b  c		g  d  a
 * d  e  f			h  e   b
 * g  h  i			i    f   c
 */
public class Code04_CycleSquale {

    // 要求原地进行调整

    /**
     * 使用分圈结构进行调整
     * <p>
     * 进行分组
     * <p>
     * a的位置在 a行b列
     * i的位置在 c行d列
     * <p>
     * <p>
     * 总共有多少组
     * d-b+1组
     * <p>
     * 第一组
     * a b c
     * c f i
     * i h g
     * g d a
     * <p>
     * 第一组的第一个位置arr[a][b]
     * 第二个位置arr[a][b+1]
     * 第三个位置arr[a+1][b+2]
     * <p>
     * 第二组的第一个位置arr[a][d]
     * 第二个位置arr[a+1][d]
     * 第三个位置arr[a+2][d]
     */

    public void cycleArr(int[][] arr) {
        if (arr == null || arr.length == 0 || arr.length != arr[0].length) {
            return;
        }
        // 行
        int row = arr.length;
        // 列
        int col = arr[0].length;
        // 左上角的横坐标
        int a = 0;
        // 左上角的纵坐标
        int b = 0;
        // 右上角的横坐标
        int c = row - 1;
        // 右上角的纵坐标
        int d = col - 1;
        // 总共有多少圈
        while (a <= c && b <= d) {
//            cycle(arr, a++, b++, c--, d--);
            cycle2(arr, a++, c--);
        }
    }

    private void cycle(int[][] arr, int a, int b, int c, int d) {
        // 第一个数arr[a][b]
        // 第二个数arr[a][b+1]
        // 第三个数arr[a+1][b+2]
        int times = 0;
        // 这里面是一圈 每一圈进行分组  总有有times组
        while (times < (d - b)) {
            // 找到四个数
            // 第time组的第一个数 arr[a][b+time]
            // 第time组的第二个数 arr[a+time][d]
            // 第time组的第三个数 arr[c][d-time]
            // 第time组的第四个数 arr[c-time][b]
            int temp = arr[a][b + times];
            arr[a][b + times] = arr[c - times][b];
            arr[c - times][b] = arr[c][d - times];
            arr[c][d - times] = arr[a + times][d];
            arr[a + times][d] = temp;
            times++;
        }
    }

    private void cycle2(int[][] arr, int a, int c) {
        // 第一个数arr[a][b]
        // 第二个数arr[a][b+1]
        // 第三个数arr[a+1][b+2]
        int times = 0;
        // 这里面是一圈 每一圈进行分组  总有有times组
        while (times < (c - a)) {
            // 总共有多少组
            int temp = arr[a][a + times];
            arr[a][a + times] = arr[c - times][a];
            arr[c - times][a] = arr[c][c - times];
            arr[c][c - times] = arr[a + times][c];
            arr[a + times][c] = temp;
            times++;
        }

    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        Code04_CycleSquale code04_cycleSquale = new Code04_CycleSquale();
        code04_cycleSquale.cycleArr(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
