package systemimprove.code09;

import java.util.*;

public class Code02_NodeByLevel {
    /**
     * 按层打印二叉树
     *
     * @param head
     */
    public void printByLevel(TreeNode head) {
        if (head == null) {
            return;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(head);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.pollFirst();
            System.out.println(treeNode.val);
            if (treeNode.left != null) {
                queue.addLast(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.addLast(treeNode.right);
            }
        }
    }

    /**
     * 找到二叉树中的最大层（节点数最大）
     * 使用容器
     *
     * @param head
     * @return
     */
    public static int findMaxLevel(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int max = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        // node  --> level
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int level = 1;
        while (!queue.isEmpty()) {
            int count = 0;
            TreeNode treeNode = queue.pollFirst();
            if (levelMap.get(treeNode) == level) {
                count++;
            } else {
                level++;
                count = 1;
            }
            if (treeNode.left != null) {
                queue.addLast(treeNode.left);
                levelMap.put(treeNode.left, levelMap.get(treeNode) + 1);
            }
            if (treeNode.right != null) {
                queue.addLast(treeNode.right);
                levelMap.put(treeNode.right, levelMap.get(treeNode) + 1);
            }
            max = Math.max(count, max);
        }
        return max;
    }

    public int findMaxLevel2(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int max = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(head);
        TreeNode curLevelEnd = head;
        TreeNode nextLevelEnd = null;
        while (!queue.isEmpty()) {
            int count = 0;
            TreeNode treeNode = queue.pollFirst();

            if (treeNode.left != null) {
                nextLevelEnd = treeNode.left;
                queue.addLast(treeNode.left);
            }
            if (treeNode.right != null) {
                nextLevelEnd = treeNode.right;
                queue.addLast(treeNode.right);
            }
            if (treeNode != curLevelEnd) {
                curLevelEnd = nextLevelEnd;
                count = 1;
            } else {
                count++;
            }
            max = Math.max(count, max);
        }
        return max;
    }

    /**
     * leetcode原题
     *
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int curLevel = 0;
        TreeNode curLevelEnd = root;
        TreeNode nextLevelEnd = null;
        int levelMax = 0;
        while (!queue.isEmpty()) {
            // 按层遍历 找到每层的最大值
            TreeNode treeNode = queue.pollFirst();

            if (treeNode.left != null) {
                queue.addLast(treeNode.left);
                nextLevelEnd = treeNode.left;
            }
            if (treeNode.right != null) {
                queue.addLast(treeNode.right);
                nextLevelEnd = treeNode.right;
            }
            levelMax = Math.max(levelMax, treeNode.val);

            if (treeNode != curLevelEnd) {
                levelMax = Math.max(levelMax, treeNode.val);
            } else {
                // 当前节点已经来到的当前层的最后一个节点
                levelMax = Math.max(levelMax, treeNode.val);
                ans.add(curLevel, levelMax);
                // 将下一层的最后一个节点赋值给当前层的最后一个节点
                curLevelEnd = nextLevelEnd;
                // 下一层的高度加1
                curLevel++;
                levelMax = 0;
            }


        }
        return ans;
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = generateRandomBST(maxLevel, maxValue);
            if (findMaxLevel(head) != findMaxLevel(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");


        System.out.println("=====================");

        /**
         * 1 / \ 3 2 / \ \ 5 3 9
         */
        TreeNode newHead = new TreeNode(1);
        newHead.left = new TreeNode(3);
        newHead.right = new TreeNode(2);
        newHead.left.left = new TreeNode(5);
        newHead.left.right = new TreeNode(3);
        newHead.right.right = new TreeNode(9);
        Code02_NodeByLevel code02_nodeByLevel = new Code02_NodeByLevel();
        code02_nodeByLevel.largestValues(newHead);

    }


}
