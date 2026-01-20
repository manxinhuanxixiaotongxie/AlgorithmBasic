package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

public class Code039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        process(candidates, target, 0, ans, new ArrayList<>());
        return ans;
    }

    public void process(int[] nums, int rest, int index, List<List<Integer>> res, List<Integer> path) {
        if (index == nums.length) {
            if (rest == 0) {
                res.add(new ArrayList<>(path));
            }
        } else if (rest == 0) {
            res.add(new ArrayList<>(path));
        } else {
            // 普遍位置
            for (int zhang = 0; zhang * nums[index] <= rest && index < nums.length; zhang++) {
                for (int i = 0; i < zhang; i++) {
                    path.add(nums[index]);
                }
                process(nums, rest - zhang * nums[index], index + 1, res, path);
                for (int i = 0; i < zhang; i++) {
                    path.remove(path.size() - 1);
                }
            }
        }
    }
}
