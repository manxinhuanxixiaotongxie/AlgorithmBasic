package systemimprove.code38;

/**
 * 给定一个正整数N，表示有N份青草统一堆放在仓库里
 * 有一只牛和一只羊，牛先吃，羊后吃，它俩轮流吃草
 * 不管是牛还是羊，每一轮能吃的草量必须是：
 * 1，4，16，64…(4的某次方)
 * 谁最先把草吃完，谁获胜
 * 假设牛和羊都绝顶聪明，都想赢，都会做出理性的决定
 * 根据唯一的参数N，返回谁会赢
 */
public class Code02_EatGrass {

    /**
     * 给定n份草 返回最终谁会赢
     * 如果是先手赢的话 返回先手
     * 如果是后手赢的话 返回后手
     *
     * @param n
     * @return
     */
    public static String whoWin(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后手" : "先手";
        }
        int want = 1;
        while (want <= n) {
            // 当前先选 whowin(n - want) 后手 当前怎么才能赢
            // 必须要whoWin(n - want) 是后手
            if (whoWin(n - want).equals("后手")) {
                return "先手";
            }
            // 防止越界
            if (want > n / 4) {
                break;
            }
            want *= 4;
        }
        return "后手";
    }

    /**
     * 规律是：打印出来的顺序是后先后先先
     *
     * @param n
     */

    public static String whiWin2(int n) {
        if (n % 5 == 0 || n % 5 == 2) {
            return "后手";
        } else {
            return "先手";
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + " : " + whoWin(i));
        }
    }
}
