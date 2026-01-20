package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

public class Code530 {
    public int getMinimumDifference(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        process(root, list);
        // 中序遍历
        int ans = Integer.MAX_VALUE;
        for (int i = 1;i < list.size();i++) {
            ans = Math.min(ans, Math.abs(list.get(i).val - list.get(i - 1).val));
        }
        return ans;
    }

    public void process(TreeNode root,List<TreeNode> list) {
        if (root == null) return;
        process(root, list);
        list.add(root);
        process(root.right, list);
    }
}
