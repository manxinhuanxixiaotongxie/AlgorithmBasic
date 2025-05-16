package systemlearning.code16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Description 图的宽度优先遍历
 * @Author Scurry
 * @Date 2023-10-09 17:04
 */
public class Code02_GraphBFS {

    /**
     * 图的宽度优先遍历
     * <p>
     * 1，利用队列实现
     * 2，从源节点开始依次按照宽度进队列，然后弹出
     * 3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
     * 4，直到队列变空
     * <p>
     * 从node触发，进行图的宽度优先遍历
     *
     * @param node
     * @return
     */
    public List<Node> grapgBFS(Node node) {
        LinkedList<Node> queue = new LinkedList<>();
        List<Node> ans = new ArrayList<>();

        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node pop = queue.pop();
            ans.add(pop);
//            if (!set.contains(pop)) {
//                set.add(pop);
//            }
            for (Node next : pop.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return ans;
    }
}
