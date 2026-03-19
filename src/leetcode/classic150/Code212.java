package leetcode.classic150;

import java.util.List;

/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 提示：
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 *
 */
public class Code212 {
    /**
     * 超时
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        int row = board.length;
        int col = board[0].length;
        List<String> ans = new java.util.ArrayList<>();
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                process(board, row, col, i, j, words, ans, visited, "");
            }
        }
        return ans;
    }

    public void process(char[][] board, int row, int col, int r, int c, String[] words, List<String> ans, boolean[][] visited, String path) {
        if (isValid(row, col, r, c) && !visited[r][c]) {
            path += board[r][c];
            visited[r][c] = true;
            for (int i = 0; i < words.length; i++) {
                if (words[i] != null && words[i].equals(path)) {
                    ans.add(path);
                    words[i] = null; // 置null防止重复添加
                }
            }
            process(board, row, col, r - 1, c, words, ans, visited, path);
            process(board, row, col, r + 1, c, words, ans, visited, path);
            process(board, row, col, r, c - 1, words, ans, visited, path);
            process(board, row, col, r, c + 1, words, ans, visited, path);
            visited[r][c] = false;
        }
    }

    public boolean isValid(int row, int col, int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col;
    }

    /**
     * 使用前缀树优化
     */
    public List<String> findWords2(char[][] board, String[] words) {
        int row = board.length;
        int col = board[0].length;
        TrieTree trie = new TrieTree();
        for (String word : words) {
            trie.insert(word);
        }
        List<String> ans = new java.util.ArrayList<>();
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                process2(board, row, col, i, j, trie, ans, visited, "");
            }
        }
        return ans;
    }

    public void process2(char[][] board, int row, int col, int r, int c, TrieTree trie, List<String> ans, boolean[][] visited, String path) {
        if (isValid(row, col, r, c) && !visited[r][c]) {
            path += board[r][c];
            visited[r][c] = true;
            if (!trie.search(path)) {
                visited[r][c] = false;
                return;
            }
            if (trie.contains(path)) {
                ans.add(path);
                trie.delete(path);
            }
            if (trie.search(path)) {
                process2(board, row, col, r - 1, c, trie, ans, visited, path);
                process2(board, row, col, r + 1, c, trie, ans, visited, path);
                process2(board, row, col, r, c - 1, trie, ans, visited, path);
                process2(board, row, col, r, c + 1, trie, ans, visited, path);
            }
            visited[r][c] = false;
        }
    }

    class TrieTree {
        // 前缀树
        TrieNode root;

        TrieTree() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                // 开始遍历
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieNode();
                }
                cur.pass++;
                cur = cur.next[c - 'a'];
            }
            cur.pass++;
            cur.end++;
        }

        public  void delete(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.next[c - 'a'] == null) {
                    return;
                }
                cur.pass--;
                cur = cur.next[c - 'a'];
            }
            cur.pass--;
            cur.end--;
        }

        public boolean search(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.next[c - 'a'] == null) {
                    return false;
                }
                cur = cur.next[c - 'a'];
            }
            return cur.pass > 0;
        }

        public boolean contains(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.next[c - 'a'] == null) {
                    return false;
                }
                cur = cur.next[c - 'a'];
            }
            return cur.end > 0;
        }
    }

    class TrieNode {
        int end;
        int pass;
        TrieNode[] next;

        // 只有小写英文
        TrieNode() {
            this.end = 0;
            this.pass = 0;
            next = new TrieNode[26];
        }
    }
}
