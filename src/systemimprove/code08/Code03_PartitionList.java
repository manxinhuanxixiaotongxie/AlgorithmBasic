package systemimprove.code08;

import java.util.ArrayList;
import java.util.List;

/**
 * 单向链表按某值划分成左边小、中间相等、右边大的形式
 */
public class Code03_PartitionList {

    public void partitionList(ListNode head) {
        if (head == null) {
            return;
        }

        // 将链表转转换成数组 在数组上进行荷兰国旗问题的拆分
        List<ListNode> listNodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            listNodes.add(cur);
            cur = cur.next;
        }
        int leftIndex = -1;
        int rightIndex = listNodes.size() - 1;
        int index = 0;
        while (index < rightIndex) {
            if (listNodes.get(index).val < head.val) {
                swap(listNodes, index++, ++leftIndex);
            } else if (listNodes.get(index).val > head.val) {
                swap(listNodes, index, rightIndex--);
            } else {
                index++;

            }
        }
        swap(listNodes, rightIndex, listNodes.size() - 1);

    }

    public ListNode partitionList2(ListNode head, int value) {
        if (head == null) {
            return null;
        }
        ListNode lessHead = null;
        ListNode lessTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode moreHead = null;
        ListNode moreTail = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = null;
            // 如果是小于的话
            if (cur.val < value) {
                if (lessHead == null) {
                    lessHead = cur;
                    lessTail = cur;
                } else {
                    lessTail.next = cur;
                    lessTail = cur;
                }
            } else if (cur.val == value) {
                if (equalHead == null) {
                    equalHead = cur;
                    equalTail = cur;
                } else {
                    equalTail.next = cur;
                    equalTail = cur;
                }
            } else {
                if (moreHead == null) {
                    moreHead = cur;
                    moreTail = cur;
                } else {
                    moreTail.next = cur;
                    moreTail = cur;
                }
            }
            cur = next;
        }

        // 将三个链表连接起来
        if (lessTail != null) {
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }

        if (equalTail != null) {
            equalTail.next = moreHead;
        }

        // 返回头节点
        if (lessHead != null) {
            head = lessHead;
        } else if (equalHead != null) {
            head = equalHead;
        } else {
            head = moreHead;
        }
        return head;


    }

    public void swap(List<ListNode> listNodes, int i, int j) {
        ListNode temp = listNodes.get(i);
        listNodes.set(i, listNodes.get(j));
        listNodes.set(j, temp);
    }
}
