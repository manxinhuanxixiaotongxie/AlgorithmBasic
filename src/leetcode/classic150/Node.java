package leetcode.classic150;

public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    /**
     * 随机链表
     */
    public Node random;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int val, Node left, Node right, Node next,Node random) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
        this.random = random;
    }
}
