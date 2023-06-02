package newerclass;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-29 17:08
 */
public class LevelOrderBottom {

    /**
     * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     *
     * 使用广度有限遍历，每次遍历树的每一层，
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> curAns = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode curNode = queue.poll();
                curAns.add(curNode.val);
                if (curNode.left != null) {
                    queue.add(curNode.left);
                }
                if (curNode.right != null) {
                    queue.add(curNode.right);
                }
            }
            ans.add(0, curAns);

        }

        return ans;
    }

}
