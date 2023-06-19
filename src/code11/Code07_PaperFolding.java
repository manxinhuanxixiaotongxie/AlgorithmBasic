package code11;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-08-01 14:25
 */

/**
 * 折纸问题
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕都方向
 */
public class Code07_PaperFolding {


    public static void main(String[] args) {
        printAllFolds(3);
    }

    public static void printAllFolds(int N) {
        process(1, N, true);
    }


    /**
     * 想象成一棵树其实就是一棵树的中序优先遍历
     * 中序优先遍历的过程
     * 1.左树到底
     * 2.弹出返回并且打印
     * 3.右树到底
     *
     * @param i
     * @param n
     * @param down
     */
    public static void process(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        process(i + 1, n, true);
        System.out.print(down ? "凹" : "凸");
        process(i + 1, n, false);
    }

}
