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
            /**
             * 右树不为空的时候 只要找到右树的最左节点就是后继节点
             */
            return getMostLeftNode(cur.right);

        }else {
            /**
             * 右树为空
             * 向father方向查找
             *
             */

            Node parent = cur.parent;

            while (parent != null&& parent.left != cur ) {
                cur = cur.parent;
                parent = cur.parent;
            }
            return parent;

        }
    }

    public static Node getMostLeftNode(Node node) {
        if (node.left == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }



    public static Node getPreNode(Node cur) {
        if (cur == null) {
            return null;
        }

        if (cur.left != null) {
            /**
             * 左子树不空   左子树的最右侧节点就是前驱节点
             */
            while (cur.right != null) {
                cur = cur.right;
            }
            return cur;
        } else {
            Node parent = cur.parent;
            while (parent != null && parent.right != cur) {
                cur = cur.parent;
                parent = cur.parent;
            }
            return parent;
        }
    }
}
