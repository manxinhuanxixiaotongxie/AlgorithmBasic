package leetcode.classic150;

import java.util.*;

/**
 * 实现RandomizedSet 类：
 *
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 */
public class RandomizedSet {

    // 基础数据
    List<Integer> list;
    // 辅助结构
    Map<Integer,Integer> indexMap;
    Random random;

    public RandomizedSet() {
        list = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }
        int index = list.size();
        list.add(val);
        indexMap.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if (indexMap.containsKey(val)) {
            int index = indexMap.get(val);
            int last = list.get(list.size() - 1);
            list.set(index, last);
            indexMap.put(last, index);
            list.remove(list.size() - 1);
            indexMap.remove(val);
            return true;
        }
        return false;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));

    }
}