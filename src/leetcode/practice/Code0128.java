package leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class Code0128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        NumsUnionSet unionSet = new NumsUnionSet(nums);
        // 建立并查集之后
        // 遍历数组
        // 如果当前元素的前一个元素在数组中
        // 那么合并
        // 如果当前元素的后一个元素在数组中
        // 那么合并
        // 合并的时候更新最大长度
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], i);
            if (map.containsKey(nums[i] - 1)) {
                unionSet.union(i, map.get(nums[i] - 1));
            }
            if (map.containsKey(nums[i] + 1)) {
                unionSet.union(i, map.get(nums[i] + 1));
            }
        }
        int[] parent = unionSet.getParent();
        int res = 0;
        for (int i = 0; i < parent.length; i++) {
            res = Math.max(res, parent[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
//        int[] nums = {0,-1};
//        int[] nums = {1, 2, 0, 1};
        Code0128 code0128 = new Code0128();
        System.out.println(code0128.longestConsecutive(nums));

    }
}

class NumsUnionSet {
    private int maxLength = 1;
    private int[] parent;
    private int[] size;

    NumsUnionSet(int[] nums) {
        parent = new int[nums.length];
        size = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int[] getParent() {
        return parent;
    }


    public void union(int i, int j) {
        int aFather = findFather(i);
        int bFather = findFather(j);
        if (aFather != bFather) {
            if (size[aFather] < size[bFather]) {
                parent[aFather] = bFather;
                size[bFather] += size[aFather];
                size[aFather] = 0;
            } else {
                parent[bFather] = aFather;
                size[aFather] += size[bFather];
                size[bFather] = 0;
            }
        } else {
            System.out.println("i = " + i + " j = " + j);
        }
    }

    private int findFather(int i) {
        // 一路向上找
        // 向上找的过程当中 进行合并
        int[] stack = new int[parent.length];
        int stackSize = 0;
        while (parent[i] != i) {
            stack[stackSize++] = i;
            i = parent[i];
        }
        while (stackSize > 0) {
            parent[stack[--stackSize]] = i;
        }
        return i;
    }
}
