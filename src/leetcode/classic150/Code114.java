package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

public class Code114 {
    public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        process(root, list);
        TreeNode help = new TreeNode(-1);
        for (TreeNode node : list) {
            help.right = node;
            help.left = null;
            help = help.right;
        }
    }

    public void process(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        process(root.left, list);
        process(root.right, list);
    }

}
