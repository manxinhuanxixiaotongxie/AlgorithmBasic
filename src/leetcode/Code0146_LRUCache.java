package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
public class Code0146_LRUCache {

    /**
     * LRU算法 最少使用算法
     * <p>
     * <p>
     * 双向链表+哈希表
     *
     * @param capacity
     */

    private Map<Integer, Node> map = new HashMap<>();
    private int capacity;
    private int size = 0;
    private Node head = null;
    private Node tail = null;

    public Code0146_LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /**
     * 双向链表   头节点是最新的信息  尾节点是最旧的信息
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        }
        // 说明缓存中已经存在这个key
        Node cur = map.get(key);
        if (head == tail || cur == head) {
            return cur.value;
        } else if (cur == tail) {
            // 如果是尾巴节点的话
            Node next = tail.next;
            tail.next = null;
            next.pre = null;
            tail = next;
            cur.next = null;
            cur.pre = head;
            head.next = cur;
            head = cur;
        } else {
            // 在中间位置
            Node pre = cur.pre;
            Node next = cur.next;
            next.pre = pre;
            pre.next = next;
            cur.pre = head;
            cur.next = null;
            head.next = cur;
            head = cur;
        }
        return cur.value;
    }

    public void put(int key, int value) {
        if (map.get(key) == null) {
            // 为空说明原来的节点是没有的
            // 分情况讨论
            Node cur = new Node(key, value);
            if (capacity == size) {
                // 链表已经来到了容量
                // 说明有节点需要被淘汰
                if (head == tail) {
                    // 说明只有一个节点
                    // 直接删除
                    map.remove(tail.key);
                    head = cur;
                    tail = cur;
                } else {
                    // 删除尾节点
                    Node next = tail.next;
                    next.pre = null;
                    tail.next = null;
                    map.remove(tail.key);
                    tail = next;
                    head.next = cur;
                    cur.pre = head;
                    cur.next = null;
                    head = cur;
                }
            } else {
                // 容量没到
                // 没有淘汰策略
                if (head == null) {
                    head = cur;
                    tail = cur;
                } else {
                    head.next = cur;
                    cur.pre = head;
                    head = cur;
                }
                size++;

            }
            map.put(key, cur);
        } else {
            // 说明原来已经有了这个节点的信息
            // 只需要做更新操作
            // 如果这个节点已经是头节点  实际上是不需要处理的
            Node cur = map.get(key);
            cur.value = value;
            if (head == tail || cur == head) {
                return;
            } else if (cur == tail) {
                Node next = tail.next;
                tail.next = null;
                next.pre = null;
                tail = next;
                head.next = cur;
                cur.pre = head;
                head = cur;
            } else {
                // 普遍位置
                Node pre = cur.pre;
                Node next = cur.next;
                pre.next = next;
                next.pre = pre;
                cur.pre = head;
                head.next = cur;
                cur.next = null;
                head = cur;
            }
        }
    }


    class Node {
        // 双向链表的节点
        Node pre;
        Node next;
        int key;
        int value;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        /**
         * ["LRUCache","put","put","put","get","put","put","get","put","put","get","put","get","get","get","put","put","get","put","get"]
         * [[10],[7,28],[7,1],[8,15],[6],[10,27],[8,10],[8],[6,29],[1,9],[6],[10,7],[1],[2],[13],[8,30],[1,5],[1],[13,2],[12]]
         */
        Code0146_LRUCache lruCache = new Code0146_LRUCache(10);
        lruCache.put(7, 28);
        lruCache.put(7, 1);
        lruCache.put(8, 15);
        System.out.println(lruCache.get(6));
        lruCache.put(10, 27);
        lruCache.put(8, 10);
        System.out.println(lruCache.get(8));
        lruCache.put(6, 29);
        lruCache.put(1, 9);
        System.out.println(lruCache.get(6));
        lruCache.put(10, 7);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));
        System.out.println(lruCache.get(13));
        lruCache.put(8, 30);
        lruCache.put(1, 5);
        System.out.println(lruCache.get(1));
        lruCache.put(13, 2);
        lruCache.get(12);
    }
}
