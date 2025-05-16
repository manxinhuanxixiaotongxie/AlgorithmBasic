package systemimprove.code09;

/**
 * 二叉树节点
 */
public class TreeNode {
    public TreeNode left;
    public TreeNode right;
    /**
     * 祖先节点
     */
    public TreeNode parent;
    public int val;

    TreeNode(int val) {
        this.val = val;
    }

}
