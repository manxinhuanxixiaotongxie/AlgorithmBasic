package leetcode.practice;

import java.util.ArrayList;
import java.util.List;

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
}
