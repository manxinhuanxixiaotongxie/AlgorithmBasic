package systemimprove.code31;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳表
 *
 * 新生代的数据结构
 * 每一个节点进入跳表 拥有的层数都是随机的 按照0.5的概率去随机造层
 * 过程如下：
 * 跳表初始化一个无限小的节点 比系统最小值改小
 * 该节点拥有的层数 是整个链表最大的层数
 * 从该节点出发 能在每一层找到链表的节点
 * 第一层一定拥有链表的全部节点 并且最低层的跳表是一个有序链表
 */
public class Code03_SkipList {

    public static class SkipNode<k extends Comparable<k>,v> {
        public k key;
        public v value;
        List<SkipNode> nextNodes;

        SkipNode(k key ,v value) {
            this.key = key;
            this.value = value;
            nextNodes = new ArrayList<>();
            // 默认拥有的第0层 那么后序所有的节点加入进来都是从第一层开始
            nextNodes.add(null);
        }

        public boolean isKeyEqual(k otherKey) {
            return (key == null && otherKey == null)
                    || (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }


    }

    /**
     * 具体的跳表的实现
     * @param <k>
     * @param <v>
     */
    public static class SkipList<k extends Comparable<k>,v> {

        private final static double PROBABILITY = 0.5;

        public SkipNode<k,v> root;
        public int size;
        // 最高层
        public int maxLevel;

        SkipList() {
            root = new SkipNode<>(null, null);
            root.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        public SkipNode add(k key, v value) {
            if (key == null) {
                return null;
            }
            // 这两行代码只做一件事 那就是在最底层  找到这个数是不是在跳表中已经存在
            // 跳表的特性 最底层 也就是第一层拥有全部数量的节点 所有的节点都可以在跳表上面找到
            // 如果第一层最右边小于的key的值存在的话 那么这个最右侧的节点的值如果跟给定的相等  那么说明之前加入过 只需要进行值的更新即可
            SkipNode<k,v> less = findMostRightLessNode(key);
            SkipNode next = less.nextNodes.get(0);
            if (next != null && next.key.compareTo(key) == 0) {
                next.value = value;
            } else {
                size++;
                SkipNode<k,v> newNode = new SkipNode<>(key, value);
                int newNodeLevel = 0;
                // 随机row出一个层高 以0.5的概率去处理
                while (Math.random() < PROBABILITY) {
                    newNodeLevel++;
                }
                while (newNodeLevel > maxLevel) {
                    root.nextNodes.add(null);
                    maxLevel++;
                }

                while (newNode.nextNodes.size() < newNodeLevel + 1) {
                    newNode.nextNodes.add(null);
                }

                int level = maxLevel;
                SkipNode<k,v> pre = root;
                while (level >= 0) {
                    // 在当前层进行处理 从root出发
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    // 为什么要判断level <= newNodeLevel？
                    // 因为可能出现某一个节点row出来的层数比较高 但是在某一层的时候已经找到了比他小的节点
                    if (level <= newNodeLevel) {
                        // 找到比cur小的节点之后  需要将cur挂入到当前层的链表中
                        // 新节点的当前层的下一个点指向pre的下一个点
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        // pre的下一个点指向新节点
                        pre.nextNodes.set(level, newNode);
                    }
                    level--;
                }
            }
            return null;

        }

        public void delete(k key) {
            if (contanis(key)) {
                size--;
                int level = maxLevel;
                SkipNode<k,v> pre = root;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipNode<k,v> next = pre.nextNodes.get(level);
                    if (next != null && next.key.compareTo(key) == 0) {
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
                    // 当前层已经没有节点的时候 需要削减层数
                    if (level != 0 && pre == root && pre.nextNodes.get(level) == null) {
                        root.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }
        }

        public boolean contanis(k key) {
            SkipNode<k, v> mostRightLessNode = findMostRightLessNode(key);
            SkipNode next = mostRightLessNode.nextNodes.get(0);
            return next != null && next.isKeyEqual(key);
        }


        // 从最高层开始找 一直找到最低层
        private SkipNode<k,v> findMostRightLessNode(k key) {
            if (key == null) {
                return null;
            }
            SkipNode<k,v> cur = root;
            int level = maxLevel;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(key, cur, level);
                level--;
            }
            return cur;
        }

        /**
         * 当前层找到比key小的最右节点、
         *
         * @param key
         * @param cur
         * @param level
         * @return
         */
        private SkipNode<k,v> mostRightLessNodeInLevel(k key, SkipNode<k,v> cur, int level) {
            SkipNode<k,v> next = cur.nextNodes.get(level);
            while (next != null && next.key.compareTo(key) < 0) {
                cur = next;
                next = cur.nextNodes.get(level);
            }
            return cur;
        }
    }

    public static void printAll(SkipList<String, String> obj) {
        for (int i = obj.maxLevel; i >= 0; i--) {
            System.out.print("Level " + i + " : ");
            SkipNode<String, String> cur = obj.root;
            while (cur.nextNodes.get(i) != null) {
                SkipNode<String, String> next = cur.nextNodes.get(i);
                System.out.print("(" + next.key + " , " + next.value + ") ");
                cur = next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SkipList<String, String> test = new SkipList<>();
        printAll(test);
        System.out.println("======================");
        test.add("A", "10");
        printAll(test);
        System.out.println("======================");
        test.delete("A");
        printAll(test);
        System.out.println("======================");
        test.add("E", "E");
        test.add("B", "B");
        test.add("A", "A");
        test.add("F", "F");
        test.add("C", "C");
        test.add("D", "D");
        printAll(test);
        System.out.println("======================");
        System.out.println(test.contanis("B"));
        System.out.println(test.contanis("Z"));
//        System.out.println(test.firstKey());
//        System.out.println(test.lastKey());
//        System.out.println(test.floorKey("D"));
//        System.out.println(test.ceilingKey("D"));
        System.out.println("======================");
        test.delete("D");
        printAll(test);
        System.out.println("======================");
//        System.out.println(test.floorKey("D"));
//        System.out.println(test.ceilingKey("D"));


    }
}
