package book_03binarytree;

import com.sun.jmx.snmp.SnmpNull;

import java.util.List;
import java.util.Stack;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-20 18:27
 */
public class ErgodicTree {
    /**
     * 采用递归非递归方式实现二叉树先序、中序、后序遍历
     */
    /**
     * 递归:
     * 1.先序遍历：根左右
     * 2.中序遍历：左根右
     * 3.后序遍历左右根
     */
    public void preErgodic(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.value);
        preErgodic(treeNode.left);
        preErgodic(treeNode.right);
    }

    public void midErgodic(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        midErgodic(treeNode.left);
        System.out.println(treeNode.value);
        midErgodic(treeNode.right);
    }

    public void posErgodic(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        posErgodic(treeNode.left);
        posErgodic(treeNode.right);
        System.out.println(treeNode.value);
    }

    /**
     * 非递归
     */
    public void preErgodic2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        while (!stack.isEmpty()) {
            treeNode = stack.pop();
            System.out.println(treeNode.value);
            if (treeNode.right != null) {
                stack.add(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }

    /**
     * 非递归中序遍历
     * 左中右
     *
     * @param treeNode
     */
    public void midErgodic2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 中序遍历
        while (!stack.isEmpty() || treeNode != null) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop();
                System.out.println(treeNode.value);
                treeNode = treeNode.right;
            }
        }

    }

    /**
     * 非递归后序遍历
     * 左右中
     *
     * @param treeNode
     */
    public void posErgodic2(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        stack.push(treeNode);

        TreeNode c = null;
        while (!stack.isEmpty() || treeNode != null) {

            c = stack.peek();

            if (c.right != null) {
                stack.push(c.right);
            } else if (c.left != null) {
                stack.push(c.left);
            } else {
                System.out.println(stack.pop().value);
            }


        }

    }


}
