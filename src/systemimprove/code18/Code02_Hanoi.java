package systemimprove.code18;

/**
 * 汉诺谈问题  焚天塔
 * <p>
 * 给定三个柱子 要将N层的圆盘从左移动到右 只能遵守小压大的原则
 * 总共需要多少步数？
 */
public class Code02_Hanoi {

    /**
     * 打印汉诺塔的移动过程
     * 如果N=3为例
     * 移动轨迹如下：
     * 1.第一层去最右侧
     * 2.第二层去中间
     * 3.第一层去中间
     * 4.第三层去最右侧
     * 5.第一层去最左侧
     * 6.第二层去最右侧
     * 7.第一层去最右侧
     * <p>
     * 那么N层汉诺塔是怎么移动的呢？
     * 1.1-n-1去中间
     * 2.N去最右侧
     * 3。N-1去最右侧
     */

    public static void main(String[] args) {
//        hanoi(10);
        hanoi2(10);
    }

    public static void hanoi(int n) {
        if (n > 0) {
            leftToRight(n);
        }
    }

    public static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to right");
            return;
        }
        // 将N从左到右需要将n-1从N-1 从左移动到中间
        leftToMid(n - 1);
        // 将n-1移动到中间之后 只剩下最后一层 这时候就可以直接将的N从左移动到右
        System.out.printf("Move %d from left to right\n", n);
        // 再将刚刚从左移动到中间的 n-1层圆盘 从中间移动到右
        midToRight(n - 1);
    }

    public static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from left to mid");
            return;
        }
        // 将圆盘从左移动到中间需要将n-1从左移动到右
        leftToRight(n - 1);
        // 将n-1从左移动到右之后 只剩下最后一层 这时候就可以直接将的N从左移动到中间
        System.out.printf("Move %d from left to mid\n", n);
        // 最后需要将已经移动到N-1的圆盘从右移动到中间
        rightToMid(n - 1);
    }

    public static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to mid");
            return;
        }
        // 将圆盘从右移动到中间需要将n-1从右移动到左
        rightToLeft(n - 1);
        // 已经将n-1从右移动到左之后 只剩下最后一层 这时候就可以直接将的N从右移动到中间
        System.out.printf("Move %d from right to mid\n", n);
        // 刚刚从右到左的圆盘 需要从左移动到中间
        leftToMid(n - 1);
    }

    public static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from right to left");
            return;
        }
        // 要将n从又移动到左 需要将n-1从右移动到中间
        rightToMid(n - 1);
        // 已经将的n-1从右移动到中间之后 只剩下最后一层 这时候就可以直接将的N从右移动到左
        System.out.printf("Move %d from right to left\n", n);
        // 将刚刚从右移动到中间的n-1层圆盘 从中间移动到左
        midToLeft(n - 1);
    }

    public static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to left");
            return;
        }
        // 如果要将n从中间移动到左 需要将n-1从中间移动到右
        midToRight(n - 1);
        // 已经将n-1从中间移动到右之后 只剩下最后一层 这时候就可以直接将的N从中间移动到左
        System.out.printf("Move %d from mid to left\n", n);
        // 将刚刚从中间移动到右的n-1层圆盘 从右移动到左
        rightToLeft(n - 1);
    }

    public static void midToRight(int n) {
        if (n == 1) {
            System.out.println("Move 1 from mid to right");
            return;
        }
        // 如果要将n从中间移动到右 需要将n-1从中间移动到左
        midToLeft(n - 1);
        // 已经将n-1从中间移动到左之后 只剩下最后一层 这时候就可以直接将的N从中间移动到右
        System.out.printf("Move %d from mid to right\n", n);
        // 将刚刚从中间移动到左的n-1层圆盘 从左移动到右
        leftToRight(n - 1);
    }

    /**
     * 尝试有好坏之分
     * N层汉诺塔从左移动到右
     * 有一个中间位置  使用这个中间位置标志汉诺塔的移动过程
     */
    public static void hanoi2(int N) {
        process("from", "to", "mid", N);
    }

    public static void process(String from, String to, String other, int N) {
        if (N == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
            return;
        }
        // 要将N 从from移动到to 需要将N-1从from移动到other
        process(from, other, to, N - 1);
        // N-1已经从from移动到to了，只剩下最后一层，这时候就可以直接将N从from移动到to
        System.out.println("Move " + N + " from " + from + " to " + to);
        // N-1从from移动到other之后，只剩下最后一层，这时候就可以直接将N从other移动到to
        process(other, to, from, N - 1);

    }
}
