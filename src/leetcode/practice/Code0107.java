package leetcode.practice;

public class Code0107 {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length || matrix.length == 1) {
            return;
        }
        int leftR = 0;
        int leftC = 0;
        int rightR = matrix.length - 1;
        int rightC = matrix.length - 1;
        // 总共有多少圈
        // 采用剥洋葱的方式对这个正方形矩阵进行处理
        // 总共有多少圈
        while (leftR < rightR) {
            swap(matrix, leftR++, leftC++, rightR--, rightC--);
        }
    }

    // 几个参数的含义
    // leftR左上角的横坐标
    // leftC是做上角的纵坐标
    // rightR是右下角的横坐标
    // rightC是右下角的纵坐标
    private void swap(int[][] matrix, int leftR, int leftC, int rightR, int rightC) {
        for (int i = 0; i < (rightR - leftR); i++) {
            // 顺时针旋转
            // 第i个数
            int temp = matrix[leftR][leftC + i];
            matrix[leftR][leftC + i] = matrix[rightR - i][leftC];
            matrix[rightR - i][leftC] = matrix[rightR][rightC - i];
            matrix[rightR][rightC - i] = matrix[leftR + i][rightC];
            matrix[leftR + i][rightC] = temp;
        }
    }

    public static void main(String[] args) {
        Code0107 code0107 = new Code0107();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        code0107.rotate(matrix);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
