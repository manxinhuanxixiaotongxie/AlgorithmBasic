package systemimprove.code32;

/**
 * 有一个滑动窗口（讲过的）：
 * <p>
 * 1）L是滑动窗口最左位置、R是滑动窗口最右位置，一开始LR都在数组左侧
 * 2）任何一步都可能R往右动，表示某个数进了窗口
 * 3）任何一步都可能L往右动，表示某个数出了窗口
 * <p>
 * 想知道每一个窗口状态的中位数
 */
public class Code02_SlidingWindowMedian {
    public static class SBTNode<K extends Comparable<K>> {
        public K key;
        public SBTNode<K> l;
        public SBTNode<K> r;
        public int size;

        public SBTNode(K k) {
            key = k;
            size = 1;
        }
    }

    public static class SizeBalancedTreeMap<K extends Comparable<K>> {
        private SBTNode<K> root;

        private SBTNode<K> rightRotate(SBTNode<K> cur) {
            SBTNode<K> leftNode = cur.l;
            cur.l = leftNode.r;
            leftNode.r = cur;
            leftNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return leftNode;
        }

        private SBTNode<K> leftRotate(SBTNode<K> cur) {
            SBTNode<K> rightNode = cur.r;
            cur.r = rightNode.l;
            rightNode.l = cur;
            rightNode.size = cur.size;
            cur.size = (cur.l != null ? cur.l.size : 0) + (cur.r != null ? cur.r.size : 0) + 1;
            return rightNode;
        }

        private SBTNode<K> maintain(SBTNode<K> cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.l != null ? cur.l.size : 0;
            int leftLeftSize = cur.l != null && cur.l.l != null ? cur.l.l.size : 0;
            int leftRightSize = cur.l != null && cur.l.r != null ? cur.l.r.size : 0;
            int rightSize = cur.r != null ? cur.r.size : 0;
            int rightLeftSize = cur.r != null && cur.r.l != null ? cur.r.l.size : 0;
            int rightRightSize = cur.r != null && cur.r.r != null ? cur.r.r.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        private SBTNode<K> findLastIndex(K key) {
            SBTNode<K> pre = root;
            SBTNode<K> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.l;
                } else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        private SBTNode<K> add(SBTNode<K> cur, K key) {
            if (cur == null) {
                return new SBTNode<K>(key);
            } else {
                cur.size++;
                if (key.compareTo(cur.key) < 0) {
                    cur.l = add(cur.l, key);
                } else {
                    cur.r = add(cur.r, key);
                }
                return maintain(cur);
            }
        }

        /**
         * 加入树的节点  当value相等的时候  就会使用数组的相对位置进行排序
         * 删除的时候也按照这个及进行查找 为什么一定要这样呢
         * 我们对这个平衡二叉树的要求：
         * 1.能够添加重复值
         * 2.允许删除节点 并且值相等的时候 能准确删除数组相对位置数
         * 3.能够根据index找到对应的节点
         * @param cur
         * @param key
         * @return
         */
        private SBTNode<K> delete(SBTNode<K> cur, K key) {
            cur.size--;
            if (key.compareTo(cur.key) > 0) {
                cur.r = delete(cur.r, key);
            } else if (key.compareTo(cur.key) < 0) {
                cur.l = delete(cur.l, key);
            } else {
                if (cur.l == null && cur.r == null) {
                    // free cur memory -> C++
                    cur = null;
                } else if (cur.l == null && cur.r != null) {
                    // free cur memory -> C++
                    cur = cur.r;
                } else if (cur.l != null && cur.r == null) {
                    // free cur memory -> C++
                    cur = cur.l;
                } else {
                    SBTNode<K> pre = null;
                    SBTNode<K> des = cur.r;
                    des.size--;
                    while (des.l != null) {
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if (pre != null) {
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
                    // free cur memory -> C++
                    cur = des;
                }
            }
            return cur;
        }

        /**
         * 在树中找到第kth个节点
         * @param cur
         * @param kth
         * @return
         */
        private SBTNode<K> getIndex(SBTNode<K> cur, int kth) {
            if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
                return cur;
            } else if (kth <= (cur.l != null ? cur.l.size : 0)) {
                return getIndex(cur.l, kth);
            } else {
                //去右树上找 找到的是右树上的第kth - (cur.l != null ? cur.l.size : 0) - 1个节点
                return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
            }
        }

        public int size() {
            return root == null ? 0 : root.size;
        }

        public boolean containsKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0;
        }

        public void add(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K> lastNode = findLastIndex(key);
            if (lastNode == null || key.compareTo(lastNode.key) != 0) {
                root = add(root, key);
            }
        }

        public void remove(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }

        /**
         * 获取第index个节点
         * 比如 tree的size为4 对应的index是0 1 2 3
         * 要找到
         * @param index
         * @return
         */
        public K getIndexKey(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).key;
        }

    }

    public static class Node implements Comparable<Node> {
        public int index;
        public int value;

        public Node(int i, int v) {
            index = i;
            value = v;
        }

        /**
         *这里的比较需要注意 直接用减号不行
         * 超过int的范围 会越界 导致判断不准确
         * @param o the object to be compared.
         * @return
         */
        @Override
        public int compareTo(Node o) {
            return value != o.value ? Integer.compare(value, o.value)
                    : Integer.compare(index, o.index);
        }
    }

    /**
     * 这棵树当value相等的时候  排序的规则是按照数组的下标的相对位置进行处理
     *
     * 当value不相等的时候  就按照value的大小进行排序
     *
     * 那么就意味着 当value不相等的时候size的相对位置与原数组相对位置是相对的
     * 当value相等的时候 size的位置与数组的相对位置是一一对应的  那么就意味着
     * 不管在什么时候  size的相对的位置总是与原数组的下标总是相对应的
     * 我们在删除一个节点的时候 只要给定index位置以及value值 就能准确的删除对应的节点
     * @param nums
     * @param k
     * @return
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        SizeBalancedTreeMap<Node> map = new SizeBalancedTreeMap<>();
        // 每一次加进来的都是的新的节点
        for (int i = 0; i < k - 1; i++) {
            map.add(new Node(i, nums[i]));
        }
        double[] ans = new double[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < nums.length; i++) {
            map.add(new Node(i, nums[i]));
            if (map.size() % 2 == 0) {
                // map的长度是4 那么获取的数就是index为 1 2的数
                // 就是tree对应的第2个数和第3个数
                Node upmid = map.getIndexKey(map.size() / 2 - 1);
                Node downmid = map.getIndexKey(map.size() / 2);
                ans[index++] = ((double) upmid.value + (double) downmid.value) / 2;
            } else {
                Node mid = map.getIndexKey(map.size() / 2);
                ans[index++] = mid.value;
            }
            map.remove(new Node(i - k + 1, nums[i - k + 1]));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {-2147483648,-2147483648,2147483647,-2147483648,1,3,-2147483648,-100,8,17,22,-2147483648,-2147483648,2147483647,2147483647,2147483647,2147483647,-2147483648,2147483647,-2147483648};
        int w = 6;
        double[] ans = medianSlidingWindow(nums, w);
        for (double an : ans) {
            System.out.print(an + " ");
        }
    }

}
