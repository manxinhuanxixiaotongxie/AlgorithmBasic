package leetcode.classic150;

public class Code048 {
    /**
     * 原地修改
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        // 使用分圈结构
        int row = matrix.length;
        int col = matrix[0].length;
        // 定义四个顶点
        int left = 0;
        int right = col - 1;
        int top = 0;
        int bottom = row - 1;
        // 开始分圈
        // 总共有多少圈
        while (left < right && top < bottom) {
            // 这是总共的圈数
            // 开始处理每一圈
            printCicle(matrix,left++,right--,top++,bottom--);

        }
    }
    public void printCicle(int[][] matrix,int left,int right,int top,int bottom) {
        // 旋转当前圈
        // 对当前圈进行分组 总共有多少组
        int group = right - left;
        for (int i = 0; i < group; i++) {
            int temp = matrix[top][left + i];
            matrix[top][left + i] = matrix[bottom - i][left];
            matrix[bottom - i][left] = matrix[bottom][right - i];
            matrix[bottom][right - i] = matrix[top + i][right];
            matrix[top + i][right] = temp;
        }
    }

    /**
     * 在数组中进行交换
     * @param matrix
     * @param l1
     * @param c1
     * @param l2
     * @param c2
     */
    private void swap(int[][] matrix,int l1,int c1,int l2,int c2) {
        int temp = matrix[l1][c1];
        matrix[l1][c1] = matrix[l2][c2];
        matrix[l2][c2] = temp;
    }

    static void main() {
        Code048 code048 = new Code048();
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        code048.rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
