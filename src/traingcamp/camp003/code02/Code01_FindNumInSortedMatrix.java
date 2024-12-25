package traingcamp.camp003.code02;

public class Code01_FindNumInSortedMatrix {
    /**
     * 在行也有序、列也有序的二维数组中，找num，找到返回true，否则false
     *
     * @param matrix
     * @param num
     * @return
     */
    public boolean isContains(int[][] matrix, int num) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        boolean ans = false;
        /**
         * 从右上角开始当前的数比num小  向下走
         * 当前值比num大  向左走
         */
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == num) {
                ans = true;
                break;
            } else if (matrix[row][col] < num) {
                row++;
            } else {
                col--;
            }
        }
        return ans;
    }
}
