package leetcode.week.code147;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 一个任务管理器系统可以让用户管理他们的任务，每个任务有一个优先级。这个系统需要高效地处理添加、修改、执行和删除任务的操作。
 * <p>
 * 请你设计一个 TaskManager 类：
 * <p>
 * TaskManager(vector<vector<int>>& tasks) 初始化任务管理器，初始化的数组格式为 [userId, taskId, priority] ，表示给 userId
 * 添加一个优先级为 priority 的任务 taskId 。
 * <p>
 * void add(int userId, int taskId, int priority) 表示给用户 userId 添加一个优先级为 priority 的任务 taskId ，输入 保证 taskId 不在系统中。
 * <p>
 * void edit(int taskId, int newPriority) 更新已经存在的任务 taskId 的优先级为 newPriority 。输入 保证 taskId 存在于系统中。
 * <p>
 * void rmv(int taskId) 从系统中删除任务 taskId 。输入 保证 taskId 存在于系统中。
 * <p>
 * int execTop() 执行所有用户的任务中优先级 最高 的任务，如果有多个任务优先级相同且都为 最高 ，执行 taskId 最大的一个任务。执行完任务后，taskId
 * 从系统中 删除 。同时请你返回这个任务所属的用户 userId 。如果不存在任何任务，返回 -1 。
 * <p>
 * 注意 ，一个用户可能被安排多个任务。
 */
public class TaskManager {


    /**
     * 1.使用系统提供的优先级队列实现加强堆
     * 优先级相等taskId大的在前面
     * 优先级不相等 优先级大的在前面
     * // taskId -> (priority, userId)
     * 2.自己实现加强堆 新增indexMap作为加强堆的索引
     */
    // taskId -> (priority, userId)
    // 额外使用一个 map 来存储 taskId 对应的优先级和 userId
    private final Map<Integer, int[]> mp = new HashMap<>();
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
        // mp.get(taskId)[1] userId
        add(mp.get(taskId)[1], taskId, newPriority);
    }

    public void rmv(int taskId) {
        // 懒删除
        mp.get(taskId)[0] = -1;
    }

    /**
     * 使用额外map记录taskId与最新的优先级和userId的映射关系
     * 如果优先级被修改过 那么记录一定与堆顶的优先级不一致
     * 如果优先级被删除过 那么记录一定与堆顶的优先级不一致
     * 如果堆顶的优先级与记录一致 那么说明这个数据是最新的
     *
     * @return
     */
    public int execTop() {
        // 进行堆的调整
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
