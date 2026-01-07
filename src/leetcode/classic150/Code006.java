package leetcode.classic150;

/**
 * 字符串变换
 *
 */
public class Code006 {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        // 开始计算
        // 分组
        // 每组有有多少个字符
        int groupCount = 2 * numRows - 2;
        // 总共有多少组
        int groups = s.length() / groupCount + 1;
        // 辅助结构
        // 总共多少行
        int row = numRows;
        // 总共多少列
        int col = groups * (numRows - 1);
        char[][] matrix = new char[row][col];
        // 填充字符
        int index = 0;
        for (int g = 0; g < groups; g++) {
            // 每组有groupCount个字符
            for (int i = 0; i < groupCount; i++) {
                if (index >= s.length()) {
                    break;
                }
                if (i < numRows) {
                    // 竖着放
                    matrix[i][g * (numRows - 1)] = s.charAt(index++);
                } else {
                    // 斜着放
                    matrix[groupCount - i][g * (numRows - 1) + (i - numRows + 1)] = s.charAt(index++);
                }
            }
        }
        // 遍历
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != '\u0000') {
                    sb.append(matrix[i][j]);
                }
            }
        }
        return sb.toString();
    }

    static void main() {
        Code006 code006 = new Code006();
        String s = "PAYPALISHIRING";
        int numRows = 3;
        String res = code006.convert(s, numRows);
        System.out.println(res);
    }
}
