package leetcode.week.code428;

import java.util.ArrayList;
import java.util.List;

public class Code3387 {
    /**
     * 不能AC
     *
     * 见code02 使用DFS+图
     *
     * @param initialCurrency
     * @param pairs1
     * @param rates1
     * @param pairs2
     * @param rates2
     * @return
     */
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        // 遍历pair2
        boolean findInitialCurrency = false;
        List<String> can = new ArrayList<>();
        for (List<String> list : pairs2) {
            if (initialCurrency.equals(list.get(1))) {
                findInitialCurrency = true;
                can.add(list.get(0));
            }
        }
        if (!findInitialCurrency) {
            return 1;
        }
        // 走到这一步说明找到了初始货币
        double max = Integer.MIN_VALUE;
        for (int i = 0; i < pairs1.size(); i++) {
            for (int j = 0; j < can.size(); j++) {
                if (pairs1.get(i).get(1).equals(can.get(j))) {
                    max = Math.max(max, rates1[i] * rates2[j]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Code3387 code3387 = new Code3387();
        // 输入： initialCurrency = "EUR", pairs1 = [["EUR","USD"],["USD","JPY"]], rates1 = [2.0,3.0], pairs2 = [["JPY","USD"],["USD","CHF"],["CHF","EUR"]], rates2 = [4.0,5.0,6.0]
        // 输出：24.00000
        List<List<String>> pairs1 = new ArrayList<>();
        List<String> pair1 = new ArrayList<>();
        pair1.add("EUR");
        pair1.add("USD");
        pairs1.add(pair1);
        List<String> pair2 = new ArrayList<>();
        pair2.add("USD");
        pair2.add("JPY");
        pairs1.add(pair2);
        double[] rates1 = new double[]{2.0, 3.0};
        List<List<String>> pairs2 = new ArrayList<>();
        List<String> pair3 = new ArrayList<>();
        pair3.add("JPY");
        pair3.add("USD");
        pairs2.add(pair3);
        List<String> pair4 = new ArrayList<>();
        pair4.add("USD");
        pair4.add("CHF");
        pairs2.add(pair4);
        List<String> pair5 = new ArrayList<>();
        pair5.add("CHF");
        pair5.add("EUR");
        pairs2.add(pair5);
        double[] rates2 = new double[]{4.0, 5.0, 6.0};
        System.out.println(code3387.maxAmount("EUR", pairs1, rates1, pairs2, rates2));
    }
}

