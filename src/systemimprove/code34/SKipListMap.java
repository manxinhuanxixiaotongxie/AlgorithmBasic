package systemimprove.code34;

import java.util.ArrayList;
import java.util.List;

public class SKipListMap<K extends Comparable<K>, V> {

    private SkipNode<K,V> head;
    private static final double PROBABILITY = 0.5;
    private int maxLevel;

    SKipListMap(SkipNode<K,V> head) {
        head = new SkipNode<>(null, null);
        head.next.add(null);
    }

    /**
     * 添加节点
     * @param key
     */
    public void add(K key) {
        // 在整棵树上找到小于key的最大节点
        SkipNode<K,V> mostRightLessNodeInTree = findMostRightLessNodeInTree(key);
        SkipNode<K,V> skipNode = mostRightLessNodeInTree.next.get(0);
        if (mostRightLessNodeInTree != null && skipNode.key.compareTo(key) == 0) {
            // 更新
        } else {
            int newNodeLevel = 0;
            // 以0.5的概率绝对当前节点的层数
            while (Math.random() > PROBABILITY) {
                newNodeLevel++;
            }
            // 对根节点进行处理
            while (maxLevel < newNodeLevel) {
                head.next.add(null);
                maxLevel++;
            }

            int level = maxLevel;
            SkipNode<K,V> newNode = new SkipNode<>(key, null);
            while (level >= 0) {
                newNode.next.add(null);
                level--;
            }
            SkipNode<K,V> pre = head;
            level = maxLevel;
            while (level >= 0) {
                // 逐层挂节点 在每层上找到小于当前key的最大节点
                pre = findMostRightNodeInLevel(pre, level, key);
                // 有可能出现当前层出现的层数是小于根节点的层数
                // 只挂新节点对应的层级 逐层进行挂
                if (level > newNodeLevel) {
                    newNode.next.set(level, pre.next.get(level));
                    pre.next.set(level, newNode);
                }
                level--;
            }
        }

    }

    /**
     * 删除节点
     *
     * @param key
     */
    public void delete(K key) {
        if (contains(key)) {
            int level = maxLevel;
            SkipNode<K,V> pre = head;
            while (level >= 0) {
                // 逐层查找 找到小于key的最大节点值
                pre = findMostRightNodeInLevel(pre, level, key);
                SkipNode<K,V> next = (SkipNode<K,V>) pre.next.get(level);
                if (next != null && next.key.compareTo(key) == 0) {
                    pre.next.set(level, next.next.get(level));
                }
                // 降层
                if (level != 0 && pre == head && pre.next.get(level) == null) {
                    head.next.remove(level);
                    maxLevel--;
                }
                level--;
            }
        }

    }

    private boolean contains(K key) {
        SkipNode<K,V> mostRightLessNodeInTree = findMostRightLessNodeInTree(key);
        SkipNode<K,V> next = null;
        if (mostRightLessNodeInTree != null) {
            next = (SkipNode<K,V>) mostRightLessNodeInTree.next.get(0);
        }
        return mostRightLessNodeInTree != null && next.key.compareTo(key) == 0;
    }


    /**
     * 在整颗数树上查找比key小的最大节点
     *
     * @param key
     * @return
     */
    private SkipNode<K,V> findMostRightLessNodeInTree(K key) {
        if (key == null) {
            return null;
        }
        // 逐层查找
        int level = maxLevel;
        SkipNode<K,V> ans = head;
        // 当前实现层数从0层开始 每个节点默认都有第0层
        // maxLevel=7实际上的层级是0-7
        while (level >= 0) {
            // 在当前层查找比key小的最大节点
            ans = findMostRightNodeInLevel(ans, level, key);
            level--;
        }
        return ans;
    }

    /**
     * 在当前层查找
     *
     * @param cur
     * @param level
     * @param key
     * @return
     */
    private SkipNode<K,V> findMostRightNodeInLevel(SkipNode<K,V> cur, int level, K key) {
        if (cur == null) {
            return null;
        }
        SkipNode<K,V> ans = cur;
        // 在当前层查找当前层比key小的数
        SkipNode<K,V> next = (SkipNode<K,V>) cur.next.get(level);
        while (next != null && next.key.compareTo(key) < 0) {
            ans = next;
            next = (SkipNode<K,V>) next.next.get(level);
        }

        return ans;
    }


}

class SkipNode<K extends Comparable<K>, V> {
    K key;
    V value;
    List<SkipNode<K,V>> next;

    SkipNode(K key, V value) {
        this.key = key;
        this.value = value;
        next = new ArrayList<>();
        next.add(null);
    }
}
