package systemimprove.dp.fromrecursiontpdp;

import java.util.HashMap;
import java.util.Objects;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * <p>
 * <p>
 * <p>
 * 所以返回2
 * <p>
 * <p>
 * <p>
 * 从左往右的尝试模型
 * 设计一个递归函数 函数的意义是：
 * 从0-位置开始在str上进行选择 返回组成目标字符串的最小张数
 */
public class Code07_StickersToSpellWord {
    // 返回至少需要多少张贴纸可以完成这个任务
    // 只能改成记忆化搜索
    public int minStickers1(String[] stickers, String target) {
        HashMap<String, Integer> dp = new HashMap<>();
        int ans = process1(stickers, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public int process1(String[] stickers, String rest, HashMap<String, Integer> dp) {
        if (dp.containsKey(rest)) {
            return dp.get(rest);
        }
        if (Objects.equals(rest, "")) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String stitck : stickers) {
            String left = rest(stitck, rest);
            if (left.length() != rest.length()) {
                min = Math.min(min, process1(stickers, left, dp));
            }
        }
        min = min == Integer.MAX_VALUE ? min : min + 1;
        dp.put(rest, min);
        return min;

    }

    private String rest(String stick, String rest) {
        if (Objects.equals(stick, "") || stick == null) {
            return rest;
        }

        char[] stickStr = stick.toCharArray();
        char[] restStr = rest.toCharArray();
        int[] map = new int[26];
        for (int i = 0; i < restStr.length; i++) {
            map[restStr[i] - 'a']++;
        }

        for (int i = 0; i < stickStr.length; i++) {
            map[stickStr[i] - 'a']--;
        }

        // 使用rest减去stick
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (map[i] > 0) {
                while (map[i] > 0) {
                    sb.append((char) (i + 'a'));
                    map[i]--;
                }
            }
        }
        return sb.toString();
    }

}
