package systemreview.code37;

/**
 * 有一个滑动窗口（讲过的）：
 * <p>
 * 1）L是滑动窗口最左位置、R是滑动窗口最右位置，一开始LR都在数组左侧
 * 2）任何一步都可能R往右动，表示某个数进了窗口
 * 3）任何一步都可能L往右动，表示某个数出了窗口
 * <p>
 * 想知道每一个窗口状态的中位数  求的是严格意义上的
 */
public class Code02_SlideWindow {

    /**
     * 给定窗口 nums 以及一个数k，求窗口中的中位数
     * 求得是严格意义上的中位数
     * 如果k 是偶数 那么求的就是k/2 k/2+1
     * 如果k 是奇数 那么求的就是 k/2+1
     *
     * @param nums
     * @param k
     * @return
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return null;
        }
        WindowTreeMap windowTreeMap = new WindowTreeMap();
        for (int i = 0; i < k - 1; i++) {
            windowTreeMap.put(new Node(i, nums[i]));
        }
        double[] ans = new double[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < nums.length; i++) {
            windowTreeMap.put(new Node(i, nums[i]));
            if (k % 2 == 0) {
                Node upmid = (Node) windowTreeMap.getIndexKey(k / 2);
                Node downmid = (Node) windowTreeMap.getIndexKey(k / 2 + 1);
                ans[index++] = ((double) upmid.value + (double) downmid.value) / 2;
            } else {
                Node mid = (Node) windowTreeMap.getIndexKey(k / 2);
                ans[index++] = mid.value;
            }
            windowTreeMap.remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-2147483648, -2147483648, 2147483647, -2147483648, 1, 3, -2147483648, -100, 8, 17, 22, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648};
        int w = 6;
        double[] ans = medianSlidingWindow(nums, w);
        for (double an : ans) {
            System.out.print(an + " ");
        }
    }

}

class WindowTreeMap<K extends Comparable<K>, V> {
    private WindowNode root;
    private int size;

    public WindowNode<K, V> leftRoate(WindowNode<K, V> node) {
        WindowNode<K, V> right = node.right;
        node.right = right.left;
        right.left = node;
        right.size = node.size;
        node.size = (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0) + 1;
        return right;
    }

    public WindowNode rightRoate(WindowNode<K, V> node) {
        WindowNode<K, V> left = node.left;
        node.left = left.right;
        left.right = node;
        left.size = node.size;
        node.size = (node.left != null ? node.left.size : 0) + (node.right != null ? node.right.size : 0) + 1;
        return left;
    }

    public void put(K key) {
        root = add(root, key);
    }

    public void remove(K key) {
        root = delete(root, key);
    }

    private WindowNode<K, V> delete(WindowNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        node.size--;
        if (node.key.compareTo(key) < 0) {
            node.right = delete(node.right, key);
        } else if (node.key.compareTo(key) > 0) {
            node.left = delete(node.left, key);
        } else {

            if (node.left == null && node.right != null) {
                node = node.right;
            } else if (node.right == null && node.left != null) {
                node = node.left;
            } else if (node.left == null && node.right == null) {
                node = null;
            } else {
                WindowNode<K, V> righNode = node.right;
                WindowNode<K, V> pre = null;
                righNode.size--;
                while (righNode.left != null) {
                    pre = righNode;
                    righNode = righNode.left;
                    righNode.size--;
                }
                if (pre != null) {
                    pre.left = righNode.right;
                    righNode.right = node.right;
                }
                // 处理右树
                righNode.left = node.left;
                righNode.size = righNode.left.size + (righNode.right != null ? righNode.right.size : 0) + 1;
                node = righNode;
            }
        }
        return node;
    }

    public K getIndexKey(int index) {
        if (index < 0 || index >= root.size) {
            throw new RuntimeException("invalid parameter.");
        }
        return getIndex(root, index).key;
    }

    private WindowNode<K, V> getIndex(WindowNode cur, int kth) {
        if (cur == null) {
            return null;
        }
        if (kth == (cur.left != null ? cur.left.size : 0) + 1) {
            return cur;
        } else if (kth <= (cur.left != null ? cur.left.size : 0)) {
            return getIndex(cur.left, kth);
        } else {
            //去右树上找 找到的是右树上的第kth - (cur.l != null ? cur.l.size : 0) - 1个节点
            return getIndex(cur.right, kth - (cur.left != null ? cur.left.size : 0) - 1);
        }
    }


    private WindowNode add(WindowNode node, K key) {

        if (node == null) {
            return new WindowNode(key);
        } else {
            node.size++;
            if (node.key.compareTo(key) < 0) {
                node.right = add(node.right, key);
            } else if (node.key.compareTo(key) > 0) {
                node.left = add(node.left, key);
            }
            return maintain(node);
        }
    }

    private WindowNode<K, V> maintain(WindowNode<K, V> node) {
        if (node == null) {
            return null;
        }
        int leftSize = node.left != null ? node.left.size : 0;
        int leftLeftSize = node.left != null && node.left.left != null ? node.left.left.size : 0;
        int leftRightSize = node.left != null && node.left.right != null ? node.left.right.size : 0;
        int rightSize = node.right != null ? node.right.size : 0;
        int rightLeftSize = node.right != null && node.right.left != null ? node.right.left.size : 0;
        int rightRightSize = node.right != null && node.right.right != null ? node.right.right.size : 0;
        if (leftLeftSize > rightSize) {
            node = rightRoate(node);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (leftRightSize > rightSize) {
            node.left = leftRoate(node.left);
            node = rightRoate(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        } else if (rightRightSize > leftSize) {
            node = leftRoate(node);
            node.left = maintain(node.left);
            node = maintain(node);
        } else if (rightLeftSize > leftSize) {
            node.right = rightRoate(node.right);
            node = leftRoate(node);
            node.left = maintain(node.left);
            node.right = maintain(node.right);
            node = maintain(node);
        }
        return node;
    }
}


class WindowNode<K extends Comparable<K>, V> {
    // SBT的平衡因子
    int size;
    // 加入到SBT节点值
    K key;

    WindowNode<K, V> left;
    WindowNode<K, V> right;

    WindowNode(K k) {
        this.key = k;
        size = 1;
    }
}

class Node implements Comparable<Node> {
    int index;
    int value;

    Node(int index, int value) {
        this.index = index;
        this.value = value;
    }

    /**
     * 对每个节点进行封装 方便进行删除数据
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Node o) {
        // return value != o.value ? value - o.value : index - o.index;
        return value != o.value ? Integer.compare(value, o.value) : Integer.compare(index, o.index);
    }
}
