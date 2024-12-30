package leetcode.practice;

import leetcode.ListNode;

public class Code0205 {
    /**
     * 两数相加
     * 思路：
     * 直接在两个链表上进行操作，不需要转换成数字再相加
     * 过程：
     * 1. 两个链表同时遍历，相加，如果有进位则加上进位
     * 2. 如果有一个链表遍历完了，另一个链表还有剩余，则继续遍历剩余的链表
     * 3. 如果最后还有进位，则加上进位
     * 4. 返回结果
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        // 无效参数过滤
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        // 0值链表过滤
        if (l1.val == 0 && l1.next == null || l2.val == 0 && l2.next == null) {
            return l1.val == 0 ? l2 : l1;
        }
        ListNode head = null;
        ListNode cur = null;
        ListNode pre = null;
        int carry = 0;

        while (l1 != null && l2 != null) {
            int sum  = l1.val + l2.val + carry;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            cur = new ListNode(sum);
            if (head == null) {
                head = cur;
                pre = cur;
            } else {
                pre.next = cur;
                pre = cur;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + carry;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            cur = new ListNode(sum);
            if (head == null) {
                head = cur;
                pre = cur;
            } else {
                pre.next = cur;
                pre = cur;
            }
            l1 = l1.next;
        }
        while (l2 != null) {
            int sum = l2.val + carry;
            if (sum >= 10) {
                carry = 1;
                sum -= 10;
            } else {
                carry = 0;
            }
            cur = new ListNode(sum);
            if (head == null) {
                head = cur;
                pre = cur;
            } else {
                pre.next = cur;
                pre = cur;
            }
            l2 = l2.next;
        }
        if (carry == 1) {
            cur = new ListNode(1);
            pre.next = cur;
        }
        return head;
    }

    /***
     * 转化成数字之后 相加 再转化成链表
     * 以下代码存在问题
     * 1. 两个链表的长度可能不一样，所以转化成数字之后，可能会超出int的范围
     * 2. 转化成数字之后，再转化成链表，需要遍历两次
     * 3. 代码逻辑复杂
     *  超过了long的范围之后无法处理
     *  	测试用例:[1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]
     * 			[5,6,4]
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        if (l1.val == 0 && l1.next == null || l2.val == 0 && l2.next == null) {
            return l1.val == 0 ? l2 : l1;
        }

        long intL1 = 0;
        int power = 0;
        while (l1 != null) {
            intL1 += l1.val * Math.pow(10, power);
            l1 = l1.next;
            power++;
        }
        long intL2 = 0;
        power = 0;
        while (l2 != null) {
            intL2 += l2.val * Math.pow(10, power);
            l2 = l2.next;
            power++;
        }
        long sum = intL1 + intL2;
        power = 0;
        long temp = sum;
        while (temp > 0) {
            temp /= 10;
            power++;
        }
        ListNode head = null;
        ListNode cur = null;
        for (int i = 0; i < power; i++) {
            int val = (int) (sum / (long) Math.pow(10, i) % 10);
            if (i == 0) {
                head = new ListNode(val);
                cur = head;
            } else {
                cur.next = new ListNode(val);
                cur = cur.next;
            }
        }
        return head;
    }
}
