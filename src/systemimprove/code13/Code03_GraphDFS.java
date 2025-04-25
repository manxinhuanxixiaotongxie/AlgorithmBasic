package systemimprove.code13;

import java.util.*;

/**
 * 图的深度优先遍历使用栈实现
 */
public class Code03_GraphDFS {

    public List<Node> graphDFS(Node node) {
        if (node == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        Set<Node> set = new HashSet<>();
        set.add(node);
        List<Node> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ans.add(cur);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    break;
                }
            }
        }

        return ans;
    }
}
