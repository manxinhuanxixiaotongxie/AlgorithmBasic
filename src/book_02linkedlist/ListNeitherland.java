package book_02linkedlist;

/**
 * @Description 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * @Author Scurry
 * @Date 2023-04-10 17:24
 */
public class ListNeitherland {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(1);
    }


    /**
     * 将单项链表划分成左边小、右边大
     *
     * @param head
     * @return
     */
    public static ListNode listNeightland(ListNode head, int value) {

        if (head == null) {
            return head;
        }

        ListNode cur = head;
        int length = 0;
        while (cur.next != null) {
            length++;
        }
        ListNode[] array = new ListNode[length];

        /**
         * 思路：
         * 1.为后序的快速排序奠定基础
         * 2。划分左区
         * 3.当index的值比给定的值小的话左半区加1
         * 4.给定值比index的值要小，index++
         * 5.结束之后就是左边小，右边大
         */
        int left = -1;
        int index = 0;
        while (index < length) {
            if (array[index].val < value) {
                swap(array, ++left, index++);

            }
            if (array[index].val >= value) {
                index++;
            }


        }
        return head;
    }

    /**
     * 将单项链表划分成左边小、中间相等、右边大的形式
     * 时间复杂度o(N) 空间复杂度o(N)
     *
     * @param head
     * @return
     */
    public static ListNode listNeightlandUp(ListNode head, int value) {

        if (head == null) {
            return head;
        }

        ListNode cur = head;
        int length = 0;
        while (cur.next != null) {
            length++;
        }
        ListNode[] array = new ListNode[length];
        cur = head;

        int left = -1;
        int right = length;
        length = 0;
        while (cur.next != null) {
            array[length++] = cur;
            cur = cur.next;
        }

        /**
         * 思路：
         * 1.left从数组最左边开始
         * 2.right从数组最右边开始
         * 3.当前值比给定值小 交换左边区的最近的数与当前值，并且index+1
         * 4.当前值比给定值要大，交换right最近的值，index不变，并且right向左移动
         * 5.如果相等交换index+1
         */
        int index = 0;
        while (index < length) {
            if (array[left].val < value) {
                swap(array, ++left, index++);
            }
            if (array[left].val > value) {
                swap(array, index, --right);
            }
            if (array[left].val == value) {
                index++;
            }

        }

        for (int i = 1; i < array.length; i++) {
            array[i - 1].next = array[i];
        }

        return array[0];
    }

    /**
     * 将单项链表划分成左边小、中间相等、右边大的形式
     * 实现：时间复杂度O(N),空间复杂度N（1）
     * =
     *
     * @param head
     * @return
     */
    public static ListNode listNeightlandUpImprove(ListNode head, int value) {

        if (head == null) {
            return head;
        }

        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode biggerHead = null;
        ListNode biggerTail = null;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val < value) {
                if (smallHead == null) {
                    smallHead = cur;
                } else {
                    smallTail.next = cur;
                    smallTail = cur;
                }
            }

            if (cur.val == value) {
                if (equalHead == null) {
                    equalHead = cur;
                    equalTail = cur;
                } else {
                    equalTail.next = cur;
                    equalTail = cur;
                }
            }

            if (cur.val > value) {
                if (biggerHead == null) {
                    biggerHead = cur;
                    biggerTail = cur;
                } else {
                    biggerTail.next = cur;
                    biggerTail = cur;
                }
            }
            cur = cur.next;
        }

        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;
        }

        if (equalTail != null) {
            equalTail.next = biggerHead;
        }

        return smallHead != null ? smallHead : equalHead != null ? equalHead : biggerHead;
    }


    /**
     * 将单项链表划分成左边小、中间相等、右边大的形式
     * 实现一种方式时间复杂度O(N),空间复杂度N（1）
     * 时间复杂度o(N) 空间复杂度o(1)
     * <p>
     * 亲测有效
     *
     * @param head
     * @return
     */
    public static ListNode listNeightlandUpImprove2(ListNode head, int value) {

        if (head == null) {
            return head;
        }

        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode biggerHead = null;
        ListNode biggerTail = null;
        ListNode next = null;

        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < value) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            }
            if (head.val == value) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                }
            }

            if (head.val > value) {
                if (biggerHead == null) {
                    biggerHead = head;
                    biggerTail = head;
                } else {
                    biggerTail.next = head;
                }
            }
            head = next;
        }

        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalHead == null ? smallTail : equalTail;
        }

        if (equalTail != null) {
            equalTail.next = biggerHead;
        }

        return smallHead != null ? smallHead : equalHead != null ? equalHead : biggerHead;
    }

    public static void swap(ListNode[] array, int left, int right) {

        ListNode leftNode = array[left];
        array[left] = array[right];
        array[right] = leftNode;

    }
}
