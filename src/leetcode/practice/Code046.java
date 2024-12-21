package leetcode.practice;

import java.util.ArrayList;
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
        Set<Integer> set = new HashSet<>();
        process(nums, 0, set, "", ans);
        return ans;
    }


    private void process(int[] nums, int index, Set<Integer> set, String path, List<List<Integer>> ans) {
        if (index == nums.length) {
            String[] split = path.split("#");
            List<Integer> list = new ArrayList<>();
            for (String s : split) {
                if (s.equals("")) {
                    continue;
                }
                list.add(Integer.parseInt(s));
            }
            ans.add(list);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(i)) {
                set.add(i);
                process(nums, index + 1, set, path + "#" + nums[i], ans);
                set.remove(i);
            }
        }
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        process2(nums, 0, ans);
        return ans;
    }

    private void process2(int[] nums, int index, List<List<Integer>> ans) {
        if (index == nums.length) {
            List<Integer> cur = new ArrayList<>();
            for (int num : nums) {
                cur.add(num);
            }
            ans.add(cur);
            return;
        }
        // 先从第Index位置开始选
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            process2(nums, index + 1, ans);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
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
