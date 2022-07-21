/**
 * @desc:
 * @author: Scurry
 * @date: 2022/7/21 23:53
 */

package code10;

/**
 * @desc:
 * @author: Scurry
 * @date: 2022/7/21 23:53
 */

import class03.Code01_ReverseList;
import code09.Code03_SmallerEuqalBigger;

/**
 * 二叉树的先序、中序、后序遍历
 * 先序：任何子数的处理顺序都是，先头节点、再左子树，然后右子树
 * 中序：任何子数的处理顺序都是，先左子树、再头节点，然后右子树
 * 后序：任何子数的处理顺序都是，先左子树、再右子树，然后头节点
 * <p>
 * 递归方式实现二叉树的先序、中序、后序遍历
 * 非递归方式实现二叉树的先序、中序、后序遍历：栈
 */
public class Code02_RecursiveTraversalBT {

    /**
     * 二叉树节点的定义
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static void pre(Node head){

        if (head == null) {
            return;
        }

        System.out.println(head.value);

        pre(head.left);
        pre(head.right);

    }

    public static void in(Node head){

        if (head == null) {
            return;
        }


        pre(head.left);

        System.out.println(head.value);

        pre(head.right);

    }

    public static void pos(Node head){

        if (head == null) {
            return;
        }


        pre(head.left);

        pre(head.right);

        System.out.println(head.value);


    }
}
