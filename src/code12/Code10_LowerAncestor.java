package code12;

import newerclass.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-28 19:10
 */
public class Code10_LowerAncestor {
    /**
     * 最低公共祖先
     */

    /**
     * 非递归实现
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        HashMap<TreeNode, TreeNode> fatherMap = new HashMap<>();
        HashSet<TreeNode> nodeSet = new HashSet<>();

        fatherMap.put(root, null);
        fillFatherMap(root, fatherMap);

        TreeNode cur = p;
        while (!nodeSet.contains(cur) && cur != null) {
            nodeSet.add(cur);
            cur = fatherMap.get(cur);
        }
        cur = q;
        while (!nodeSet.contains(cur)) {
            cur = fatherMap.get(cur);
        }
        return cur;

    }

    public void fillFatherMap(TreeNode treeNode, Map<TreeNode, TreeNode> fatherMap) {
        if (treeNode.left != null) {
            fatherMap.put(treeNode.left, treeNode);
            fillFatherMap(treeNode.left, fatherMap);
        }
        if (treeNode.right != null) {
            fatherMap.put(treeNode.right, treeNode);
            fillFatherMap(treeNode.right, fatherMap);
        }
    }


    /**
     * 递归实现
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        return process(root, p, q).ans;

    }

    public Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);

        boolean findP = leftInfo.findP || rightInfo.findP || p == root;
        boolean findQ = leftInfo.findQ || rightInfo.findQ || q == root;

        /**
         *
         * 与X无关
         * 在左树或者右树上找到了答案
         * 与X有关
         * 左树找到一个 右树找到一个 答案就是X
         *
         */
        TreeNode ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findP && findQ) {
                ans = root;
            }
        }

        return new Info(findP, findQ, ans);

    }

    /**
     * 分类
     * （1）与X无关
     * 在左树或者右树找到了p q
     * <p>
     * （2）与X有关
     * 在左树找到了p或者Q 在右树找到了P或者Q
     * 或者P Q与X就是一个节点
     */
    public class Info {
        boolean findP;
        boolean findQ;
        TreeNode ans;

        Info(boolean findP, boolean findQ, TreeNode ans) {
            this.findP = findP;
            this.findQ = findQ;
            this.ans = ans;
        }
    }
}
