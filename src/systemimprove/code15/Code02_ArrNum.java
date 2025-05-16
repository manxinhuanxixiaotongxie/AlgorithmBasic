package systemimprove.code15;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个数组，如果子数组满足 最大值减去最小值<=num的情况下，求有多少个子数组
 */
public class Code02_ArrNum {


    // 暴力方法
    public int getNums1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // 找到所有的子数组
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int max = arr[i];
                int min = arr[i];
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                }
                if (max - min <= num) {
                    count++;
                }
            }
        }
        return count;


    }

    /**
     * 窗口最大值最小值已经有了 接下来怎么处理
     * R会不会回退？
     * 怎么理解？
     * 给定一个例子：
     * [5,3,3,1,4,7]    num = 3
     * i等于0的时候
     * 窗口最大值跟最小值都是5   5小于等于3
     * i来到1的时候 窗口最大值的更新结构5,3
     * 窗口最小值变成3
     * 窗口最大值减去最小值 5-3 = 2  满足条件
     * i来到2位置的时候
     * 窗口最大值的更新结构变成了5,3 只是index发生了变化 值没变
     * 窗口最小值的更新结构依然是3 满足条件
     * 来到3位置的时候
     * 窗口最大值的更新结构变成了5,3,1
     * 窗口最小值的更新结构变成了1
     * 此时不满足条件
     * 来到4位置的时候
     * 窗口最大值的更新结构来到了5,4
     * 窗口最小值的更新结构变更了1.4最小值还是1 满足条件
     * <p>
     * 来到5位置的时候
     * 窗口最大值的更新结构来到了7
     * 窗口最小值的更新结构变成了1,4,7 不满足条件 不产生新的子数组
     * <p>
     * 分析以上过程：
     * R是不会回退的
     * 分析一下为什么？
     * 1.最大窗口的更新结构的值只会变大
     * 2.最小窗口的更新结构的值只会变小
     * <p>
     * <p>
     * <p>
     * 以L开头的位置有多少个子数组满足条件
     * 数组是1 4 3 7 6 num的值是5
     * 怎么理解这个过程：
     * <p>
     * <p>
     * 生成两个双端队列maxQueue和minQueue。当子数组为arr[i..j]时，maxQueue维护了窗口子数组arr[i..j]的最大值更新的结
     * 构，minQueue维护了窗口子数组arr[i..j]的最小值更新的结构。当子数组arr[i..j]向右扩一个位置变成arr[i..j+1]时，
     * maxQueue和minQueue结构可以在O(1)的时间内更新，并且可以在O(1)的时间内得到arr[i..j+1]的最大值和最小值。当子数组
     * arr[i..j]向左缩一个位置变成arr[i+1..j]时，maxQueue和minQueue结构依然可以在O(1)的时间内更新，并且在O(1)的时间
     * 内得到arr[i+1..j]的最大值和最小值。
     * <p>
     * 通过分析题目满足的条件，可以得到如下两个结论：
     * １、如果子数组arr[i..j]满足条件，即max(arr[i..j])-min(arr[i..j])<=num，那么arr[i..j]中的每一个子数组，即
     * arr[k..l](i<=k<=l<=j)都满足条件。我们以子数组arr[i..j-1]为例说明，arr[i..j-1]最大值只可能小于或等于
     * arr[i..j]的最大值，arr[i..j-1]最小值只可能大于或等于arr[i..j]的最小值，所以arr[i..j-1]必然满足条件，同理，
     * arr[i..j]中的每一个子数组都满足条件。
     * ２、如果子数组arr[i..j]不满足条件，那么所有包含arr[i..j]的子数组，即arr[k..l](k<=i<=j<=l)都不满足条件。证明过
     * 程同第一个结论。
     * <p>
     * 根据双端队列maxQueue和minQueue的结构性质，以及如上两个结论，设计整个过程如下：
     * １、生成两个双端队列maxQueue和minQueue，含义如上文所说。生成两个整型变量i和j，表示子数组的范围，即arr[i..j]。生
     * 成整型变量res，表示所有满足条件的子数组数量。
     * ２、令j不断向右移动（j++），表示arr[i..j]一直向右扩大，并不断更新maxQueue和minQueue结构，保证maxQueue和
     * minQueue始终维持动态窗口最大值和最小值的更新结构。一旦出现arr[i..j]不满足条件的情况，j向右扩的过程停止，此时
     * arr[i..j-1]、arr[i..j-2]、arr[i..j-3]、...、arr[i..j]一定都是满足条件的。也就是说，所有必须以arr[i]作为第一
     * 个元素的子数组，满足条件的数量为j-i个。于是令res+=j-i。
     * ３、当进行完步骤2，令i向右移动一个位置，并对maxQueue和minQueue做出相应的更新，maxQueue和minQueue从原来的
     * arr[i..j]窗口变成arr[i+1..j]窗口的最大值和最小值的更新结构。然后重复步骤2，也就是求所有必须以arr[i+1]作为第一个
     * 元素的子数组中，满足条件的数量有多少个。
     * ４、根据步骤2和步骤3，依次求出以arr[0]、arr[1]、...、arr[N-1]作为第一个元素的子数组中满足条件的数量分别有多少个，
     * 累加起来的数量就是最终的结果。
     * 上述过程中，所有的下标值最多进maxQueue和minQueue一次，出maxQueue和minQueue一次。i和j的值也不断增加，并且从来不
     * 减小。所以整个过程的时间复杂度为O(N)。
     */

    public static int getNum4(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {
                // 1 2 3 4
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                // 5 4 3 2 1
                // 0 1 2 3 4
                // L       R
                // L=0 R=4
                //
                maxWindow.addLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            // 以i开头的子数组数量
            count += R - L;
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
        }
        return count;
    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        Code02_ArrNum test = new Code02_ArrNum();
        int maxValue = 100;
        int maxLength = 30;
        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = test.generateArr(maxValue, maxLength);
            int num = (int) (Math.random() * maxValue) + 1;
            int ans1 = test.getNums1(arr, num);
            int ans4 = getNum4(arr, num);
            if (ans1 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
