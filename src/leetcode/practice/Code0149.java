package leetcode.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 */
public class Code0149 {
    public int maxPoints(int[][] points) {

        /**
         * 思路：
         * 必须要包含某个点
         * 后面的点与当前点计算一个斜率
         * 定义一个map  key是斜率 value是数量
         */
        int ans = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                int gcd = gcd(x, y);
                x /= gcd;
                y /= gcd;
                String key = x + "_" + y;
                map.merge(key, 1, Integer::sum);
            }
            int max = 0;
            for (Integer value : map.values()) {
                max = Math.max(max, value);
            }
            ans = Math.max(ans, max + 1);
        }
        return ans;
    }

    /**
     * 辗转相除
     *
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int gcd2(int a,int b){
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    static void main() {
        Code0149 code0149 = new Code0149();
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(code0149.maxPoints(points));
    }
}
