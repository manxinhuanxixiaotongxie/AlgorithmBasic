package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Code0140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) {
            return null;
        }
        List<String> ans = new ArrayList<>();
        process(s, 0, wordDict, ans, "");
        return ans;
    }

    private void process(String s, int index, List<String> wordDict, List<String> ans, String path) {
        if (index == s.length()) {
            ans.add(path.substring(0, path.length() - 1));
        } else {
            for (int end = index; end < s.length(); end++) {
                if (wordDict.contains(s.substring(index, end + 1))) {
                    path += s.substring(index, end + 1) + " ";
                    process(s, end + 1, wordDict, ans, path);
                    path = path.substring(0, path.length() - s.substring(index, end + 1).length() - 1);
                }
            }
        }
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        if (wordDict == null || wordDict.size() == 0) {
            return null;
        }
        List<String> ans = new ArrayList<>();
        process(s, 0, wordDict, ans, "");
        return ans;
    }

    class TreeNode {
        TreeNode[] next;

        TreeNode() {
            next = new TreeNode[26];
        }
    }
}
