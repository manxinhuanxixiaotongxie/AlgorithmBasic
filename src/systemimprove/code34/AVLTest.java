package systemimprove.code34;

public class AVLTest {


}

class AVLTree<K extends Comparable<K>, V> {

    Node root;
    int size;

    public boolean containsKey(K k) {
        if (root == null) {
            return false;
        }
        Node<K, V> cur = root;
        while (cur != null) {
            if (k.compareTo(cur.k) == 0) {
                return true;
            } else if (k.compareTo(cur.k) < 0) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    public void put(K key, V v) {
        Node lastIndex = findLastIndex(key);
        if (lastIndex != null && lastIndex.k.compareTo(key) == 0) {
            lastIndex.v = v;
        }else {
            size++;
            root = add(root, key,v);
        }
    }

    /**
     * 小于等于k的最大值
     *
     * @param k
     * @return
     */
    private Node findLastIndex(K k) {
        Node cur = root;
        Node pre = null;
        while (cur != null) {
            pre = cur;
            if (cur.k.compareTo(k) < 0) {
                cur = cur.right;
            } else if (cur.k.compareTo(k) > 0) {
                cur = cur.left;
            } else {
                return cur;
            }
        }
        return pre;
    }


    public Node add(Node node, K k,V v) {
        if (node == null) {
            return new Node<K, V>(k);
        }

        if (node.k.compareTo(node.k) < 0) {
            node.left = add(node.left, k,v);
        } else if (node.k.compareTo(node.k) > 0) {
            node.right = add(node.right, k,v);
        }else {
            node.v = v;
        }
        node.height = Math.max(node.left == null ? 0 : node.left.height, node.right == null
                ? 0 : node.right.height) + 1;
        return maintain(node);
    }

    public Node delete(Node cur, K k) {
        if (cur == null) {
            return null;
        }
        if (cur.k.compareTo(k) > 0) {
            cur.left = delete(cur.left, k);
        } else if (cur.k.compareTo(k) < 0) {
            cur.right = delete(cur.right, k);
        } else {
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left == null || cur.right == null) {
                cur = cur.left == null ? cur.right : cur.left;
            } else {
                // 右树最左侧节点
                Node<K,V> rightNode = cur.right;
                while (rightNode.left != null) {
                    rightNode = rightNode.left;
                }
                cur.right = delete(cur.right, rightNode.k);
                rightNode.left = cur.left;
                rightNode.right = cur.right;
                cur = rightNode;

            }
        }
        if (cur != null) {
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null
                    ? 0 : cur.right.height) + 1;
        }
        return maintain(cur);
    }

    public Node maintain(Node cur) {
        // 调整
        if (cur == null) {
            return null;
        }
        int leftHeight = cur.left == null ? 0 : cur.left.height;
        int rightHeight = cur.right == null ? 0 : cur.right.height;
        if (Math.abs(leftHeight - rightHeight) > 1) {
            if (leftHeight > rightHeight) {
                int leftLeftHeight = (cur.left == null || cur.left.left == null) ? 0 : cur.left.left.height;
                int leftRightHeight = (cur.left == null || cur.left.right == null) ? 0 : cur.left.right.height;
                if (leftLeftHeight >= leftRightHeight) {
                    // LL LR型违规
                    // 右旋
                    return rightRotate(cur);
                } else {
                    // LR违规
                    // 左旋
                    cur.left = lertRotate(cur.left);
                    return rightRotate(cur);
                }
            } else {
                int rightLeftHeight = (cur.right == null || cur.right.left == null) ? 0 : cur.right.left.height;
                int rightRightHeight = (cur.right == null || cur.right.right == null) ? 0 : cur.right.right.height;

                if (rightLeftHeight >= rightRightHeight) {
                    return rightRotate(cur);
                } else {
                    cur.right = rightRotate(cur.right);
                    return lertRotate(cur);

                }
            }
        }
        return cur;
    }

    public Node lertRotate(Node cur) {
        if (cur == null) {
            return cur;
        }
        Node right = cur.right;
        cur.right = right.left;
        right.left = cur;
        cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null
                ? 0 : cur.right.height) + 1;
        right.height = Math.max(right.left.height, right.right == null ? 0 : right.right.height) + 1;
        return right;
    }

    public Node rightRotate(Node cur) {
        if (cur == null) {
            return null;
        }
        Node left = cur.left;
        cur.left = left.right;
        left.right = cur;
        cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null
                ? 0 : cur.right.height) + 1;
        left.height = Math.max(left.left == null ? 0 : left.left.height, left.right.height) + 1;
        return left;
    }
}

class Node<K extends Comparable<K>, V> {
    Node left;

    Node right;

    int height;

    K k;

    V v;

    Node(K k) {
        this.k = k;
        this.height = 1;
    }
}
