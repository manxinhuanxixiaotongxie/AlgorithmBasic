package systemimprove.code12;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 并查集：
 */

class Node {
    private int value;
    private Node next;

    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}

public class Code01_UnionSet {

    private Map<Node, Node> parentMap;
    private Map<Node, Integer> sizeMap;

    Code01_UnionSet() {
        parentMap = new HashMap<>();
        sizeMap = new HashMap<>();
    }

    void add(Node node) {
        if (node == null) {
            return;
        }

        parentMap.put(node, node);
        sizeMap.put(node, 1);
    }

    public int sets() {
        return sizeMap.size();
    }


    void union(Node a, Node b) {
        if (isSameSet(a, b)) {
            return;
        }
        // 不是同一个集合，进行合并
        Node fatherA = findFather(a);
        Node fatherB = findFather(b);
        int sizeA = sizeMap.get(fatherA);
        int sizeB = sizeMap.get(fatherB);
        if (sizeA >= sizeB) {
            parentMap.put(fatherB, fatherA);
            sizeMap.put(fatherA, sizeA + sizeB);
            sizeMap.remove(fatherB);
        } else {
            parentMap.put(fatherA, fatherB);
            sizeMap.put(fatherB, sizeA + sizeB);
            sizeMap.remove(fatherA);
        }

    }

    /**
     *               0
     *            1
     *          2
     *   |  cur = 0 father = 0        |
     *   |  cur = 1 father = 0        |
     *   |  cur = 2 father = 1        |
     *   |____________________________|
     *
     * @param cur
     * @return
     */
    Node findFather(Node cur) {
        Node father = parentMap.get(cur);
        if (father != cur) {
            father = findFather(father);
        }
        // 可以
        parentMap.put(cur, father);
        return father;
    }

    Node findFather2(Node cur) {
        Stack<Node> stack = new Stack<>();
        while (cur != parentMap.get(cur)) {
            stack.push(cur);
            cur = parentMap.get(cur);
        }
        // 在这个过程中，把cur到头节点的路径上的所有节点都放到stack里
        // 弹出之后 沿途节点都指向了头节点
        while (!stack.isEmpty()) {
            parentMap.put(stack.pop(), cur);
            sizeMap.put(cur, sizeMap.get(cur) + 1);
        }
        return cur;
    }

    Node findFather1(Node node) {
        if (node == null) {
            return null;
        }

        Node cur = node;
        Node father = parentMap.get(cur);
        while (cur != father) {
            cur = father;
            father = parentMap.get(cur);
        }
        return father;
    }

    boolean isSameSet(Node a, Node b) {
        Node curA = a;
        Node curB = b;
        while (curA != parentMap.get(curA)) {
            curA = parentMap.get(curA);
        }
        while (curB != parentMap.get(curB)) {
            curB = parentMap.get(curB);
        }
        return curA == curB;
    }
}
