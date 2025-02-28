package systemimprove.code40;

/**
 * 给定一个长方形矩阵matrix，实现转圈打印
 * a  b  c  d
 * e  f   g  h
 * i   j   k   L
 * 打印顺序：a b c d h L k j I e f g
 */
public class Code05_PrintRectangle {

    public void printRec(char[][] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        /**
         * 使用分圈结构
         */
        int row = arr.length;
        int col = arr[0].length;
        int a = 0;
        int b = 0;
        int c = row - 1;
        int d = col - 1;

        while (a <= c) {
            priint(arr, a++, b++, c--, d--);
        }
    }

    private void priint(char[][] arr, int a, int b, int c, int d) {
        // a行b列打印到c行d列
        if (a == c) {
            // 注意变成一条直线
            for (int i = b; i <= d; i++) {
                System.out.println(arr[a][i]);
            }
        } else if (b == d) {
            for (int i = a; i <= c; i++) {
                System.out.println(arr[i][b]);
            }
        } else {
            int curC = b;
            int curR = a;
            while (curC != d) {
                System.out.println(arr[a][curC++]);
            }
            while (curR != c) {
                System.out.println(arr[curR++][d]);
            }
            while (curC != b) {
                System.out.println(arr[c][curC--]);
            }
            while (curR != a) {
                System.out.println(arr[curR--][b]);
            }
        }
    }

    public static void main(String[] args) {
        char[][] arr = new char[][]{
//                {'a', 'b', 'c', 'd'},
//                {'e', 'f', 'g', 'h'},
//                {'i', 'j', 'k', 'l'}
                {'a','b','c'},
                {'d','e','f'},
                {'g','h','i'},
                {'j','k','l'},
                {'m','n','o'}
        };
        Code05_PrintRectangle code05_printRectangle = new Code05_PrintRectangle();
        code05_printRectangle.printRec(arr);

    }
}
