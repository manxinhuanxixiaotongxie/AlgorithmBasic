package leetcode.practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code060 {
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        for (int i = 1; i <= n; i++) {
            factorial[i - 1] = i;
        }
        List<String> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        process2(factorial, 0, new StringBuilder(), res, set,k);
        return res.get(k - 1);
    }

    private void process(int[] nums, int index, List<String> res) {
        if (index == nums.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]);
            }
            res.add(sb.toString());
        } else {
            for (int i = index; i < nums.length; i++) {
                swap(nums, index, i);
                process(nums, index + 1, res);
                swap(nums, index, i);
            }
        }
    }

    private void process2(int[] nums, int index,StringBuilder path, List<String> res,Set<Integer> set,int k) {
        if (res.size() == k) {
            return;
        }
        if (index == nums.length) {
            res.add(path.toString());
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!set.contains(i)) {
                    set.add(i);
                    process2(nums, index + 1, path.append(nums[i]), res, set,k);
                    path.deleteCharAt(path.length() - 1);
                    set.remove(i);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }

    }

    public static void main(String[] args) {
        Code060 code060 = new Code060();
        System.out.println(code060.getPermutation(3, 3));
    }
}
