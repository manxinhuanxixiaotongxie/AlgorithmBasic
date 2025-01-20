package codeforgreat.code02;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 给定数组hard和money，长度都为N
 * hard[i]表示i号的难度， money[i]表示i号工作的收入
 * 给定数组ability，长度都为M，ability[j]表示j号人的能力
 * 每一号工作，都可以提供无数的岗位，难度和收入都一样
 * 但是人的能力必须>=这份工作的难度，才能上班
 * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
 */
public class Code01_ChooseWork {

    /**
     * 第一种思路：
     * 1.将job数组进行排序 排序的规则是根据能力值进行排序的  能力值有小到大  能力值一样  根据收入大小进行排序
     * 2.这样排序排序之后 ability[i]能够获取的最大收入 进行二分
     * <p>
     * <=能力值最右侧的位置
     * 能力给定能力小  二分来到右边位置
     * 能力给定能力大  二分来到左边位置
     * <p>
     * <p>
     * <p>
     * <p>
     * ================================
     * <p>
     * <p>
     * 直接这样计算惠会有问题
     * 问题出在设呢么地方呢？
     * <p>
     * 根据题意：实际上涞水是要根据能力值要找到获取的最大收入
     * <p>
     * 我们根据这种排序方式 尽管是有序的 但是 并不是严格按照要求越高 能力越高进行的
     * <p>
     * 因此原始数组在排序之后要处理成要求越高  收入也越高的数据
     *
     * @param job
     * @param ability
     * @return
     */
    public static int[] getMoneys1(Job[] job, int[] ability) {
        Arrays.sort(job, (a, b) -> a.hard != b.hard ? a.hard - b.hard : a.money - b.money);
        int[] ans = new int[ability.length];
        // 每一步都二分
        for (int i = 0; i < ability.length; i++) {
            int l = 0;
            int r = 0;
            while (l <= r) {
                int mid = l + ((r - l) >> 1);
                if (job[mid].hard <= ability[i]) {
                    ans[i] = job[mid].money;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return ans;
    }

    public static int[] getMoneys2(Job[] job, int[] ability) {
        // 将数组处理成按照优先级从低到高
        // 收入从高到低的排序方式
        // 这样一来 所有的优先级相同的都是收入最高的排在前面
        // 前面已经提到过
        // 我们要处理数据 将整个数组处理成能力值从低到高 并且收入也是从低到高
        Arrays.sort(job, (a, b) -> a.hard != b.hard ? a.hard - b.hard : b.money - a.money);
        // 每一步都二分
        // 是不是真的要处理原始数组呢
        // 有简便的处理方式
        // key是难度   value是的能够获得的收益
        // key : 难度   value：报酬
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(job[0].hard, job[0].money);
        // pre : 上一份进入map的工作
        Job pre = job[0];
        for (int i = 1; i < job.length; i++) {
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                pre = job[i];
                map.put(pre.hard, pre.money);
            }

            /**
             * 如果直接这样写
             * 有什么问题？
             * 我们要在map里面维护的是能力增大 收入也跟着增大的单调性
             */
//            if (!map.containsKey(job[i].hard)) {
//                map.put(job[i].hard, job[i].money);
//            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ability.length; i++) {
            // ability[i] 当前人的能力 <= ability[i]  且离它最近的
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }

}

class Job {
    int hard;
    int money;

    Job(int h, int m) {
        hard = h;
        money = m;
    }
}
