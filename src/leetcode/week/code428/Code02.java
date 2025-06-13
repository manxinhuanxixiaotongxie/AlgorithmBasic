package leetcode.week.code428;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 initialCurrency，表示初始货币类型，并且你一开始拥有 1.0 单位的 initialCurrency。
 * <p>
 * 另给你四个数组，分别表示货币对（字符串）和汇率（实数）：
 * <p>
 * pairs1[i] = [startCurrencyi, targetCurrencyi] 表示在 第 1 天，可以按照汇率 rates1[i] 将 startCurrencyi 转换为 targetCurrencyi。
 * pairs2[i] = [startCurrencyi, targetCurrencyi] 表示在 第 2 天，可以按照汇率 rates2[i] 将 startCurrencyi 转换为 targetCurrencyi。
 * 此外，每种 targetCurrency 都可以以汇率 1 / rate 转换回对应的 startCurrency。
 * 你可以在 第 1 天 使用 rates1 进行任意次数的兑换（包括 0 次），然后在 第 2 天 使用 rates2 再进行任意次数的兑换（包括 0 次）。
 * <p>
 * 返回在两天兑换后，最大可能拥有的 initialCurrency 的数量。
 * <p>
 * 注意：汇率是有效的，并且第 1 天和第 2 天的汇率之间相互独立，不会产生矛盾。
 */
public class Code02 {

    /**
     * dfs + 图
     *
     * @param initialCurrency
     * @param pairs1
     * @param rates1
     * @param pairs2
     * @param rates2
     * @return
     */
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, Double> day1Amount = calcAmount(pairs1, rates1, initialCurrency);
        Map<String, Double> day2Amount = calcAmount(pairs2, rates2, initialCurrency);

        double ans = 0;
        // 记录的是初始汇率到某个货币的金额
        for (Map.Entry<String, Double> e : day2Amount.entrySet()) {
            ans = Math.max(ans, day1Amount.getOrDefault(e.getKey(), 0.0) / e.getValue());
        }
        return ans;
    }

    private record Pair(String to, double rate) {
    }

    private Map<String, Double> calcAmount(List<List<String>> pairs, double[] rates, String initialCurrency) {
        // 邻接表法
        Map<String, List<Pair>> g = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            String x = pairs.get(i).get(0);
            String y = pairs.get(i).get(1);
            double r = rates[i];
            // 记录从某个点到另外一个点的汇率
            g.computeIfAbsent(x, k -> new ArrayList<>()).add(new Pair(y, r));
            g.computeIfAbsent(y, k -> new ArrayList<>()).add(new Pair(x, 1 / r));
        }

        Map<String, Double> amount = new HashMap<>();
        dfs(initialCurrency, 1, g, amount);
        return amount;
    }

    // g记录的是从某个货币到其他货币的原始汇率
    // amount是某个点到另外一个点的最终汇率金额
    private void dfs(String x, double curAmount, Map<String, List<Pair>> g, Map<String, Double> amount) {
        amount.put(x, curAmount);
        // 如果x不在图中，说明没有可兑换的货币，直接返回
        if (!g.containsKey(x)) {
            return;
        }
        // 从X出发 遍历X的所有节点
        for (Pair p : g.get(x)) {
            // 每个节点只需递归一次（重复递归算出来的结果是一样的，因为题目保证汇率没有矛盾）
            if (!amount.containsKey(p.to)) {
                dfs(p.to, curAmount * p.rate, g, amount);
            }
        }
    }

    public static void main(String[] args) {
        //initialCurrency = "USD", pairs1 = [["USD","EUR"]], rates1 = [1.0], pairs2 = [["EUR","JPY"]], rates2 = [10.0]
        Code02 code02 = new Code02();
        String initialCurrency = "USD";
        List<List<String>> pairs1 = new ArrayList<>();
        List<String> pair1 = new ArrayList<>();
        pair1.add("USD");
        pair1.add("EUR");
        pairs1.add(pair1);
        double[] rates1 = {1.0};
        List<List<String>> pairs2 = new ArrayList<>();
        List<String> pair2 = new ArrayList<>();
        pair2.add("EUR");
        pair2.add("JPY");
        pairs2.add(pair2);
        double[] rates2 = {10.0};
        double result = code02.maxAmount(initialCurrency, pairs1, rates1, pairs2, rates2);
        System.out.println(result); // 输出 1.0
    }

}
