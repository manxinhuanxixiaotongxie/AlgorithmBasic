package systemimprove.code32;

import java.util.ArrayList;

/**
 * 如果是一个标准的数组 类似java这些语言的数组
 * 是一个干净的数组的话  如果我们需要在插入数据 尤其是在指定的位置加入数据
 * 比如一个数组是[1,2,3,4,5] 我们需要在2和3之间加入一个数据
 * 我们在2的位置加入一个数据 2后面的数据都要往后移动一位 这个时间复杂度是O(N)
 * <p>
 * 要删除2位置的数据 2后面的数据都要往前移动一位 这个时间复杂度是O(N)
 * <p>
 * 使用平衡二叉搜索树的话 插入和删除的时间复杂度是O(logN)
 * 怎么实现呢？用平衡二叉搜索树或者SBT
 * 本文件采用SBT实现
 * 1.在index位置加入一个数据
 * 2.删除index位置的数
 * 3.二叉树能接受重复的数据
 * <p>
 * 新增一个辅助的all字段   平衡因子怎么定义呢
 */
public class Code03_ArrayImpl {

    public static void main(String[] args) {

        int test = 50000;
        int max = 1000000;
        boolean pass = true;
        ArrayList<Integer> list = new ArrayList<>();
        ArrayListImplByTree<Integer> sbtList = new ArrayListImplByTree<>();
        for (int i = 0; i < test; i++) {
            if (list.size() != sbtList.size) {
                pass = false;
                break;
            }
            if (list.size() > 1 && Math.random() < 0.5) {
                int removeIndex = (int) (Math.random() * list.size());
                list.remove(removeIndex);
                sbtList.remove(removeIndex);
            } else {
                int randomIndex = (int) (Math.random() * (list.size() + 1));
                int randomValue = (int) (Math.random() * (max + 1));
                list.add(randomIndex, randomValue);
                sbtList.put(randomIndex, randomValue);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(sbtList.get(i))) {
                pass = false;
                break;
            }
        }
        System.out.println("功能测试是否通过 : " + pass);

        // 性能测试
        test = 500000;
        list = new ArrayList<>();
        sbtList = new ArrayListImplByTree<>();
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
            int randomIndex = (int) (Math.random() * (sbtList.size + 1));
            int randomValue = (int) (Math.random() * (max + 1));
            sbtList.put(randomIndex, randomValue);
        }
        end = System.currentTimeMillis();
        System.out.println("SBT插入总时长(毫秒) ： " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * sbtList.size);
            sbtList.get(randomIndex);
        }
        end = System.currentTimeMillis();
        System.out.println("SBT读取总时长(毫秒) : " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < test; i++) {
            int randomIndex = (int) (Math.random() * (sbtList.size + 1));
            sbtList.remove(randomIndex);
        }
        end = System.currentTimeMillis();

        System.out.println("SBT删除总时长(毫秒) : " + (end - start));


    }

}

class ArrayListImplByTree<V> {
    private ArrayNode<V> root;
    public int size;

    private ArrayNode<V> leftRotate(ArrayNode<V> cur) {
        ArrayNode<V> right = cur.right;
        cur.right = right.left;
        right.left = cur;
        right.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return right;
    }

    private ArrayNode<V> rightRotate(ArrayNode<V> cur) {
        ArrayNode<V> left = cur.left;
        cur.left = left.right;
        left.right = cur;
        left.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return left;
    }

    public V get(int index) {
        ArrayNode<V> arrayNode = get(root, index);
        return arrayNode.value;
    }

    /**
     * 获取index位置的数据  在数据中 如果数组是设计从0开始 那么获取index位置数 其实就是获取size是index+1的数
     *
     * @param cur
     * @param index
     * @return
     */
    public ArrayNode<V> get(ArrayNode<V> cur, int index) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.left != null ? cur.left.size : 0;
        if (index < leftSize) {
            return get(cur.left, index);
        } else if (index > leftSize) {
            // 左树占据的位置是0-cur.left.size-1 当前节点占据的位置是cur.left.size 右树占据的节点就是cur.size - cur.left.size - 1
            return get(cur.right, index - leftSize - 1);
        } else {
            return cur;
        }
    }

    /**
     * 在index位置加入一个数据
     */
    public void put(int index, V value) {
        ArrayNode<V> cur = new ArrayNode<>(value);
        root = add(root, index, cur);
        size++;
    }

    /**
     * index是放在数组的位置
     * <p>
     * 在这棵树中 左树占据的下标的位置是0-cur,left.size-1 当前节点占据的位置是cur.left.size
     *
     * @param cur
     * @param index
     * @return
     */
    public ArrayNode<V> add(ArrayNode<V> cur, int index, ArrayNode<V> node) {

        // 要实现的功能是在index位置加入一个数据 那么在这之后的数据都会向右移动
        // 在平衡树的移动过程中 占据的index位置并不会因此发生变化
        // 其中size不仅仅是平衡因子 而且是数组的位置
        if (cur == null) {
            return node;
        }
        cur.size++;
        // size==1 表示占据的是是0位置 如果我们要在index等于0的位置加入一个数据的话 需要将新增数据的节点放在当前节点的左树 当前节点的size++
        // 此时 左树的size变成了1 占据0位置
        int leftSize = (cur.left != null ? cur.left.size : 0) + 1;
        //
        if (index < leftSize) {
            cur.left = add(cur.left, index, node);
        } else {
            // 在右树上找到第几个节点 举个例子
            /**
             * 如果树长这个样子
             *            a
             *          b  c
             *          这个树的index从1开始   1位置放的是b 2位置放的是a 3位置放的是c
             *          如果我们要在i位置放置一个数d 怎么区分向左走还是向右走呢
             *          左树占用的位置是1-left.size 我们要在index位置加入一个新的点
             *          如果index等于left.size 当前位置及其之后的位置都会被向后移动
             *                    8
             *          4                       9
             *       2      6              10         2
             *     1  3  5     7        2    2    2    2
             *
             *
             *
             *
             */
            cur.right = add(cur.right, index - leftSize, node);
        }
        return maintain(cur);
    }

    private ArrayNode<V> maintain(ArrayNode<V> cur) {
        if (cur == null) {
            return cur;
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

    public void remove(int index) {
        root = delete(root, index);
        size--;
    }

    public ArrayNode<V> delete(ArrayNode<V> cur, int index) {
        if (cur == null) {
            return null;
        }
        cur.size--;
        int leftAndHeadSize = (cur.left != null ? cur.left.size : 0);
        /**
         * 删除是怎么处理的呢
         * 比如要删除的的index位置的数 那么找的就是index+1的位置
         * 左树占据的位置是0-cur.left.size-1 当前节点占据的位置是cur.left.size 右树占据的节点就是cur.size - cur.left.size - 1
         */
        if (index != leftAndHeadSize) {
            if (index < leftAndHeadSize) {
                cur.left = delete(cur.left, index);
            } else {
                cur.right = delete(cur.right, index - leftAndHeadSize - 1);
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
                ArrayNode<V> pre = null;
                ArrayNode<V> des = cur.right;
                des.size--;
                while (des.left != null) {
                    pre = des;
                    des = des.left;
                    des.size--;
                }
                if (pre != null) {
                    pre.left = des.right;
                    des.right = cur.right;
                }
                des.left = cur.left;
                des.size = des.left.size + (des.right != null ? des.right.size : 0) + 1;
                cur = des;
            }
        }
        return cur;

    }
}


class ArrayNode<V> {

    // 数组的值
    V value;

    // 平衡因子
    int size;

    ArrayNode<V> left;

    ArrayNode<V> right;

    /**
     * 加入数组的index以及value
     *
     * @param value
     */
    ArrayNode(V value) {
        this.value = value;
        size = 1;
    }
}
