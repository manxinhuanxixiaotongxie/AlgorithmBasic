package leetcode.practice;

public class Code014 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        MyTrieTree trieTree = new MyTrieTree();
        for (String str : strs) {
            trieTree.add(str);
        }
        StringBuilder ans = new StringBuilder();
        MyTrieNode cur = trieTree.root;
        for (String string:strs) {
            for (int i = 0; i < string.length(); i++) {
                if (cur != null && cur.next[string.charAt(i)-'a'] != null && cur.next[string.charAt(i) - 'a'].pass == strs.length) {
                    ans.append(string.charAt(i));
                    cur = cur.next[string.charAt(i) - 'a'];
                } else {
                    break;
                }
            }
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        Code014 code014 = new Code014();
//        String[] strings = {"flower","flow","flight"};
        String[] strings = {"a"};
        System.out.println(code014.longestCommonPrefix(strings));

    }
}
class MyTrieTree {
    MyTrieNode root;
    MyTrieTree() {
        root = new MyTrieNode('/');
        root.next = new MyTrieNode[26];
    }

    public void add(String string) {
        MyTrieNode cur = root;
        for (int i = 0; i < string.length(); i++) {
            if (cur.next[string.charAt(i) - 'a'] == null) {
                cur.next[string.charAt(i) - 'a'] = new MyTrieNode(string.charAt(i));
            }
            cur.pass++;
            cur = cur.next[string.charAt(i) - 'a'];
        }
        cur.pass++;
        cur.end++;
    }

}

class MyTrieNode {
    char val;
    MyTrieNode[] next;
    int pass;
    int end;
    MyTrieNode(char val) {
        this.val = val;
        next = new MyTrieNode[26];
        pass = 0;
        end = 0;
    }
}
