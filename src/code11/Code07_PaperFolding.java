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

    /**
     * 从前到后打印，意味着就是整棵树的中序遍历
     * 凹
     * 凹  凸
     * 凹 凸 凹凸
     * <p>
     * 怎么处理？
     * 对折N次  建立整棵树
     *
     *
     */

    public static void main(String[] args) {
        printAllFolds(3);
    }

    public static void printAllFolds(int N) {
        process(1, N, true);
    }

    /**
     * 脑海想象一棵树
     * i代表层级  N代表节点数
     * 递归的过程：
     * 从第一层开始向下递归
     * 一直到最后一层
     * 进行中序遍历
     * 左子树一定是凹折痕
     * 右子树一定是凸折痕
     *
     *这么进行理解：
     *
     *
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
        System.out.print(down ? "凹 " : "凸 ");
        process(i + 1, n, false);
    }

}
