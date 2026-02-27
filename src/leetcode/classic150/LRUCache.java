package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int limit;
    private Map<Integer, ListNode> map;
    private ListNode head;
    private ListNode tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.limit = capacity;
        head = tail = null;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        ListNode node = map.get(key);
        if (node == head) {
            return node.val;
        } else {
            // 先断开node
            if (node == tail) {
                tail = tail.pre;
                if (tail != null) tail.next = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            // 插入到head前面
            node.pre = null;
            node.next = head;
            head.pre = node;
            head = node;
            return node.val;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            if (node == head) {
                return;
            } else {
                // 先断开node
                if (node == tail) {
                    tail = tail.pre;
                    if (tail != null) tail.next = null;
                } else {
                    node.pre.next = node.next;
                    node.next.pre = node.pre;
                }
                // 插入到head前面
                node.pre = null;
                node.next = head;
                head.pre = node;
                head = node;
            }
        } else {
            ListNode listNode = new ListNode(key, value);
            map.put(key, listNode);
            if (head == null) {
                head = tail = listNode;
                return;
            }
            // 插入到head前面
            listNode.pre = null;
            listNode.next = head;
            head.pre = listNode;
            head = listNode;
            if (map.size() > limit) {
                // 淘汰tail节点
                map.remove(tail.next == null ? tail.key : tail.next.key);
                if (tail == head) {
                    head = tail = null;
                } else {
                    tail = tail.pre;
                    if (tail != null) tail.next = null;
                }
            }
        }
    }

    private static class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static void main() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 3 作废
        System.out.println(cache.get(3));       // 返回 -1 (未找到)
        System.out.println(cache.get(4));       // 返回  4
    }
}
