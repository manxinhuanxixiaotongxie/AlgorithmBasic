package systemimprove.code09;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 用先序方式序列化二叉树
 * 用先序方式反序列化二叉树
 */
public class Code03_SeriPre {

    public String preSeria(TreeNode root) {
        if (root == null) {
            return null;
        }
        return process(root);

    }

    public String process(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        String res = root.val + "!";
        res += process(root.left);
        res += process(root.right);
        return res;
    }

    public TreeNode preDeseria(Deque<String> stringDeque) {
        if (stringDeque == null) {
            return null;
        }
        return process1(stringDeque);
    }

    public TreeNode process1(Deque<String> queue) {
        String value = queue.pollFirst();
        if (value.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = process1(queue);
        root.right = process1(queue);
        return root;
    }

    public String levelSeria(TreeNode root) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        String res = root.val + "!";
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.pollFirst();
            if (treeNode.left != null) {
                res += treeNode.left.val + "!";
                queue.addLast(treeNode.left);
            } else {
                res += "#!";
            }
            if (treeNode.right != null) {
                res += treeNode.right.val + "!";
                queue.addLast(treeNode.right);
            } else {
                res += "#!";
            }
        }
        return res;
    }

    public TreeNode levelDeseria(String str) {
        if (str == null) {
            return null;
        }
        String[] values = str.split("!");
        int index = 0;
        TreeNode root = generateNode(values[index++]);
        Deque<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.addLast(root);
        }
        TreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.pollFirst();
            node.left = generateNode(values[index++]);
            node.right = generateNode(values[index++]);
            if (node.left != null) {
                queue.addLast(node.left);
            }
            if (node.right != null) {
                queue.addLast(node.right);
            }
        }
        return root;
    }

    public TreeNode generateNode(String value) {
        if (value.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.parseInt(value));
    }
}
