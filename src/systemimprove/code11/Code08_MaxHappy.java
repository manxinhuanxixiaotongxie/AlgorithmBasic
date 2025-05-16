package systemimprove.code11;

import java.util.List;

public class Code08_MaxHappy {

    /**
     * 派对的最大快乐值
     * 公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。树的头节点是公司唯一的老板。
     * 除老板之外的每个员工都有唯一的直接上级。 叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级
     * <p>
     * <p>
     * 派对的最大快乐值
     * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
     * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
     * 2.派对的整体快乐值是所有到场员工快乐值的累加
     * 3.你的目标是让派对的整体快乐值尽量大
     * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
     */
    class Employee {
        public int happy; // 这名员工可以带来的快乐值
        List<Employee> subordinates; // 这名员工有哪些直接下级
    }

    public int maxHappy(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info all = process(boss);
        return Math.max(all.yes, all.no);
    }

    /**
     * 递归函数
     * 范围上的尝试模型
     * 从左到右的尝试模型
     * 样本对应模型
     * 分支限界尝试模型
     * <p>
     * <p>
     * <p>
     * 分析可能性：
     * 任意X开头
     * X来  下级员工都不来
     * X。happy + 下级员工都不来的最大快乐值
     * <p>
     * <p>
     * X不来  下级员工来或不来
     *
     * @param
     * @return
     */

    class Info {
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    /**
     * X
     * a        b       c
     * d e f    g h i    j k
     *
     * @param X
     * @return
     */
    public Info process(Employee X) {
        if (X == null) {
            return new Info(0, 0);
        }
        int yes = X.happy;
        int no = 0;
        if (X.subordinates != null) {
            for (Employee next : X.subordinates) {
                Info nextInfo = process(next);
                // X去 下级员工都不来
                yes += nextInfo.no;
                // X不去 下级员工来或不来
                no += Math.max(nextInfo.yes, nextInfo.no);
            }
        }
        return new Info(yes, no);
    }

    /**
     * 局部最功利的标准可以得到全局最优解
     */

}
