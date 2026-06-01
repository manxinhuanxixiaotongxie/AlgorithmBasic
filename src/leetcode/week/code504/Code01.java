package leetcode.week.code504;

import java.util.HashMap;
import java.util.Map;

/**
 * n 的 得分 定义为：对所有 不同 数字 d，计算 d * freq(d) 的总和，其中 freq(d) 表示数字 d 在 n 中出现的次数。
 * <p>
 * 返回一个整数，表示 n 的得分。
 */
public class Code01 {
    public int digitFrequencyScore(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        while (n != 0) {
            map.merge(n % 10, 1, Integer::sum);
            n /= 10;
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans += entry.getKey() * entry.getValue();
        }
        return ans;
    }
}
