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
public class TaskManager2 {


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
    private PriorityQueue<Info> heap = null;

    public TaskManager2(List<List<Integer>> tasks) {
        heap = new PriorityQueue<>((o1, o2) -> {
            if (o1.priority == o2.priority) {
                return o2.taskId - o1.taskId;
            } else {
                return o2.priority - o1.priority;
            }
        });
        for (List<Integer> task : tasks) {
            int userId = task.get(0);
            int taskId = task.get(1);
            int priority = task.get(2);
            Info info = new Info(taskId, priority, userId);
            add(info);
        }
    }

    public void add(int userId, int taskId, int priority) {
        // 添加到堆中
        Info info = new Info(taskId, priority, userId);
        add(info);
    }

    private void add(Info info) {
        heap.add(info);
        // 更新 mp 中的映射关系
        mp.put(info.taskId, new int[]{info.priority, info.userId});
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
        while (!heap.isEmpty()) {
            Info info = heap.poll();
            if (info.priority == mp.get(info.taskId)[0] && info.userId == mp.get(info.taskId)[1]) {
                rmv(info.taskId);
                return info.userId;
            }
        }
        return -1;
    }

    class Info {
        int taskId;
        int priority;
        int userId;

        Info(int taskId, int priority, int userId) {
            this.taskId = taskId;
            this.priority = priority;
            this.userId = userId;
        }
    }
}
