package systemreview.code11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 给定一个二叉树 按层进行遍历 求最宽的个数
 */
public class Code01_MaxWidthLevel {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int curLevel = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            curLevel++;

            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }
            if (node == curEnd) {
                ans = Math.max(ans, curLevel);
                curLevel = 0;
                curEnd = nextEnd;
            }
        }
        return ans;
    }

    /**
     * leetcode题目  求最大宽度
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int curLevel = 0;
        // 记录每个节点所处的index位置
        Map<TreeNode, Integer> indexMap = new HashMap<>();
        indexMap.put(root, 1);
        // 记录每一层开始节点的位置
        Map<Integer, TreeNode> startedMap = new HashMap<>();
        startedMap.put(0, root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
                indexMap.put(node.left, 2 * indexMap.get(node));
                startedMap.computeIfAbsent(curLevel + 1, k -> node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
                indexMap.put(node.right, 2 * indexMap.get(node) + 1);
                startedMap.computeIfAbsent(curLevel + 1, k -> node.right);
            }
            // 当前层结束
            if (node == curEnd) {
                ans = Math.max(ans, indexMap.get(curEnd) - indexMap.get(startedMap.get(curLevel)) + 1);
                curLevel++;
                curEnd = nextEnd;
            }
        }
        return ans;
    }

    public int widthOfBinaryTree3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        Queue<List<Map<TreeNode, Integer>>> queue = new LinkedList<>();

        List<Map<TreeNode, Integer>> list = new ArrayList<>();
        Map<TreeNode, Integer> indexMap = new HashMap<>();
        indexMap.put(root, 1);
        list.add(indexMap);
        queue.add(list);

        while (!queue.isEmpty()) {
            list = queue.poll();
            List<Map<TreeNode, Integer>> nextList = new ArrayList<>();
            for (Map<TreeNode, Integer> map : list) {
                // 当前层开始塞入下一层
                for (Map.Entry<TreeNode, Integer> entry : map.entrySet()) {
                    TreeNode node = entry.getKey();
                    Integer index = entry.getValue();
                    if (node.left != null) {
                        Map<TreeNode, Integer> leftMap = new HashMap<>();
                        leftMap.put(node.left, 2 * index);
                        nextList.add(leftMap);
                    }
                    if (node.right != null) {
                        Map<TreeNode, Integer> rightMap = new HashMap<>();
                        rightMap.put(node.right, 2 * index + 1);
                        nextList.add(rightMap);
                    }
                }

            }
            if (nextList.isEmpty()) {
                break;
            }
            int curLevel = list.get(list.size() - 1).values().iterator().next() - list.get(0).values().iterator().next() + 1;
            ans = Math.max(ans, curLevel);
            queue.add(nextList);
        }
        return ans;
    }

    public int widthOfBinaryTree4(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;

        return ans;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
