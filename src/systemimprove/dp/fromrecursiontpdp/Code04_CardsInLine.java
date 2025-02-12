package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数
 * <p>
 * <p>
 * 举例：
 * 5 100 10
 * <p>
 * 有A B两个玩家
 * A先拿 A会拿10
 * B会拿到100
 * <p>
 * 最终B获胜
 * <p>
 * <p>
 * 5 100 10  3
 * <p>
 * 这个例子玩家A先拿 玩家A会先拿3
 * 因为玩家都绝顶聪明 会将最差的情况留给对手 如果玩家A先拿5 会将100留给对手 导致自己拿不到这个100
 * <p>
 * A先拿走了3   轮到B的时候 只能在5 跟10中进行选择 会选择10
 * <p>
 * <p>
 * 对于每个选手来说 都绝顶聪明 总是做出对自己有利的最好选择
 * <p>
 * <p>
 * 抽象化一下 假设设计一个函数 怎么做出选择
 * <p>
 * 对于玩家A来说 他可以拿左边或者右边的牌 但是留给对手的牌是最差的
 * <p>
 * 那么玩家A能够获取的分数就是arr[左] + processB()  或者是 arr[右] + processB() 两者中的最大值
 * <p>
 * 玩家B能够的获取的分数就是arr[左] + processA() 或者是 arr[右] + processA() 两者中的最小值
 */
public class Code04_CardsInLine {

    /**
     * 定义先手函数 后手函数
     * 最终的答案是先手以及后手的最大值
     *
     * @param arr
     * @return
     */
    public int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f1(arr, 0, arr.length - 1), g1(arr, 0, arr.length - 1));
    }

    /**
     * 从左往右的尝试模型
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public int f1(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        // 根据刚刚分析的
        // 对于先手来说 他总是能够做出最优选择
        // 必须是后手给他的选择中，做出最好的原则
        int p1 = arr[l] + g1(arr, l + 1, r);
        int p2 = arr[r] + g1(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    /**
     * 对于后手来说
     * 能够做出的选择就是
     * 在前人已经做完选择的情况下
     * 只能在现有数据状况下选择最差的
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public int g1(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // 对于后手来说
        // 他只能在前人已经做完选择的情况下
        // 在现有数据状况下选择最差的
        // 第一种情况是前人选择了左边的
        // 前人选择了左边 那么对于后手来说 后人可以在L+1到r上面进行选择
        int p1 = f1(arr, l + 1, r);
        // 第二种情况是前人选择了右边的
        int p2 = f1(arr, l, r - 1);
        return Math.min(p1, p2);
    }

    public int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 定义两张表
        int[][] f = new int[arr.length][arr.length];
        int[][] g = new int[arr.length][arr.length];
        // 对于每张表来说 每张表的左下角位置是无效的
        // 先填f1的对角线
        for (int i = 0; i < arr.length; i++) {
            f[i][i] = arr[i];
        }
        // 分析普遍位置的依赖
        // 对于f表格 依赖的位置是g表格的左边跟下边
        // 因此填写表格最下面一行往上面填
        // 从左到右进行填写
        for (int row = arr.length - 2; row >= 0; row--) {
            int col = row + 1;
            while (col < arr.length) {
                f[row][col] = Math.max(arr[row] + g[row + 1][col], arr[col] + g[row][col - 1]);
                g[row][col] = Math.min(f[row + 1][col], f[row][col - 1]);
                col++;
            }
        }
        return Math.max(f[0][arr.length - 1], g[0][arr.length - 1]);

    }

    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        Code04_CardsInLine cardsInLine = new Code04_CardsInLine();
        System.out.println(cardsInLine.win1(arr));
        System.out.println(cardsInLine.win2(arr));
    }
}
