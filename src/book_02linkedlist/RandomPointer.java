package book_02linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-14 10:40
 */
public class RandomPointer {
    /**
     * 复制含有随机指针节点的链表
     * 给定一个由node节点类型组成的单链表头结点head
     * 实现一个函数完成这个链表所有结构的复制，返回复制新链表的头结点
     *
     *
     * 进阶：不适用额外的数据结构 只用有限的几个变量，在时间复杂度O(n)完成原问题要实现的函数
     */

    /**
     * 进阶解法
     *
     * @param randomNode
     * @return
     */
    public RandomNode copyRandomNodeList(RandomNode randomNode) {

        if (randomNode == null) {
            return randomNode;
        }
        // 原样复制原来的新链表
        RandomNode randomNodeNew;
        RandomNode next;
        RandomNode head = randomNode;
        while (randomNode.next != null) {
            next = randomNode.next;
            randomNode.next = new RandomNode(randomNode.value);
            randomNode.next.next = next;
            randomNode = next;

        }
        // 还原链表,设置random指针
        RandomNode randomNodeCopy = null;
        RandomNode cur = head;
        while (cur.next != null) {
            next = cur.next.next;
            randomNodeCopy = cur.next;
            randomNodeCopy.random = cur.random != null ? cur.random : null;
            cur = next;
        }

        // 拆除新复制的链表
        cur = head;
        while (cur.next != null) {
            next = cur.next.next;
            randomNodeCopy = cur.next;
            cur.next = next;
            randomNodeCopy.next = next != null ? next.next : null;
            cur = next;
        }


        return head.next;
    }

    /**
     * 普通解法
     *
     * @param randomNode
     * @return
     */
    public RandomNode copyRandomNodeListNormal(RandomNode randomNode) {

        if (randomNode == null) {
            return randomNode;
        }

        Map<RandomNode, RandomNode> map = new HashMap<>();
        RandomNode cur = randomNode;
        while (cur.next != null) {
            map.put(cur, new RandomNode(cur.value));
        }
        cur = randomNode;
        while (cur.next != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }


        return map.get(randomNode);
    }

}
