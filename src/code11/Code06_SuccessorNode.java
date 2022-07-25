package code11;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-25 19:22
 */

/**
 * 给你二叉树中某个节点，返回该节点的后继节点
 * 中序遍历的下一个节点
 */
public class Code06_SuccessorNode {

    /**
     * 第一种实现方式：中序遍历整个树
     * 找出节点的后继节点
     */
    /**
     * 第二种实现方式：
     * 找到规律
     *
     */

    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node getNextNode(Node cur) {

        if (cur == null) {
            return null;
        }

        if (cur.right!= null) {
            // 右树不为空

        }else {

        }


        return new Node(1);
    }
}
