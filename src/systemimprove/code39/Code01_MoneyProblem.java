package systemimprove.code39;

/**
 * int[] d，d[i]：i号怪兽的能力
 * int[] p，p[i]：i号怪兽要求的钱
 * 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的怪兽。
 * 如果你当前的能力，小于i号怪兽的能力，你必须付出p[i]的钱，贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上；如果你当前的能力，大于等于i号怪兽的能力，你可以选择直接通过，你的能力并不会下降，
 * 你也可以选择贿赂这个怪兽，然后怪兽就会加入你，他的能力直接累加到你的能力上。
 * 返回通过所有的怪兽，需要花的最小钱数。
 */
public class Code01_MoneyProblem {


    /**
     * 常见的从左到右的递归方式
     * 这种递归适合
     * 怪兽花费的钱数相对较大
     * 但是怪兽当前的能力值相对不大
     *
     * @param d
     * @param p
     * @return
     */
    public int moneyProblem1(int[] d, int[] p) {
        return process(d, p, 0, 0);
    }

    /**
     * 这个递归函数的含义的是从0-index的范围上获得ability能力 通过怪兽花费的最小钱数
     *
     * @param d
     * @param p
     * @param index
     * @param ability
     * @return
     */
    public int process(int[] d, int[] p, int index, int ability) {
        if (index == d.length) {
            return 0;
        }
        // 当前能力比怪兽的能力值小的时候
        // 只能通过贿赂的方式获得能力才能通过怪兽
        if (ability < d[index]) {
            return p[index] + process(d, p, index + 1, ability + d[index]);
        } else {
            // 当前的能力比怪兽的能力值大的时候
            // 有两种选择
            // 贿赂当前怪兽 不贿赂当前怪兽 两者求最小值
            return Math.min(p[index] + process(d, p, index + 1, ability + d[index]), process(d, p, index + 1, ability));
        }
    }

    /**
     * 将上述方法改成动态规划
     * 转义方程：
     * dp[index][ability] =
     * 1.能力比怪兽能力小 依赖dp[index+1][ability+d[index]] + p[index]
     * 2.能力比怪兽的能力大 依赖的位置是Math.min(dp[index+1][ability],dp[index+1][ability+d[index]]+p[index])
     *
     *
     * 当题目给定的怪兽的能力比较小的时候 这个猜法是可以的
     * 如果怪兽的能力非常大 那么aility能够达到值就非常大
     * 导致在10^8的指令集的过程中 无法完成题目给定数据量的计算
     *
     */

    /**
     * 数据状况
     * ability的值非常大 钱数相对不大
     * 返回能够通过所有怪兽花费的最小钱数
     *
     * @param d
     * @param p
     * @return
     */
    public int moneyProblem2(int[] d, int[] p) {
        // 严格花费money这么多钱 获得的最大能力值
        int allMoney = 0;
        for (int i = 0; i < p.length; i++) {
            allMoney += p[i];
        }
        for (int money = 0; money < allMoney; money++) {
            if (process2(d, p, d.length - 1, money) != -1) {
                return money;
            }
        }
        return allMoney;
    }

    /**
     * 这个递归函数的含义是
     * <p>
     * 严格花费money这么多钱 获得的最大能力值
     * <p>
     * 至于为什么要范围能力值
     * 假设不返回能力的话 到当前位置要花完money的钱
     * 1.有两种情况 第一种情况 不贿赂当前位置的怪兽 那么之前已经形成的能力必须要大于当前能力 并且在之前已经花完了money这么多钱
     * 2.第二种情况  当前位置进行贿赂 之前花的钱是money-当前位置的钱 那么就不管之前的能力值 这时候不关心之前形成的能力 能力大小都可以选择贿赂
     *
     * @param d
     * @param p
     * @param index
     * @param money
     * @return
     */
    public long process2(int[] d, int[] p, int index, int money) {
        if (index == -1) {
            return money == 0 ? 0 : -1;
        } else {
            // 不贿赂当前怪兽
            // 不贿赂当前怪兽 之前形成的能力值要大于当前能力值
            long preAbility = process2(d, p, index - 1, money);
            long p1 = -1;
            // 之前要花光money的钱 并且能力要大于当前能力
            if (preAbility != -1 && preAbility >= d[index]) {
                p1 = preAbility;
            }
            // 贿赂当前当前
            // 之前花的钱是money-p[index]
            long preAbility2 = process2(d, p, index - 1, money - p[index]);
            long p2 = -1;
            if (preAbility2 != -1) {
                p2 = d[index] + preAbility2;
            }
            return Math.max(p1, p2);
        }
    }
}
