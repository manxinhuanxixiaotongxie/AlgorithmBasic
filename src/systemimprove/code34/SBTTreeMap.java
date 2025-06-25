package systemimprove.code34;

/**
 * size balance tree 实现
 * 特点：
 * 孙子节点的数量不能超过叔叔节点的数量
 *
 */

public class SBTTreeMap<K extends Comparable<K>, V> {
    private SBTNode<K,V> root;
    private int size;

    public SBTNode<K, V> add(SBTNode<K,V> node, K k) {
        if (node == null) {
            return new SBTNode<>(k);
        }
        size++;
        if (node.k.compareTo(k) > 0) {
            node.left = add(node.left, k);
        } else if (node.k.compareTo(k) < 0) {
            node.right = add(node.right, k);
        }
        return maintain(node);
    }

    /**
     * 调整 四种情况的违规
     * 1.ll型违规 当前节点右旋
     * 2.lr型违规 左树节点左旋 当前节点进行右旋
     * 3.rr型违规 当前节点左旋
     * 4.rl型违规 右树节点右旋 当前节点进行左旋
     *
     * @param node
     * @return
     */
    public SBTNode<K, V> maintain(SBTNode<K, V> node) {
        if (node == null) {
            return null;
        }
        int leftSize = node.left == null ? 0 : node.left.size;
        int righSize = node.right == null ? 0 : node.right.size;
        int leftLeftSize = node.left != null && node.left.left != null ? node.left.left.size : 0;
        int leftRightSize = node.left != null && node.left.right != null ? node.left.right.size : 0;
        int rightLeftSize = node.right != null && node.right.left != null ? node.right.left.size : 0;
        int rightRightSize = node.right != null && node.right.right != null ? node.right.right.size : 0;
        // ll型违规
        if (leftLeftSize > righSize) {
            // LL型
            node = rightRotate(node);
            // 换了孩子之后的节点 都需要进行平衡性的调整
            // 右旋之后 左孩子以及新的cur节点的孩子更换了
            // 右旋的过程   新的node节点会变成当前node的左孩子  原来左孩子的右节点会被当前node替代
            // 原来node的左孩子会被远node节点的左孩子的有孩子替代
            // 因此更换了孩子的节点变成了新node 以及新node的左孩子
            node.left = maintain(node.left);
            node = maintain(node);
        } else if (leftRightSize > righSize) {
            // LR型
            // LR型的违规需要将当前节点的作数的进行左旋  左旋之后 将以node为头的整棵树进行右旋
            node.left = leftRotate(node.left);
            node = rightRotate(node);
            // 变更了孩子的节点有哪些 现在分析一下
            // 第一次左旋的时候  左孩子的变化
            // 左孩子的右节点会替换掉左孩子的位置 原左孩子的右树是新的头节点会被当前当前节点的左孩子替换
            // 对于左树来说变换了孩子节点的就是新node节点 以及新node的左孩子
            // 再进行右旋  右旋之后 新的node会变成原node的左孩子
            // 此时的变化是原node的原node的右孩子会被原node替换 原node的左孩子的右树会被新node右树的左孩子接管
            // 变换之后 其实新node以及新node的左孩子、新node的右孩子发生了变化
            node.left = leftRotate(node.left);
            node = rightRotate(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (rightRightSize > leftSize) {
            // RR型
            node = leftRotate(node);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (rightLeftSize > leftSize) {
            // RL型
            node.right = rightRotate(node.right);
            node = leftRotate(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        }

        return node;
    }

    public SBTNode<K, V> delete(SBTNode<K, V> cur, K key) {
        if (cur == null) {
            return null;
        }

        cur.size--;
        if (cur.k.compareTo(key) > 0) {
            cur = delete(cur.left, key);
        } else if (cur.k.compareTo(key) < 0) {
            cur = delete(cur.right, key);
        } else {

            // 相等要执行删除
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left != null && cur.right == null) {
                cur = cur.left;
            } else if (cur.left == null && cur.right != null) {
                cur = cur.right;
            } else {
                SBTNode<K,V> pre = null;
                SBTNode<K,V> leftMostRightNode = cur.right;
                // 找到右树最左侧节点
                while (leftMostRightNode.left != null) {
                    pre = leftMostRightNode;
                    leftMostRightNode = leftMostRightNode.left;
                    leftMostRightNode.size--;
                }

                // 要考虑的一种场景 右树的最左侧节点的右树如果存在的话  右树最左侧节点的右树会被pre的左树接管
                if (pre != null) {
                    pre.left = leftMostRightNode.right;
                    leftMostRightNode.right = cur.right;
                }

                leftMostRightNode.left = cur.left;
                leftMostRightNode.size = leftMostRightNode.left.size + (leftMostRightNode.right == null ? 0 : leftMostRightNode.right.size) + 1;
                cur = leftMostRightNode;

            }
        }
//        return maintain(cur);
        return cur;
    }

    public SBTNode<K,V> leftRotate(SBTNode<K,V> cur) {
        if (cur == null) {
            return null;
        }
        // 左旋
        SBTNode<K,V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        right.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return right;
    }

    public SBTNode<K,V> rightRotate(SBTNode<K,V> cur) {
        if (cur == null) {
            return null;
        }
        SBTNode<K,V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        left.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return left;
    }
}

