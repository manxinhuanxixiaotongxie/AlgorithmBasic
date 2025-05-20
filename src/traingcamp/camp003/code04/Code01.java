package traingcamp.camp003.code04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 给你一个字符串类型的数组arr，譬如:
 * String[] arr = { "b\st", "d\", "a\d\e", "a\b\c" };
 * 把这些路径中蕴含的目录结构给打印出来，子目录直接列在父目录下面，并比父目录向右进两格，就像这样:
 * <p>
 * a
 * b
 * c
 * d
 * e
 * b
 * st
 * d
 * <p>
 * 同一级的需要按字母顺序排列不能乱。
 */
public class Code01 {

    public void print(String[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        TrieNode root = new TrieNode("");
        // 构建前缀树
        for (String str : arr) {
            TrieNode cur = root;
            String[] paths = str.split("\\\\");
            // 拆分之后 将每个字符依次装进前缀树种
            for (String path : paths) {
                if (!cur.nextMap.containsKey(path)) {
                    cur.nextMap.put(path, new TrieNode(path));
                }
                cur = cur.nextMap.get(path);
            }
        }
        // 有了前缀树数组之后 打印这颗前缀树
        printProcess(root, 0);

    }

    /**
     * 使用宽度优先遍历有问题
     * 问题是什么？
     * 宽度优先遍历没有把一层遍历完
     *
     * @param root
     */
    private void printTrieTree(TrieNode root) {
        int level = 0;
        // 宽度优先遍历
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TrieNode node = queue.poll();
                if (level != 0) {
                    System.out.println(get4nSpace(level) + node.path);
                }
                if (node.nextMap != null) {
                    for (TrieNode next : node.nextMap.values()) {
                        queue.add(next);
                    }
                }
            }
            level++;
        }
    }

    private void printProcess(TrieNode node, int lelvel) {
        if (lelvel != 0) {
            System.out.println(get4nSpace(lelvel) + node.path);
        }
        for (TrieNode next : node.nextMap.values()) {
            printProcess(next, lelvel + 1);
        }
    }

    public static String get4nSpace(int n) {
        String res = "";
        for (int i = 1; i < n; i++) {
            res += "    ";
        }
        return res;
    }

    // 利用前缀树的知识进行处理


    /**
     * 定义个前缀树的节点
     */
    class TrieNode {

        // 当前节点字符的值
        private String path;

        private TreeMap<String, TrieNode> nextMap;

        TrieNode(String str) {
            this.path = str;
            nextMap = new TreeMap<>();
        }

    }

    public static void main(String[] args) {
        String[] arr = {"b\\st", "d\\", "a\\d\\e", "a\\b\\c"};
        new Code01().print(arr);
    }
}
