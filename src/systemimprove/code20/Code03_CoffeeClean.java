package systemimprove.code20;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
 * 给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
 * 只有一台洗杯机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
 * 每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
 * 假设所有人拿到咖啡之后立刻喝干净，
 * 返回从开始等到所有咖啡机变干净的最短时间
 * 三个参数：int[] arr、int N，int a、int b
 */
public class Code03_CoffeeClean {

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = bestTime(arr, n, a, b);
            int ans3 = bestTime2(arr, n, a, b);
            if (ans1 != ans2 || ans2 != ans3) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");
    }

    // 验证的方法
    // 彻底的暴力
    // 很慢但是绝对正确
    public static int right(int[] arr, int n, int a, int b) {
        int[] times = new int[arr.length];
        int[] drink = new int[n];
        return forceMake(arr, times, 0, drink, n, a, b);
    }

    public static int forceWash(int[] drinks, int a, int b, int index, int washLine, int time) {
        if (index == drinks.length) {
            return time;
        }
        // 选择一：当前index号咖啡杯，选择用洗咖啡机刷干净
        int wash = Math.max(drinks[index], washLine) + a;
        int ans1 = forceWash(drinks, a, b, index + 1, wash, Math.max(wash, time));

        // 选择二：当前index号咖啡杯，选择自然挥发
        int dry = drinks[index] + b;
        int ans2 = forceWash(drinks, a, b, index + 1, washLine, Math.max(dry, time));
        return Math.min(ans1, ans2);
    }

    // 每个人暴力尝试用每一个咖啡机给自己做咖啡
    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            int[] drinkSorted = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkSorted);
            return forceWash(drinkSorted, a, b, 0, 0, 0);
        }
        int time = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            time = Math.min(time, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return time;
    }

    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    /**
     * arr[i]代表i号咖啡机泡一杯咖啡的时间
     * N表示有N个人等着咖啡机泡咖啡 每台机器是串行泡咖啡
     * 只有一台洗杯子的机器 一次只能洗一个杯子 时间消耗a 串的洗
     * 同时咖啡杯也可以自由的挥发干净 时间消耗b 并行的挥发
     * 返回从开始等到所有咖啡机变干净的最短时间
     *
     * @param arr
     * @param n
     * @param a
     * @param b
     * @return
     */
    public static int bestTime(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<CoffeRecord> queue = new PriorityQueue<>(Comparator.comparingInt(o ->
                (o.getWorkTime() + o.getTimePoint())));


        for (int i = 0; i < arr.length; i++) {
            CoffeRecord record = new CoffeRecord();
            record.setWorkTime(arr[i]);
            record.setTimePoint(0);
            queue.add(record);
        }
        // 首先我们要解决一个问题是怎么得到所有人最佳喝到咖啡的排队时间
        // 这里的目的是站在上帝的视角上 给用户进行选择
        // 用户怎么进行站队  能够得到最快的时间能够喝上咖啡
        // 小跟堆弹出的时候就是i号用户能够拿到咖啡的最佳时间
        // 那么下一个用户能够到咖啡机的时间就是弹出的时间加上咖啡机泡咖啡的时间
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            CoffeRecord cur = queue.poll();
            cur.setTimePoint(cur.getTimePoint() + cur.getWorkTime());
            drinks[i] = cur.getTimePoint();
            queue.add(cur);
        }
        // 有了能够拿到咖啡的时间之后 我们才能切除所有杯子变得干净的时间
        return process(drinks, a, b, 0, 0);
    }

    /**
     * arr[i]代表i号咖啡机泡一杯咖啡的时间
     * N表示有N个人等着咖啡机泡咖啡 每台机器是串行泡咖啡
     * 只有一台洗杯子的机器 一次只能洗一个杯子 时间消耗a 串的洗
     * 同时咖啡杯也可以自由的挥发干净 时间消耗b 并行的挥发
     * 返回从开始等到所有咖啡机变干净的最短时间
     *
     * @param arr
     * @param n
     * @param a
     * @param b
     * @return
     */
    public static int bestTime2(int[] arr, int n, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<CoffeRecord> queue = new PriorityQueue<>(Comparator.comparingInt(o ->
                (o.getWorkTime() + o.getTimePoint())));


        for (int i = 0; i < arr.length; i++) {
            CoffeRecord record = new CoffeRecord();
            record.setWorkTime(arr[i]);
            record.setTimePoint(0);
            queue.add(record);
        }
        // 首先我们要解决一个问题是怎么得到所有人最佳喝到咖啡的排队时间
        // 这里的目的是站在上帝的视角上 给用户进行选择
        // 用户怎么进行站队  能够得到最快的时间能够喝上咖啡
        // 小跟堆弹出的时候就是i号用户能够拿到咖啡的最佳时间
        // 那么下一个用户能够到咖啡机的时间就是弹出的时间加上咖啡机泡咖啡的时间
        int[] drinks = new int[n];
        for (int i = 0; i < n; i++) {
            CoffeRecord cur = queue.poll();
            cur.setTimePoint(cur.getTimePoint() + cur.getWorkTime());
            drinks[i] = cur.getTimePoint();
            queue.add(cur);
        }
        // 有了能够拿到咖啡的时间之后 我们才能切除所有杯子变得干净的时间
        return dp(drinks, a, b);
    }

    /**
     * 设计一个函数  给定用户最佳的排队时间 返回直到所有杯子变干净的最短时间
     * dirnks是最佳的排队时间
     * air是挥发干净的时间
     * washtime是洗杯机洗干净杯子的时间
     * index当前杯子的位置 或者等着洗杯子的用户
     *
     * @return
     */
    public static int process(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        // 当前杯子选择使用洗杯机进行清洗 那么后序的杯子可以自由选择进行清洗或者自由挥发
        // 注释的这行代码合那关键 由于洗杯机是串行的 当前杯子选择清洗的时候 一定是前面已经做好的决定的最后时间
        // 咖啡杯必须空闲才行
//        int selfAir = drinks[index] + wash;
        int selfClean = Math.max(drinks[index], free) + wash;
        int selfOthersAir = process(drinks, wash, air, index + 1, selfClean);
        // 为什么要求最大值 是因为当前杯子选择使用洗杯机 其他杯子自由做选择 必须要等所有的杯子都清洗完成了之后 才能作为左右杯子都清洗完成的时间
        int p2 = Math.max(selfClean, selfOthersAir);

        // 要洗干净所有的杯子 有两种选择
        // 一种是当前杯子选择挥发 那么后续的杯子进行任意选择挥发或者是进行洗杯机的清洗
        int selfAir = drinks[index] + air;
        // 当前杯子已经选择了挥发 那么后序的杯子或者用户可以自由进行选择挥发或者是洗杯机的清洗
        int selfOthers = process(drinks, wash, air, index + 1, free);
        int p1 = Math.max(selfAir, selfOthers);

        // 当前杯子会产生两种结果 一种是选择清洗 一种是选择挥发  所有的情况都已经列举完成了
        // 题目要求的是会发干净的时间 也就是求这两种选择的最小时间

        return Math.min(p1, p2);
    }

    public static int dp(int[] drinks, int wash, int air) {
        int N = drinks.length;
        int free = 0;
        for (int i = 0; i < drinks.length; i++) {
            free = Math.max(free, drinks[i]) + wash;
        }
        int[][] dp = new int[N + 1][free + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int j = free; j >= 0; j--) {
                // 为什么会有这个判断 】
                // 实际上在递归的过程中并不会出现比free最大值大的时候
                // 在原始的递归过程中 并不会出现这种情况 分析一下原始递归
                // 在原始递归中 当index来到最后一个位置即index-1位置的时候
                // 尽管计算了Math.max(drinks[index], free) + wash 但是在递归的过程中 就已经终止了
                // 并不会将这个free的值进行传递下去 因此在这个过程中 free实际不会出现比全都使用洗杯机进行洗杯子的时间还大的情况
                // 这里为了避免越界 要进行判断
                if ((Math.max(drinks[i], j) + wash) > free) {
                    continue;
                }
                int selfClean = Math.max(drinks[i], j) + wash;
                int selfOthersAir = dp[i + 1][selfClean];
                int p2 = Math.max(selfClean, selfOthersAir);

                // 要洗干净所有的杯子 有两种选择
                // 一种是当前杯子选择挥发 那么后续的杯子进行任意选择挥发或者是进行洗杯机的清洗
                int selfAir = drinks[i] + air;
                // 当前杯子已经选择了挥发 那么后序的杯子或者用户可以自由进行选择挥发或者是洗杯机的清洗
                int selfOthers = dp[i + 1][j];
                int p1 = Math.max(selfAir, selfOthers);

                dp[i][j] = Math.min(p1, p2);
            }
        }

        return dp[0][0];
    }

}

class CoffeRecord {
    private int timePoint;
    private int workTime;


    public int getTimePoint() {
        return timePoint;
    }

    public void setTimePoint(int timePoint) {
        this.timePoint = timePoint;
    }

    public int getWorkTime() {
        return workTime;
    }

    public void setWorkTime(int workTime) {
        this.workTime = workTime;
    }
}
