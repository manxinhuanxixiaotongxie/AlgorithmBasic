package leetcode.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个解法是有问题的
 * 报错 无法求解出答案
 */
public class Code039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        process(candidates, 0, res, "", target);
        return res;
    }

    private void process(int[] candidates, int index, List<List<Integer>> res, String path, int rest) {
        if (rest == 0) {
            List<Integer> list = new ArrayList<>();
            String[] split = path.split(",");
            for (String s : split) {
                list.add(Integer.parseInt(s));
            }
            res.add(list);
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (rest - candidates[i] >= 0) {
                    process(candidates, i, res, path + ',' + candidates[i], rest - candidates[i]);
                }
            }
        }
    }

    List<List<Integer>> ans;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        ans = new ArrayList<>();
        process2(candidates, 0, target, new ArrayList<>());
        return ans;
    }

    public void process2(int[] candidates, int index, int rest, List<Integer> cur) {
        if (index == candidates.length) {
            if (rest == 0) {
                ans.add(new ArrayList<>(cur));
            }
        } else {
            for (int zhang = 0; zhang * candidates[index] <= rest; zhang++) {
                for (int i = 0; i < zhang; i++) {
                    cur.add(candidates[index]);
                }
                process2(candidates, index + 1, rest - zhang * candidates[index], cur);
                for (int i = 0; i < zhang; i++) {
                    cur.remove(cur.size() - 1);
                }
            }
        }
    }
}
