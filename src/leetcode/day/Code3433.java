package leetcode.day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数 numberOfUsers 表示用户总数，另有一个大小为 n x 3 的数组 events 。
 *
 * 每个 events[i] 都属于下述两种类型之一：
 *
 * 消息事件（Message Event）：["MESSAGE", "timestampi", "mentions_stringi"]
 * 事件表示在 timestampi 时，一组用户被消息提及。
 * mentions_stringi 字符串包含下述标识符之一：
 * id<number>：其中 <number> 是一个区间 [0,numberOfUsers - 1] 内的整数。可以用单个空格分隔 多个 id ，并且 id 可能重复。
 * 此外，这种形式可以提及离线用户。
 * ALL：提及 所有 用户。
 * HERE：提及所有 在线 用户。
 * 离线事件（Offline Event）：["OFFLINE", "timestampi", "idi"]
 * 事件表示用户 idi 在 timestampi 时变为离线状态 60 个单位时间。用户会在 timestampi + 60 时自动再次上线。
 * 返回数组 mentions ，其中 mentions[i] 表示  id 为  i 的用户在所有 MESSAGE 事件中被提及的次数。
 *
 * 最初所有用户都处于在线状态，并且如果某个用户离线或者重新上线，其对应的状态变更将会在所有相同时间发生的消息事件之前进行处理和同步。
 *
 * 注意 在单条消息中，同一个用户可能会被提及多次。每次提及都需要被 分别 统计。
 *
 */
public class Code3433 {

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        // 按照时间戳从小到大排序，时间戳相同的，离线事件排在前面
        events.sort((a, b) -> {
            int ta = Integer.parseInt(a.get(1));
            int tb = Integer.parseInt(b.get(1));
            return ta != tb ? ta - tb : b.get(0).charAt(0) - a.get(0).charAt(0);
        });

        int[] ans = new int[numberOfUsers];
        int[] onlineT = new int[numberOfUsers];
        for (List<String> e : events) {
            int curT = Integer.parseInt(e.get(1)); // 当前时间
            String mention = e.get(2);
            if (e.get(0).charAt(0) == 'O') { // 离线
                onlineT[Integer.parseInt(mention)] = curT + 60; // 下次在线时间
            } else if (mention.charAt(0) == 'A') { // @所有人
                for (int i = 0; i < numberOfUsers; i++) {
                    ans[i]++;
                }
            } else if (mention.charAt(0) == 'H') { // @所有在线用户
                for (int i = 0; i < numberOfUsers; i++) {
                    if (onlineT[i] <= curT) { // 在线
                        ans[i]++;
                    }
                }
            } else { // @id
                for (String s : mention.split(" ")) {
                    int i = Integer.parseInt(s.substring(2));
                    ans[i]++;
                }
            }
        }
        return ans;
    }

    public int[] countMentions2(int numberOfUsers, List<List<String>> events) {
        // 按照时间戳从小到大排序，时间戳相同的，离线事件排在前面
        events.sort((a, b) -> {
            int ta = Integer.parseInt(a.get(1));
            int tb = Integer.parseInt(b.get(1));
            return ta != tb ? ta - tb : b.get(0).charAt(0) - a.get(0).charAt(0);
        });
        int[] ans = new int[numberOfUsers];
        Map<Integer,Integer> offineMap = new HashMap<>();
        for (List<String> e : events) {
            String name = e.get(0);
            // 当前时间
            int curT = Integer.parseInt(e.get(1));
            // 通知对象
            String mention = e.get(2);
            if ("OFFLINE".equals(name)) {
                // 事件类型是离线
                offineMap.put(Integer.parseInt(mention),curT);
            } else if ("ALL".equals(mention)) {
                for (int i = 0; i < numberOfUsers; i++) {
                    ans[i]++;
                }
            } else if ("HERE".equals(mention)) {
                // 在线
                for (int i = 0; i < numberOfUsers; i++) {
                    if (!offineMap.containsKey(i) || offineMap.get(i) + 60 <= curT) {
                        ans[i]++;
                    }
                }
            }else {
                String[] ids = mention.split(" ");
                for (String idStr : ids) {
                    int id = Integer.parseInt(idStr.substring(2));
                    ans[id]++;
                }
            }
        }
        return ans;
    }
}
