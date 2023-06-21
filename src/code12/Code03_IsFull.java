package code12;

import newerclass.TreeNode;

/**
 * @Description
 * 判断一棵树是不是满二叉树
 * 二叉树的特性：
 * 节点的数量是2……height-1
 * @Author Scurry
 * @Date 2023-06-20 19:06
 */
public class Code03_IsFull {


    public boolean isFull(TreeNode root) {
        Info process = process(root);
        return process.size == 1<<process.height-1;
    }


    class Info {
        int height;
        int size;
        Info (int height,int size) {
            this.size = size;
            this.height = height;
        }
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0,0);
        }
        Info letfInfo = process(root.left);
        Info rightINfo = process(root.right);

        int height = Math.max(letfInfo.height,rightINfo.height)+1;

        int size = letfInfo.size+rightINfo.size+1;

        return new Info(height,size);

    }

}
