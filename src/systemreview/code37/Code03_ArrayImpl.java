package systemreview.code37;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 设计一个结构包含如下两个方法：
 * void add(int index, int num)：把num加入到index位置
 * int get(int index) ：取出index位置的值
 * void remove(int index) ：把index位置上的值删除
 * 要求三个方法时间复杂度O(logN)
 */
public class Code03_ArrayImpl {
    /**
     * 使用有序表实现一个数组 达到
     * 1.插入O(logN)
     * 2.删除O(logN)
     * 3.查询O(logN)
     */

    public static void main(String[] args) {
        int test = 50000;
        int max = 10000;
        ArrayList<Integer> list = new ArrayList<>();
        ArrayMap<Integer, Integer> map = new ArrayMap<>();
        for (int i = 0; i < test; i++) {
            if (Math.random() < 0.5) {
                int index = (int) (Math.random() * (list.size() + 1));
                int num = (int) (Math.random() * max);
                list.add(index, num);
                map.put(index, num);
            } else {
                if (list.size() > 0) {
                    int index = (int) (Math.random() * list.size());
                    list.remove(index);
                    map.remove(index);
                }
            }

        }
        boolean isPass = true;
        for (int i = 0; i < list.size(); i++) {
            if (!Objects.equals(list.get(i), map.get(i))) {
                System.out.println(list.get(i));
                System.out.println(map.get(i));
                System.out.println("Oops");
                isPass = false;
            }
        }

        System.out.println(isPass ? "pass" : "failed");

        test = 500000;
        list = new ArrayList<>();
        map = new ArrayMap<>();
        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (list.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            list.add(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList插入总时长(毫秒) ： " + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            list.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList读取总时长(毫秒) : " + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * list.size());
            list.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayList删除总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (map.size() + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            map.put(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayMap插入总时长(毫秒) ： " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (i + 1));
            map.get(randomIndex);
        }
        end = System.currentTimeMillis();

        System.out.println("ArrayMap读取总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * map.size());
            map.remove(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("ArrayMap删除总时长(毫秒) : " + (end - start));
    }
}

class ArrayMap<K extends Comparable<K>, V> {
    private ArrayNode root;

    public void put(int index, K k) {
        root = add(root, index, k);
    }

    public void remove(int index) {
        if (index >= 0 && root != null)
            root = delete(root, index);
    }

    private ArrayNode<K, V> delete(ArrayNode<K, V> cur, int index) {
        if (cur == null) {
            return null;
        }
        cur.size--;
        int leftSize = cur.left != null ? cur.left.size : 0;
        if (index != leftSize) {
            if (index < leftSize) {
                cur.left = delete(cur.left, index);
            } else {
                cur.right = delete(cur.right, index - leftSize - 1);
            }
            return cur;
        } else {
            if (cur.left == null && cur.right == null) {
                return null;
            } else if (cur.left == null) {
                return cur.right;
            } else if (cur.right == null) {
                return cur.left;
            } else {
                ArrayNode<K, V> pre = null;
                ArrayNode<K, V> suc = cur.right;
                suc.size--;
                while (suc.left != null) {
                    pre = suc;
                    suc = suc.left;
                    suc.size--;
                }
                if (pre != null) {
                    pre.left = suc.right;
                    suc.right = cur.right;
                }
                suc.left = cur.left;
                suc.size = suc.left.size + (suc.right == null ? 0 : suc.right.size) + 1;
                cur = suc;
            }
        }
        return cur;
    }

    public K get(int index) {

        ArrayNode<K, V> arrayNode = get(root, index);
        return arrayNode.k;
    }

    private ArrayNode<K, V> get(ArrayNode<K, V> cur, int index) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.left != null ? cur.left.size : 0;
        if (index < leftSize) {
            return get(cur.left, index);
        } else if (index == leftSize) {
            return cur;
        } else {
            return get(cur.right, index - leftSize - 1);
        }
    }

    private ArrayNode<K, V> add(ArrayNode<K, V> cur, int index, K k) {
        if (cur == null) {
            return new ArrayNode<>(k);
        }
        cur.size++;
        int leftSize = (cur.left != null ? cur.left.size : 0) + 1;
        // 如果index小于左边的size，就往左边插入
        // 一个节点的size是15的话 那么意味着该节点以及该节点的子树的所有孩子占据的位置是1-15
        // 假设cur.left.size=8意味着左树的节点占据的位置是1-8 cur节点占据的位置是9 cur.right的节点占据的位置是10-15
        if (index < leftSize) {
            cur.left = add(cur.left, index, k);
        } else {
            cur.right = add(cur.right, index - leftSize, k);
        }
        return maintain(cur);
    }

    private ArrayNode<K, V> maintain(ArrayNode<K, V> cur) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.left != null ? cur.left.size : 0;
        int rightSize = cur.right != null ? cur.right.size : 0;
        int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
        int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
        int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
        int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
        if (leftLeftSize > rightSize) {
            cur = rightRotate(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
            cur.left = leftRotate(cur.left);
            cur = rightRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (rightRightSize > leftSize) {
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        } else if (rightLeftSize > leftSize) {
            cur.right = rightRotate(cur.right);
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        return cur;
    }

    private ArrayNode<K, V> leftRotate(ArrayNode<K, V> cur) {
        if (cur == null) {
            return cur;
        }
        // 左旋当前节点右侧节点会上来
        ArrayNode rightNode = cur.right;
        cur.right = rightNode.left;
        rightNode.left = cur;
        rightNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return rightNode;
    }

    private ArrayNode<K, V> rightRotate(ArrayNode<K, V> cur) {
        if (cur == null) {
            return cur;
        }
        ArrayNode leftNode = cur.left;
        cur.left = leftNode.right;
        leftNode.right = cur;
        leftNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return leftNode;
    }

    public Integer size() {
        return root == null ? 0 : root.size;
    }
}


class ArrayNode<K extends Comparable<K>, V> {
    int size;

    K k;
    ArrayNode left;
    ArrayNode right;

    ArrayNode(K k) {
        this.k = k;
        size = 1;
    }

}
