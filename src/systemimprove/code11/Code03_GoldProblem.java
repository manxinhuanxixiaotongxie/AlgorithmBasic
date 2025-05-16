package systemimprove.code11;

public class Code03_GoldProblem {

    // 暴力方法
    public int minValue(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    // 暴力方法
    public int process(int[] arr, int pre) {
        // 将问题进行转化
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // 怎么进行转化呢？
                // 金矿的拆分  ==》转化成金矿的合并
                // 拆分所花费的数量与合并所花费的数量是一样的
                // 那么合并花费的数量是多少呢？
                // 之前已经合并了多少个金矿加上现在合并的两个金矿i和j
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    /**
     * 此方法的含义是：
     * 进行i位置与j位置进行合并
     * 生成一个新的数组 是i位置与j位置合并与数组剩下的数组合成的一个新的数组
     * int[] arrNew = new int[arr.length - 1];
     * // 新数组位置的下标
     * int ansi = 0;
     * // 遍历原始数组
     * for(int index = 0;index < arr.length;index++))   {
     * // 将i位置与j位置的元素进行合并
     * arrNew[ansi++] = arr[i] + arr[j];
     * // 其他位置维持原样
     * if (index != i && index != j) {
     * arrNew[ansi++] = arr[index];
     * }
     * }
     *
     * @param arr
     * @param i
     * @param j
     * @return
     */
    public int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }
}
