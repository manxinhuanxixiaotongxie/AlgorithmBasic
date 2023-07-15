package code13;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-07-15 15:10
 */
public class Code01_RobotWalk {

    public static int ways1(int N,int start ,int aim ,int k) {
        return process1(N,start,k,aim);
    }

    /**
     *对于process1函数，其递归调用的二叉树形式如下：
     *
     * process1(4, 1, 2, 3)
     *                            |
     *                  process1(4, 2, 1, 3)
     *                 /                   \
     *       process1(4, 3, 0, 3)        process1(4, 1, 1, 3)
     *            |                             |
     *            1                         process1(4, 2, 0, 3)
     *                                                 |
     *                                                 1
     * 对于process2函数，其递归调用的二叉树形式如下：
     *
     * process2(4, 1, 2, 3)
     *                            |
     *                  process2(4, 2, 1, 3)
     *                 /                   \
     *       process2(4, 1, 1, 3)        process2(4, 3, 0, 3)
     *            |                             |
     *            1                         process2(4, 2, 0, 3)
     *                                                 |
     *                                                 0
     * 可以看到，在这个例子中，process1和process2函数的递归调用的二叉树形式也是不同的。在process1函数中，先递归到了左子树，然后再递归到右子树；而在process2函数中，先递归到了右子树，然后再递归到左子树。这导致了最后一层的叶子节点返回值的差异，即process1函数返回1，而process2函数返回0。
     *
     * @param N
     * @param cur
     * @param rest
     * @param aim
     * @return
     */
    public static int process1(int N,int cur,int rest,int aim) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(N, 2, rest - 1, aim);
        }
        if (cur == N) {
            return process1(N, N - 1, rest - 1, aim);
        }
        return process1(N, cur + 1, rest - 1, aim) + process1(N, cur - 1, rest - 1, aim);
    }

    public static int process2(int N,int cur,int rest,int aim) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process2(N, cur+1, rest - 1, aim);
        }
        if (cur == N) {
            return process2(N, N - 1, rest - 1, aim);
        }
        return process2(N, cur + 1, rest - 1, aim) + process2(N, cur - 1, rest - 1, aim);
    }

}
