package leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

public class Code0230 {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        process(root, list);
        return list.get(k - 1);
    }

    private void process(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        process(root.left, list);
        list.add(root.val);
        process(root.right, list);

    }
}
