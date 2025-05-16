package leetcode.practice;

public class Code059 {

    public int[][] generateMatrix(int n) {
        // 使用分圈结构
        // 总共有多少圈
        // 定义左上角 定义右下角
        int leftIndex = 0;
        int rightIndex = n - 1;
        int[][] ans = new int[n][n];
        int index = 1;
        while (leftIndex <= rightIndex) {
            index = cycle(ans, leftIndex, leftIndex, rightIndex, rightIndex, index);
            leftIndex++;
            rightIndex--;
        }
        return ans;
    }

    // 打印每一圈信息
    private int cycle(int[][] matrix, int leftR, int leftC, int rightR, int rightC, int index) {
        // 在每一圈里面设置数组的值
        // 第一行
        int r = leftR;
        int c = leftC;
        if (leftR == rightR) {
            matrix[r][c] = index;
            return index;
        }

        while (c < rightC) {
            matrix[r][c] = index++;
            c++;
        }
        // 右边最后一列的
        while (r < rightR) {
            matrix[r][c] = index++;
            r++;
        }
        // 最下面的一行
        while (c > leftC) {
            matrix[r][c] = index++;
            c--;
        }
        // 最左边的一列
        while (r > leftR) {
            matrix[r][c] = index++;
            r--;
        }
        return index;
    }

    public static void main(String[] args) {
        Code059 code059 = new Code059();
        int[][] res = code059.generateMatrix(4);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
