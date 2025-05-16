package leetcode.week;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Dijkstra
 * 思路与Dijkstra算法类似  维护 一个加强堆
 * <p>
 * 本题在堆中维护一个多余的数据
 */
public class TaskManager {
    private final Map<Integer, int[]> mp = new HashMap<>(); // taskId -> (priority, userId)
    // 大跟堆 使用优先级进行维护大跟堆
    // 如果优先级相同 那么根据taskid进行排序
    private final PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]); // (priority, taskId, userId)

    public TaskManager(List<List<Integer>> tasks) {
        for (List<Integer> task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        mp.put(taskId, new int[]{priority, userId});
        pq.offer(new int[]{priority, taskId, userId});
    }

    public void edit(int taskId, int newPriority) {
        // 懒修改
        add(mp.get(taskId)[1], taskId, newPriority);
    }

    public void rmv(int taskId) {
        // 懒删除
        mp.get(taskId)[0] = -1;
    }

    public int execTop() {
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int priority = top[0], taskId = top[1], userId = top[2];
            int[] p = mp.get(taskId);
            // 如果货不对板，堆顶和 mp 中记录的不一样，说明这个数据已被修改/删除，不做处理
            if (p[0] == priority && p[1] == userId) {
                rmv(taskId);
                return userId;
            }
        }
        return -1;
    }
}


/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */