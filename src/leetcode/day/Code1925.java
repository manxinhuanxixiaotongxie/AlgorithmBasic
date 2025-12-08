package leetcode.day;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 一个 平方和三元组 (a,b,c) 指的是满足 a^2 + b^2 = c^2 的 整数 三元组 a，b 和 c 。
 * <p>
 * 给你一个整数 n ，请你返回满足 1 <= a, b, c <= n 的 平方和三元组 的数目。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 250
 *
 *
 *
 */
public class Code1925 {

    Set<Integer> set;
    private void init(int n) {
         set = new HashSet<>();
        for (int i = 2; i <= n; i++) {
            set.add(i * i);
        }

    }

    public int countTriples(int n) {
        int ans = 0;
        init(n);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (set.contains(i * i + j * j)) ans += 1;
            }
        }
        return ans;
    }

    static void main() {
        Code1925 code1925 = new Code1925();
        System.out.println(code1925.countTriples(12)); // 4
        System.out.println(code1925.countTriples(5)); // 2
    }
}
