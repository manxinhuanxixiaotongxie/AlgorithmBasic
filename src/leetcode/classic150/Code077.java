package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 */
public class Code077 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        char[] path = new char[n + 1];
        process(n, 0, k, res, path);
        return res;
    }

    /**
     * 在index-n范围内选择k个数 组合
     * @param n
     * @param index
     * @param rest
     */
    public void process(int n,int index,int rest,List<List<Integer>> res, char[] path) {
        if (index == n) {
            if (rest == 0) {
                // 收集结果
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < path.length; i++) {
                    if (path[i] == 1) {
                        ans.add(i + 1);
                    }
                }
                res.add(ans);
            }
            return;
        }
        if (rest == 0) {
            // 收集结果
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < path.length; i++) {
                if (path[i] == 1) {
                    ans.add(i + 1);
                }
            }
            res.add(ans);
            return;
        }
        // 普遍位置
        // 当前index位置选
        path[index] = 1;
        process(n, index + 1, rest - 1, res, path);
        path[index] = 0;
        // 当前位置不选
        process(n, index + 1, rest, res, path);
    }
}
