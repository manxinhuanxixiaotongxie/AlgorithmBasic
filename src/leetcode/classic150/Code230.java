package leetcode.classic150;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Code230 {
    public int kthSmallest(TreeNode root, int k) {
        List<TreeNode>  list = new ArrayList<>();
        process(root,list);
        Collections.sort(list, new Comparator<TreeNode>() {
            @Override
            public int compare(TreeNode o1, TreeNode o2) {
                return o1.val - o2.val;
            }
        });
        return list.get(k-1).val;
    }

    public void process(TreeNode root, List<TreeNode> list) {
        if (root == null) return;
        process(root.left, list);
        list.add(root);
        process(root.right, list);
    }
}
