package book_01stackandlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-10-20 19:32
 */

/**
 * 使用栈实现汉诺塔
 * 要求：
 * 不能从最左到最右也不能从最右到最左
 * 必须要经过中间
 * 当塔有N层 打印最优移动过程和最优移动步数
 * <p>
 * 用以下两个方法实现：
 * 1.使用递归
 * 2.非递归的方法，用栈来模拟汉诺塔的三个塔
 */
public class UseStackToImplHanoi {

    public static void main(String[] args) {
//        moveLeftToRight(2);
        hanoiProblem1(6, "left", "mid", "right");
    }


    /**
     * 两层汉诺塔最上层是1 下面是2
     * <p>
     * 那么移动的步骤就是
     * 1 left mid
     * 1 mid right
     * 2 left mid
     * <p>
     * 1 right mid
     * 1 mid left
     * 2 mid right
     * 1 left mid
     * 1 mid right
     */

    /**
     * f(n) = f(n-1) + n
     * f(n-1) = f(n-2) +n-1
     * .
     * .
     * .
     * f(2) = f(1) + 2
     */

    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 0) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    // 开始的时候 from left to right
    private static int process(int num, String left, String mid, String right, String from, String to) {
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                //移动一次
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                //移动两次
                return 2;
            }
        }

        //起点或者终点为mid情况
        if (from.equals(mid) || to.equals(mid)) {
            //将N层 左->中，中->左, 中->右，右->中，
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            /**
             * 左->中:  1-N-1，left->right; N, left -> mid; 1-N-1 right->mid
             *
             * 整个过程：
             *
             * process(N,left mid right left right)
             * 需要1-N-1移动到最右侧
             * process(N-1,left mid right left right)
             * N移动到中间
             * move N from left to mid
             * 1-N-1移动到左侧
             * process(N-1,left mid right right left)
             *
             * N移动到右侧
             * move N from mid to right
             * 1-N-1移动到右侧
             * process(N-1,left mid right left right)
             */
            //为什么需要中间层？  一开始就在中间层  全部移动到右侧或者全部移动到左侧 只需要三步
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1; // N left->mid
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            // 1-N-1 从左到右(必要要经过中间)
            int part1 = process(num - 1, left, mid, right, from, to);

            int part2 = 1;
            // N从left 到right
            System.out.println("Move " + num + " from " + from + " to " + mid);
            // 1-N-1 从右到左
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            // N从mid 到right
            System.out.println("Move " + num + " from " + mid + " to " + to);
            // 1-N-1 从左到右
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }

    }

    public static void moveLeftToRight(int n) {

        if (1 == n) {
            return;
        }

        moveLeftToMid(n - 1);
        moveMidToRight(n - 1);
        moveLeftToMid(n);
        moveRightToMid(n - 1);
        moveMidToLeft(n - 1);
        moveMidToRight(n);
        moveLeftToMid(n - 1);
        moveMidToRight(n - 1);


    }

    public static void moveLeftToMid(int n) {
        if (n == 1) {
            System.out.println("move " + n + " from " + " left " + " to " + "mid");
            return;
        }
        moveMidToRight(n);

    }

    public static void moveMidToRight(int n) {
        if (1 == n) {
            System.out.println("move " + 1 + " from " + " mid " + " to " + "right");
            return;
        }
        moveMidToRight(n);

    }

    public static void moveRightToMid(int n) {
        if (n == 1) {
            System.out.println("move " + 1 + " from " + " right " + " to " + "mid");
            return;
        }
        moveMidToLeft(n);
    }

    public static void moveMidToLeft(int n) {
        if (n == 1) {
            System.out.println("move " + 1 + " from " + " mid " + " to " + "left");
            return;
        }
        moveMidToRight(n);
    }


    public static int process(int num, String from, String to) {
        if (num == 1) {
            System.out.println("move 1 from " + from + " to middle");
            System.out.println("move 1 from middle to " + to);
            return 2;
        } else {
            int p1 = process(num - 1, from, to);
            System.out.println("move " + num + " from " + from + " to middle");
            int p2 = process(num - 1, to, from);
            System.out.println("move " + num + " from middle to " + to);
            int p3 = process(num - 1, from, to);
            return p1 + p2 + p3 + 2;
        }
    }
}
