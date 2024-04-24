package systemimprove.code19;

import java.util.Map;

/**
 * 贴纸问题：
 * <p>
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class Code03_PrintPaper {

    public int minStickers(String[] stickers, String target) {
        int process = process(stickers, target);
        return process == Integer.MAX_VALUE ? -1 : process;
    }

    public int minStickers2(String[] stickers, String target) {
        int process = process2(stickers, target, new java.util.HashMap<>());
        return process == Integer.MAX_VALUE ? -1 : process;
    }

    public int minStickers3(String[] stickers, String target) {
        int[][] stickersNums = new int[stickers.length][26];
        // 按照行的方式进行填写
        for (int i = 0; i < stickers.length; i++) {
            String sticker = stickers[i];
            for (int j = 0; j < sticker.length(); j++) {
                stickersNums[i][sticker.charAt(j) - 'a']++;
            }
        }
        int ans = process3(stickersNums, target, new java.util.HashMap<>());
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 设计一个递归函数 process 方法的功能是返回能够组成target的最少的贴纸数量
    public int process(String[] stickers, String rest) {
        if (rest.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String temp = except(sticker, rest);
            if (temp.length() != rest.length()) {
                // 当前的最小值与之前已经得到的最小值进行比较
                // 注释的这行代码有问题process函数有可能返回的是Integer.MAX_VALUE
//                min = Math.min(min,process(stickers,rest)+1);
                min = Math.min(min, process(stickers, temp));
            }
        }
        // 已经得到了最小值
        // 求完之后再加1
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    // rest无法进行量化 改成dp的成本很高 使用就记忆化搜索
    public int process2(String[] stickers, String rest, Map<String, Integer> cache) {
        if (cache.get(rest) != null) {
            return cache.get(rest);
        }
        if (rest.isEmpty()) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String temp = except(sticker, rest);
            if (temp.length() != rest.length()) {
                // 当前的最小值与之前已经得到的最小值进行比较
                // 注释的这行代码有问题process函数有可能返回的是Integer.MAX_VALUE
//                min = Math.min(min,process(stickers,rest)+1);
                min = Math.min(min, process2(stickers, temp, cache));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        cache.put(rest, ans);
        return ans;
    }

    public int process3(int[][] stickers, String rest, Map<String, Integer> cache) {
        if (cache.containsKey(rest)) {
            return cache.get(rest);
        }
        if (rest.isEmpty()) {
            return 0;
        }
        char[] target = rest.toCharArray();
        int[] tcounts = new int[26];
        for (char cha : target) {
            tcounts[cha - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int[] sticker : stickers) {
            if (sticker[target[0] - 'a'] > 0) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tcounts[j] > 0) {
                        int nums = tcounts[j] - sticker[j];
                        for (int k = 0; k < nums; k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String temp = builder.toString();
                min = Math.min(min, process3(stickers, temp, cache));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        cache.put(rest, ans);
        return ans;
    }

    public String except(String sticker, String rest) {
        int[] count = new int[26];
        for (int i = 0; i < rest.length(); i++) {
            count[rest.charAt(i) - 'a']++;
        }
        for (int i = 0; i < sticker.length(); i++) {
            count[sticker.charAt(i) - 'a']--;
        }

        StringBuilder builder = new StringBuilder();
        // 在count数组中的提取出还能剩下的字符
        for (int i = 0; i < count.length; i++) {
            // count[i]代表的是i位置字符还有多少个
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public String except2(int[] sticker, String rest) {
        int[] count = new int[26];
        for (int i = 0; i < rest.length(); i++) {
            count[rest.charAt(i) - 'a']++;
        }

        StringBuilder builder = new StringBuilder();
        // 在count数组中的提取出还能剩下的字符
        for (int i = 0; i < count.length; i++) {
            // count[i]代表的是i位置字符还有多少个
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    builder.append((char) (i + 'a'));
                }
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String[] stickers = {"with", "example", "science"};
        String target = "thehat";
        Code03_PrintPaper code03_printPaper = new Code03_PrintPaper();
        System.out.println(code03_printPaper.minStickers(stickers, target));
    }
}
