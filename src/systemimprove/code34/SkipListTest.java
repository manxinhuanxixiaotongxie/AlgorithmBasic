package systemimprove.code34;

import java.util.ArrayList;
import java.util.List;

public class SkipListTest {


}

class SKipList<K extends Comparable<K>, V> {

    private SkipNode head;
    private static final double PROBABILITY = 0.5;
    private int maxLevel;


    public void add(K key) {

        SkipNode mostRightLessNodeInTree = findMostRightLessNodeInTree(key);
        SkipNode skipNode = (SkipNode) mostRightLessNodeInTree.next.get(0);
        if (mostRightLessNodeInTree != null && skipNode.key.compareTo(key) == 0) {
            // 更新
        } else {
            int newNodeLevel = 0;
            // 随机生层
            while (Math.random() > PROBABILITY) {
                newNodeLevel++;
            }
            while (maxLevel < newNodeLevel) {
                head.next.add(null);
                maxLevel++;
            }
            int level = maxLevel;
            SkipNode newNode = new SkipNode(key, null);
            while (level >= 0) {
                newNode.next.add(null);
                level--;
            }
            SkipNode pre = head;
            level = maxLevel;
            while (level >= 0) {
                pre = findMostRightNodeInLevel(pre, level, key);
                if (level > newNodeLevel) {
                    newNode.next.set(level, pre.next.get(level));
                    pre.next.set(level, newNode);
                }
                level--;
            }
        }

    }


    private SkipNode findMostRightLessNodeInTree(K key) {
        if (key == null) {
            return null;
        }
        int level = maxLevel;
        SkipNode ans = head;
        while (level >= 0) {
            ans = findMostRightNodeInLevel(ans, level, key);
            level--;
        }
        return ans;
    }

    private SkipNode findMostRightNodeInLevel(SkipNode cur, int level, K key) {
        if (cur == null) {
            return null;
        }
        SkipNode ans = cur;
        // 在当前层查找当前层比key小的数
        SkipNode next = (SkipNode) cur.next.get(level);
        while (next != null && next.key.compareTo(key) < 0) {
            ans = next;
            next = (SkipNode) next.next.get(level);
        }

        return ans;
    }


}

class SkipNode<K extends Comparable<K>, V> {
    K key;
    V value;
    List<SkipNode> next;

    SkipNode(K key, V value) {
        this.key = key;
        this.value = value;
        next = new ArrayList<>();
    }
}
