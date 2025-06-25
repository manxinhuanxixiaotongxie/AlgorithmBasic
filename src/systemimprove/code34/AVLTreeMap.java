package systemimprove.code34;

/**
 * avl 平衡二叉搜索树
 * 特点：
 * 左树的高度与右树高度的差值的绝对值不超过1
 * @param <K>
 * @param <V>
 */
public class AVLTreeMap<K extends Comparable<K>, V> {

    AVLNode<K,V> root;
    int size;

    public boolean containsKey(K k) {
        if (root == null) {
            return false;
        }
        AVLNode<K, V> cur = root;
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
        AVLNode<K,V> lastIndex = findLastIndex(key);
        if (lastIndex != null && lastIndex.k.compareTo(key) == 0) {
            lastIndex.v = v;
        } else {
            size++;
            root = add(root, key, v);
        }
    }

    /**
     * 小于等于k的最大值
     *
     * @param k
     * @return
     */
    private AVLNode<K,V> findLastIndex(K k) {
        AVLNode cur = root;
        AVLNode pre = null;
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


    public AVLNode<K,V> add(AVLNode<K,V> AVLNode, K k, V v) {
        if (AVLNode == null) {
            return new AVLNode<K, V>(k);
        }

        if (AVLNode.k.compareTo(AVLNode.k) < 0) {
            AVLNode.left = add(AVLNode.left, k, v);
        } else if (AVLNode.k.compareTo(AVLNode.k) > 0) {
            AVLNode.right = add(AVLNode.right, k, v);
        } else {
            AVLNode.v = v;
        }
        AVLNode.height = Math.max(AVLNode.left == null ? 0 : AVLNode.left.height, AVLNode.right == null
                ? 0 : AVLNode.right.height) + 1;
        return maintain(AVLNode);
    }

    public AVLNode<K,V> delete(AVLNode<K,V> cur, K k) {
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
                AVLNode<K, V> rightAVLNode = cur.right;
                while (rightAVLNode.left != null) {
                    rightAVLNode = rightAVLNode.left;
                }
                cur.right = delete(cur.right, rightAVLNode.k);
                rightAVLNode.left = cur.left;
                rightAVLNode.right = cur.right;
                cur = rightAVLNode;
            }
        }
        if (cur != null) {
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null
                    ? 0 : cur.right.height) + 1;
        }
        return maintain(cur);
    }

    public AVLNode<K,V> maintain(AVLNode<K,V> cur) {
        // 调整
        if (cur == null) {
            return null;
        }
        int leftHeight = cur.left == null ? 0 : cur.left.height;
        int rightHeight = cur.right == null ? 0 : cur.right.height;
        // 左右树的高度查的绝对值超过1 说明违规
        // 讨论违规的类型
        if (Math.abs(leftHeight - rightHeight) > 1) {
            // 左树违规
            if (leftHeight > rightHeight) {
                int leftLeftHeight = (cur.left == null || cur.left.left == null) ? 0 : cur.left.left.height;
                int leftRightHeight = (cur.left == null || cur.left.right == null) ? 0 : cur.left.right.height;
                // 讨论违规的类型
                if (leftLeftHeight >= leftRightHeight) {
                    // LL LR型违规
                    // 右旋
                    return rightRotate(cur);
                } else {
                    // LR违规
                    // 左子树进行左旋
                    // 当前节点进行右旋
                    cur.left = leftRotate(cur.left);
                    return rightRotate(cur);
                }
            } else {
                int rightLeftHeight = (cur.right == null || cur.right.left == null) ? 0 : cur.right.left.height;
                int rightRightHeight = (cur.right == null || cur.right.right == null) ? 0 : cur.right.right.height;

                // rr rl型违规
                // 进行左旋
                if (rightLeftHeight >= rightRightHeight) {
                    return leftRotate(cur);
                } else {
                    cur.right = rightRotate(cur.right);
                    return leftRotate(cur);

                }
            }
        }
        return cur;
    }

    public AVLNode<K,V> leftRotate(AVLNode<K,V> cur) {
        if (cur == null) {
            return cur;
        }
        AVLNode<K,V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null
                ? 0 : cur.right.height) + 1;
        right.height = Math.max(right.left.height, right.right == null ? 0 : right.right.height) + 1;
        return right;
    }

    public AVLNode<K,V> rightRotate(AVLNode<K,V> cur) {
        if (cur == null) {
            return null;
        }
        AVLNode<K,V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null
                ? 0 : cur.right.height) + 1;
        left.height = Math.max(left.left == null ? 0 : left.left.height, left.right.height) + 1;
        return left;
    }
}

