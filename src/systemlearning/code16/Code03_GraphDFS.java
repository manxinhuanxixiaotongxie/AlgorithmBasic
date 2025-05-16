package systemlearning.code16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @Description 图的深度优先遍历
 * <p>
 * 1，利用栈实现
 * 2，从源节点开始把节点按照深度放入栈，然后弹出
 * 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 * 4，直到栈变空
 * @Author Scurry
 * @Date 2023-10-09 17:04
 */
public class Code03_GraphDFS {

    public List<Node> graphDFS1(Node node) {
        if (node == null) {
            return null;
        }
        Set<Node> set = new HashSet<>();
        List<Node> ans = new LinkedList<>();

        process(node, set, ans);
        return ans;

    }

    public void process(Node node, Set<Node> set, List<Node> ans) {

        ans.add(node);
        set.add(node);
        for (Node next : node.nexts) {
            if (!set.contains(next)) {
                process(next, set, ans);
            }
        }

    }

    /**
     * 深度优先遍历使用栈实现
     *
     * @param node
     * @return
     */
    public List<Node> graphDFS2(Node node) {
        Set<Node> set = new HashSet<>();
        List<Node> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        stack.push(node);
        ans.add(node);
        set.add(node);

        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(pop)) {
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    ans.add(next);
                    break;
                }
            }
        }
        return ans;
    }

}
