package leetcode.day;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 你正在把积木堆成金字塔。每个块都有一个颜色，用一个字母表示。每一行的块比它下面的行 少一个块 ，并且居中。
 * <p>
 * 为了使金字塔美观，只有特定的 三角形图案 是允许的。一个三角形的图案由 两个块 和叠在上面的 单个块 组成。模式是以三个字母字符串的列表形式 allowed 给出的，
 * 其中模式的前两个字符分别表示左右底部块，第三个字符表示顶部块。
 * <p>
 * 例如，"ABC" 表示一个三角形图案，其中一个 “C” 块堆叠在一个 'A' 块(左)和一个 'B' 块(右)之上。请注意，这与 "BAC" 不同，"B" 在左下角，"A" 在右下角。
 * 你从作为单个字符串给出的底部的一排积木 bottom 开始，必须 将其作为金字塔的底部。
 * <p>
 * 在给定 bottom 和 allowed 的情况下，如果你能一直构建到金字塔顶部，使金字塔中的 每个三角形图案 都是在 allowed 中的，则返回 true ，否则返回 false 。
 *
 */
public class Code756 {
    /**
     * 这样有问题  应该使用并查集
     *
     * @param bottom
     * @param allowed
     * @return
     */
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        // 按照字典序对给点的allowed进行排序
        Collections.sort(allowed);
        // 假设底层的长度是4 那么 下一层就是 3 2 1 这样 如果能到说明就是可以构建起来金字塔
        int level = bottom.length();
        while (level > 2) {
            // level == 2 的时候 只需要判断allowed有没有最后一个字符满足条件即可
            // 当前底层的长度
            if (level != bottom.length()) {
                return false;
            }
            int len = bottom.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len - 1; i++) {
                // 准备构建下一层
                // 当前两位长度的子串
                String subStr = bottom.substring(i, i + 2);
                // 如果包含的话 说明可以拿到当前的金字塔的上一层的字母
                // 找到满足条件的第三个字母
                boolean flag = false;
                for (String pattern : allowed) {
                    if (pattern.startsWith(subStr)) {
                        // 获取最后一位字符
                        sb.append(pattern.charAt(2));
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    return false;
                }
            }
            // 重新设置底部
            bottom = sb.toString();
            level--;
        }
        // 已经到了最后一层说明是可以构建金字塔的
        boolean flag = true;
        for (String pattern : allowed) {
            if (pattern.startsWith(bottom)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            return false;
        }

        return flag;
    }

    static void main() {
        Code756 code = new Code756();
        String bottom = "AAAA";
        List<String> allowed = Arrays.asList("AAB","AAC","BCD","BBE","DEF");
        boolean res = code.pyramidTransition(bottom, allowed);
        System.out.println(res);
    }

}
