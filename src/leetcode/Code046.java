package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code046 {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        process(nums, new HashSet<>(), 0, cur, ans);
        return ans;
    }

    private void process(int[] nums, Set<Integer> set, int index, List<Integer> cur, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> list = new ArrayList<>(cur);
            for (int num : set) {
                list.add(nums[num]);
            }
            ans.add(list);
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!set.contains(nums[i])) {
                    set.add(i);
                    process(nums, set, index + 1, cur, ans);
                    set.remove(i);
                }
            }
        }
    }


    public static void main(String[] args) {
        Code046 code046 = new Code046();
        int[] nums = {1, 2, 3};
        List<List<Integer>> permute = code046.permute(nums);
        for (List<Integer> list : permute) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

}
