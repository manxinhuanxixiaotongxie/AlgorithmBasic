package leetcode.classic150;

import java.util.List;

public class Code054 {
    public List<Integer> spiralOrder(int[][] matrix) {
        // 分圈 一圈一圈的遍历
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0, right = col - 1, top = 0, bottom = row - 1;
        List<Integer> res = new java.util.ArrayList<>();

        while (left <= right && top <= bottom) {
            if (left == right) {
                // 只有一列 打印从top到button
                while (top <= bottom) {
                    res.add(matrix[top][left]);
                    top++;
                }
                break;
            }
            if (top == bottom) {
                // 只有一行
                while (left <= right) {
                    res.add(matrix[top][left]);
                    left++;
                }
                break;
            }
            // 开始分圈
            // 顶边
            for (int i = left; i < right; i++) {
                res.add(matrix[top][i]);
            }
            // 右边
            for (int i = top; i < bottom; i++) {
                res.add(matrix[i][right]);
            }
            // 底边
            for (int i = right; i > left; i--) {
                res.add(matrix[bottom][i]);
            }
            // 左边
            for (int i = bottom; i > top; i--) {
                res.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            bottom--;
        }

        return res;
    }

    static void main() {
        Code054 code = new Code054();
        // [[1,2,3,4],[5,6,7,8],[9,10,11,12],[13,14,15,16],[17,18,19,20],[21,22,23,24]]
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16},
                {17, 18, 19, 20},
                {21, 22, 23, 24}
        };
        List<Integer> res = code.spiralOrder(matrix);
        System.out.println(res);
    }
}
