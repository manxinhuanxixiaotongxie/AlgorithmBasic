package leetcode.week;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串 s。
 * <p>
 * 英文字母中每个字母的 镜像 定义为反转字母表之后对应位置上的字母。例如，'a' 的镜像是 'z'，'y' 的镜像是 'b'。
 * <p>
 * 最初，字符串 s 中的所有字符都 未标记 。
 * <p>
 * 字符串 s 的初始分数为 0 ，你需要对其执行以下过程：
 * <p>
 * 从左到右遍历字符串。
 * 对于每个下标 i ，找到距离最近的 未标记 下标 j，下标 j 需要满足 j < i 且 s[j] 是 s[i] 的镜像。然后 标记 下标 i 和 j，总分加上 i - j 的值。
 * 如果对于下标 i，不存在满足条件的下标 j，则跳过该下标，继续处理下一个下标，不需要进行标记。
 * 返回最终的总分。
 */
public class Code3412 {
    /**
     * 这种情况不能完全AC
     * 不能完全AC的原因是因为会存在覆盖场景
     * 举个例子：
     * zadavyayobbgqsexaabk
     * 器重的b与y是镜像关系  前面有两个b的时候 存在覆盖关系
     * 会错误第一个B与第二个y的缺失 导致少算了
     *
     * @param s
     * @return
     */
    public long calculateScore(String s) {
        long ans = 0;
        // 当前字符对应的镜像字符 以及镜像字符应对原字符串下标
        Map<Character, Info> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char cur = str[i];
            if (map.containsKey(cur) && map.get(cur).index < i && !map.get(cur).marked) {
                // 找到镜像字符
                Info info = map.get(cur);
                ans += i - info.index;
                // 标记为已使用
                info.marked = true;
            } else {
                // 计算镜像字符
                char mirrorChar = (char) ('a' + 'z' - cur);
                // a z
                //
                map.put(mirrorChar, new Info(mirrorChar, i));
            }

        }
        return ans;
    }

    /**
     * 针对上述遗漏的场景进行优化
     *
     * @param s
     * @return
     */
    public long calculateScore2(String s) {
        long ans = 0;
        // 当前字符对应的镜像字符 以及镜像字符应对原字符串下标
        Map<Character, List<Integer>> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            char cur = str[i];
            List<Integer> list = map.get(cur);
            if (list != null) {
                ans += i - list.getLast();
                list.removeLast();
                if (list.isEmpty()) {
                    map.remove(cur);
                }
                continue;
            }
            // 计算镜像字符
            char mirrorChar = (char) ('a' + 'z' - cur);
            map.computeIfAbsent(mirrorChar, k -> new ArrayList<>()).add(i);
        }
        return ans;
    }

    class Info {
        boolean marked = false;
        char s;
        int index;

        public Info(char s, int index) {
            this.s = s;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Code3412 code3412 = new Code3412();
        String s = "zadavyayobbgqsexaabk";
        long score = code3412.calculateScore(s);
        System.out.println("Score: " + score); // 输出: Score: 6
    }
}
