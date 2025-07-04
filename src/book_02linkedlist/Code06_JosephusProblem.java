package book_02linkedlist;

/**
 * 环形单链表的约瑟夫环问题
 *
 * 据说著名犹太历史学家Josephus有过以下故事:在罗马人占领乔塔帕特后，39个犹太人与Josephus 及他的朋友躲到一个洞中，39 个犹太人决定宁愿死也不要被敌人抓到，于是决定了一种自杀方式，
 * 41个人排成一个圆圈，由第1个人开始报数，报数到3的人就自杀，然后再由下一个人重新报1，报数到3的人再自杀，这样依次下去，直到剩下最后一个人时，那个人可以自由选择自己的命运。这就是著名的约瑟夫问题。现在请用单向环形链表描述该结构并呈现整个自杀过程。
 * 输入:一个环形单向链表的头节点head 和报数的值m。返回:最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删掉。
 * 进阶问题:如果链表节点数为N，想在时间复杂度为(N)时完成原问题的要求，该怎么实现?
 *
 * 本题测试链接 : https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *
 */
public class Code06_JosephusProblem {

    // 提交直接通过
    // 给定的编号是0~n-1的情况下，数到m就杀
    // 返回谁会活？
    public int lastRemaining1(int n, int m) {
        return getLive(n, m) - 1;
    }

    // 课上题目的设定是，给定的编号是1~n的情况下，数到m就杀
    // 返回谁会活？
    public static int getLive(int n, int m) {
        if (n == 1) {
            return 1;
        }
        return (getLive(n - 1, m) + m - 1) % n + 1;
    }

    // 提交直接通过
    // 给定的编号是0~n-1的情况下，数到m就杀
    // 返回谁会活？
    // 这个版本是迭代版
    public int lastRemaining2(int n, int m) {
        int ans = 1;
        int r = 1;
        while (r <= n) {
            ans = (ans + m - 1) % (r++) + 1;
        }
        return ans - 1;
    }

    // 以下的code针对单链表，不要提交
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node josephusKill1(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    public static Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int size = 1; // tmp -> list size
        while (cur != head) {
            size++;
            cur = cur.next;
        }
        int live = getLive(size, m); // tmp -> service node position
        while (--live != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }

    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1;
        printCircularList(head1);
        head1 = josephusKill1(head1, 3);
        printCircularList(head1);

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = head2;
        printCircularList(head2);
        head2 = josephusKill2(head2, 3);
        printCircularList(head2);

    }
}
