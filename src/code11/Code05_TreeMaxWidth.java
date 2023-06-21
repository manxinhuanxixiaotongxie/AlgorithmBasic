package code11;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-23 16:32
 */

import javafx.util.Pair;
import newerclass.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 求二叉树最宽的层有多少个节点：按层遍历，
 * 定义一个当前层结束的结尾节点变量
 * 定义一个下一层结束的结尾节点变量
 */
public class Code05_TreeMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    public static int maxWidthNoMap(Node head) {

        if (head == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        // 当前层，最右节点是谁
        Node curEnd = head;
        // 下一层，最右节点是谁
        Node nextEnd = null;
        // 当前层有多少节点
        int curLevelNodes = 0;
        // 最大节点数
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;

            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;

            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }

        }

        return max;


    }


    /**
     * 剑指 Offer II 044. 二叉树每层的最大值
     * <p>
     * https://leetcode.cn/problems/hPov7L/description/
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        list.add(root);

        while (!list.isEmpty()) {
            int size = list.size();
            int max = list.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode pop = list.pop();
                max = Math.max(max, pop.val);
                if (pop.left != null) {
                    list.add(pop.left);
                }
                if (pop.right != null) {
                    list.add(pop.right);
                }
            }
            ans.add(max);


        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);

        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(3);

        TreeNode node16 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = null;
        node3.right = node16;

        Code05_TreeMaxWidth code05_treeMaxWidth = new Code05_TreeMaxWidth();
        code05_treeMaxWidth.widthOfBinaryTree(node1);


    }

    /**
     * 662. 二叉树最大宽度
     * <p>
     * https://leetcode.cn/problems/maximum-width-of-binary-tree/
     *
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        int res = 1;
        List<Pair<TreeNode, Integer>> arr = new ArrayList<Pair<TreeNode, Integer>>();
        arr.add(new Pair<TreeNode, Integer>(root, 1));
        while (!arr.isEmpty()) {
            List<Pair<TreeNode, Integer>> tmp = new ArrayList<Pair<TreeNode, Integer>>();
            for (Pair<TreeNode, Integer> pair : arr) {
                TreeNode node = pair.getKey();
                int index = pair.getValue();
                if (node.left != null) {
                    tmp.add(new Pair<TreeNode, Integer>(node.left, index * 2));
                }
                if (node.right != null) {
                    tmp.add(new Pair<TreeNode, Integer>(node.right, index * 2 + 1));
                }
            }
            res = Math.max(res, arr.get(arr.size() - 1).getValue() - arr.get(0).getValue() + 1);
            arr = tmp;
        }
        return res;
    }

    public static int maxWidthwWithMap(Node head) {

        if (head == null) {
            return 0;
        }

        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 当前层
        int curLevel = 1;
        // 当前层有多少节点
        int curLevelNodes = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                levelMap.put(cur.left, levelMap.get(cur) + 1);
                queue.add(cur.left);
            }

            if (cur.right != null) {
                levelMap.put(cur.right, levelMap.get(cur) + 1);
                queue.add(cur.right);
            }

            // 不是当前层
            if (levelMap.get(cur) != curLevel) {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            } else {
                curLevelNodes++;
            }
        }

        return max;
    }

}
