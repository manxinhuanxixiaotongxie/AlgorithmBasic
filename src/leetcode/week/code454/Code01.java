package leetcode.week.code454;

import java.util.HashMap;
import java.util.Map;

public class Code01 {
    public String generateTag(String caption) {
        if (caption == null || caption.length() == 0) return "";

        String[] split = caption.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(split[0].toLowerCase());
        for (int i = 1; i < split.length; i++) {
            // 将首字母大写
            split[i] = split[i].substring(0, 1).toUpperCase() + split[i].substring(1).toLowerCase();
            sb.append(split[i]);
        }
        if (sb.length() > 100) {
            return sb.substring(0, 100);
        } else {
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String caption = "   ";
        System.out.println(new Code01().generateTag(caption));
    }

    public int specialTriplets(int[] nums) {
        int n = nums.length;
        long[] leftIndex = new long[n];
        long[] rightIndex = new long[n];

        Map<Integer, Long> leftDoubleMap = new HashMap<>();
        leftDoubleMap.put(nums[0], 1L);

        for (int i = 1; i < n - 1; i++) {
            leftIndex[i] = leftDoubleMap.getOrDefault(nums[i] << 1, 0L);
            leftDoubleMap.put(nums[i], leftDoubleMap.getOrDefault(nums[i], 0L) + 1);
        }

        Map<Integer, Long> rightDoubleMap = new HashMap<>();
        rightDoubleMap.put(nums[n - 1], 1L);

        for (int i = n - 2; i > 0; i--) {
            rightIndex[i] = rightDoubleMap.getOrDefault(nums[i] << 1, 0L);
            rightDoubleMap.put(nums[i], rightDoubleMap.getOrDefault(nums[i], 0L) + 1);
        }

        long ans = 0;
        for (int i = 1; i < n - 1; i++) {
            ans += (leftIndex[i] * rightIndex[i]) % 1000000007;
        }

        return (int) (ans % 1000000007);
    }

}
