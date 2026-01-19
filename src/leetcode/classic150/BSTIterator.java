package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
    List<Integer> list;
    int index;
    int size;

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        process(root);
        index = 0;
        size = list.size();
    }

    public void process(TreeNode root) {
        if (root == null) {
            return;
        } else {

            process(root.left);
            list.add(root.val);
            process(root.right);
        }
    }

    public int next() {
        if (index < size) {
            return list.get(index++);
        } else {
            return -1;
        }
    }

    public boolean hasNext() {
        return index < size;
    }
}
