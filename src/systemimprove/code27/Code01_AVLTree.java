package systemimprove.code27;

/**
 *
 * 所有的有序表结构必须要保证key是可以比较的
 */
public class Code01_AVLTree {
    /**
     * 数据库的索引
     * 为什么索引不能使用的hash表？
     * 1.哈希表是无序的
     * 2.哈希表不能解决范围查询的问题
     * 3.哈希表不能解决最大值最小值的问题
     * 4.哈希表不能解决前驱后继的问题
     *
     *
     * 可比较
     * 为什么可以使用索引加快查询的效率？
     * 有序表
     *
     * 维护一个数据结构可以快速实现增删改查
     * 以BST为例：
     *  BST特性 当前节点左边节点比当前节点小 右边节点比当前节点大
     * 1.新增节点
     *  给定一个的新的节点 如果比当前值小 那么就往左走 如果比当前值大 那么就往右走
     *  直到找到一个空的位置 就是新节点的位置
     * 2.查找节点
     *   也比较荣容易  给定一个值 从根节点开始比较 如果比当前节点小 那么就往左走 如果比当前节点大 那么就往右走
     *  直到找到一个节点的值等于给定值
     * 3.删除节点
     *   对于二叉搜索树来说 新增节点以及查找节点都是非常容易的
     *   但是对于删除节点来说就比较麻烦
     *   分四种情况：
     *   1.删除节点左右子树都为空 直接删除该节点
     *   2.删除节点只有一个子树 那么直接删除该节点 将子树挂到删除节点的位置
     *   3.删除节点有两个子树 那么找到删除节点的前驱节点或者后继节点 用前驱节点或者后继节点替换删除节点
     *   这点很关键  二叉搜索树的特性决定了前驱节点或者后继节点一定是删除节点的左子树的最大值或者右子树的最小值
     *
     *
     *   二叉搜索树的问题：
     *   树的形状跟用户输入的数据有关 最差的情况下可能会退化成链表 时间复杂度O(N)
     *
     *   AVL树
     *   注意：平衡二叉搜索树只是高度做了严格的限制 实际上还是二叉搜索树 调整完之后满足二叉搜索树的特性
     *   即左子树的值比父节点小 右子树的值比父节点大
     *   平衡二叉搜索树的特性： 左右子树的高度差不超过1 即 |左子树高度-右子树高度| < 2
     *   1.新增节点
     *   找到对应的位置 挂进去 但是要保证平衡 需要增加平衡的补丁
     *   2.删除节点
     *   3.查找节点
     *   查找节点
     *
     *   平衡的补丁是怎么维护的：
     *   1.左旋
     *   2.右旋
     *
     *   分为以下四种情况：
     *   1.LL
     *   这种情况下需要右旋
     *   2.LR
     *   将节点进行左旋 然后再进行右旋
     *   3.RR
     *   只需要左旋
     *   4.RL
     *   当前节点的树进行右旋 然后再进行左旋
     *
     *   一种特殊的情况：
     *   同时满足LL\LR 或者RR\RL
     *   LL\LR 进行右旋
     *   为什么要这么处理呢？
     *   举个例子：
     *   例子1：
     *                                                     （root）
     *                                (left1)7                                    (right1)6
     *                  (left2)6                (right2)6
     *                                    (left3)4  (right3)5
     *
     *   这种情况下不违规
     *   假设此时在右树上删除一个节点 此时树变成了以下这个样子：
     *                                                      （root）
     *                               (left1)7                                    (right1)5
     *                 (left2)6               (right2)6
     *                                   (left3)4    (right3)5
     *
     *    如果一棵树右边的数删除了一个 那个这棵树不满足平衡二叉搜索树的特性
     *    并且同时满足LL\LR
     *    这个时候只需要进行右旋 那么此时这棵树变成了下面这个样子
     *                                                      （left1）8
     *                                        (left2)6                          （root）7
     *                                                                   (right2)6      (right1)5
     *                                                            (left3)4   (right4)5
     *
     *已经是平衡的了
     *
     * 但是我们如果按照LR的方式进行处理的话：
     * 第一次 节点进行左旋的时候，这棵树变成了下面这个样子：
     *                                         （root） 8
     *                               （right2）7                       （right1）5
     *                        （left1）6      （right3）5
     *                   （left2）5  (left3)4
     *
     * 依然不平 按照LL 进行右旋 那么 这棵树就会变成下面这个样子：
     *                                                        （right2）8
     *                                        (left1)   6                             （root）7
     *                                   (left2)5    (left3) 4               (righ3)5     (right1)5
     *
     *
     *   RR\RL 进行左旋
     *
     *   平衡二叉搜索树执行严格的平衡性 对于每一个节点来说 左右子树的高度差不超过1
     *
     *   旋转的时间复杂度是O(1)
     *
     */

    public static class AVLNode<K extends Comparable<K>, V> {
        public K k;
        public V value;
        public AVLNode<K, V> left;
        public AVLNode<K, V> right;
        public int height;

        public AVLNode(K key, V value) {
            k = key;
            this.value = value;
            height = 1;
        }
    }

    public class AVLTree<K extends Comparable<K>, V> {
        private AVLNode root;
        private int size;

        public AVLTree() {
            this.root = null;
            this.size = 0;
        }

        /**
         * 左旋
         * 左旋的过程：给定一个节点cur进行左旋
         * 那么cur的有孩子会替代当前cur的位置
         * 并且cur节点会接管cur的右孩子的左孩子
         * cur会变成新节点的左孩子
         */
        private AVLNode leftRotate(AVLNode cur) {
            // 在当前节点进行左旋
            // 左旋的过程中就是将当前节点的右节点作为新的根节点
            AVLNode right = cur.right;
            // 当前节点cur节点与right节点乱开链接 并且将cur节点的有节点接管right的左节点
            cur.right = right.left;
            right.left = cur;
            // 要调整高度 当前节点的高度怎么计算 如果cur本身是有left节点的话 那么cur节点的高度就是cur.left 与cur.right的最大值
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height,cur.right == null ? 0 : cur.right.height) + 1;
            // right节点的高度也要调整 调整之后的高度为cur节点的高度与right节点的右节点的高度的最大值
            right.height = Math.max(right.left == null ? 0 : right.left.height,right.right == null ? 0 : right.right.height) + 1;
            return right;
        }

        /**
         * 右旋
         * 右旋的过程：给定一个节点cur进行右旋
         * 那么cur的左孩子会替代当前cur的位置
         * cur节点会接管cur的左孩子的右孩子
         * cur会变成新节点的右孩子
         * @param cur
         * @return
         */
        private AVLNode rightRotate(AVLNode cur) {
            AVLNode left = cur.left;
            // cur节点与left节点断开连接 并且cur节点接管left的右孩子
            cur.left = left.right;
            left.right = cur;
            // 右旋调整高度
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height,cur.right == null ? 0 : cur.right.height) + 1;
            left.height = Math.max(left.left == null ? 0 : left.left.height,left.right == null ? 0 : left.right.height) + 1;
            return left;
        }

        /**
         * 新增节点
         */
        public AVLNode add(AVLNode cur, K key, V value) {
            /**
             * 新增节点 先将节点放在对应的位置 按照二叉搜索树的特性
             * 放进去之后再进行AVL的补丁进行调整
             */
            if (cur == null) {
                // 如果cur已经来到了空位置 那么意味着 已经来到了可以放节点位置的地方
                AVLNode node = new AVLNode(key,value);
                size++;
                return node;
            }
            if (cur.k.compareTo(key) < 0) {
                // 如果当前节点的key比要插入的key小 那么就往右走
                cur.right = add(cur.right, key, value);
            } else if (cur.k.compareTo(key) > 0) {
                // 如果当前节点的key比要插入的key大 那么就往左走
                cur.left = add(cur.left, key, value);
            } else {
                // 如果当前节点的key等于要插入的key 那么就更新当前节点的value
                cur.value = value;
            }
            // 更新当前节点的高度
            cur.height = Math.max(cur.left == null ? 0 : cur.left.height,cur.right == null ? 0 : cur.right.height) + 1;
            // 维护平衡性
            return maintain(cur);
        }

        /**
         * 维护平衡性
         *
         * avl树的平衡性维护 严格遵循左右子树的高度差不超过1
         * @param cur
         * @return
         */
        public AVLNode maintain(AVLNode cur) {
            if (cur == null) {
                return null;
            }
            int leftHeight = cur.left == null ? 0 : cur.left.height;
            int rightHeight = cur.right == null ? 0 : cur.right.height;
            // 左子树的高度比右子树的高度大于1
            // 分四种情况进行旋转
            /**
             * LL 进行一次右旋
             * LR 左子树进行一次左旋 当前节点进行右旋
             * RR 当前节点进行一次左旋
             * RL 右子树进行一次右旋 当前节点进行左旋
             */
            if (Math.abs(leftHeight - rightHeight) > 1) {
                // 左子树的高度大于右子树的高度
                if (leftHeight > rightHeight) {
                    /**
                     * 如果是左树的高度比右树的高度大1 那么可能有以下两种情况
                     * 1.LL型
                     * 比如下面的这个例子：
                     *                         （cur）
                     *              left 7                  right 5
                     *      leftleft 6  leftright 5
                     * 在这个例子中 违反高度定义的就是LL型
                     * 那么需要将cur进行右旋
                     * 2.LR型
                     *   比如这个例子：
                     *                                                 （cur）
                     *                                    left 7                  right 5
                     *                            leftleft 6  leftright 6
                     * 那么这个例子就是LR型
                     * 这个时候同样进行右旋
                     */
                    // 左树左树的高度 左树右树的高度 用于确认是LL型还是LR型
                    /**
                     * 现在已经明确是左树违规 要确认是LL型违规还是LR型违规
                     * 要找到左树的左树的高度 以及左树的右树的高度
                     * 左树的左树的高度：必须要保证左树不为空以及左树的左树不为空
                     * 左树的右树的高度：必须要保证左树不为空以及左树的右树不为空
                     */
                    int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
                    int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
                    // 如果是左的左树比左树的右树高的话 那么就是LL型
                    // 如果是左树的左树的高度与左树的右树的高度相等的话 那么就是同时满足LL LR型 这个时候进行一次右旋即可
                    if (leftLeftHeight >= leftRightHeight) {
                        // LL型或者是LL与LR同时满足的类型 进行一次的右旋
                        cur = rightRotate(cur);
                    }else {
                        // 仅仅是满足LR型的情况 需要进行两次旋转
                        // 将左树进行左旋
                        cur.left = leftRotate(cur.left);
                        // 将cur进行右旋
                        cur =  rightRotate(cur);
                    }
                } else {
                    // 右子树的高度大于左子树的高度
                    // 已经明确是右树违规
                    // 要确认是RL型还是RR型
                    int rightLeftHeight = cur.right != null && cur.right.left != null? cur.right.left.height : 0;
                    int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
                    // 现在需要确认的是 到底是RL型违规 还是RR型违规
                    if (rightRightHeight >= rightLeftHeight) {
                        // RR型违规 或者是RL/RR型同时违规
                        // 只需要进行一次左旋
                        cur = leftRotate(cur);
                    } else {
                        // RL型违规 需要进行两次旋转
                        // 将右子树进行右旋
                        cur.right = rightRotate(cur.right);
                        // 将cur进行左旋
                        cur = leftRotate(cur);
                    }

                }
            }
            return cur;
        }

        /**
         * 删除cur node
         * 删除节点需要先找到对应的节点
         * 需要注意的是，对于搜索二叉树来说 删除节点是一件比较麻烦的事情
         * 1.分为四种情况
         * 第一种：左树与右树都为空 那么直接删除节点就可以
         * 第二种：左树不为空 右树为空 那么使用左树的环境替代当前节点的环境
         * 第三种：右树不为空 左树为空 那么使用右树的环境替代当前节点的环境
         * 第四种：左树与右树都不为空 为了保持 搜索树的特性 需要找到当前节点的前驱节点或者后继节点
         * 前驱节点：左树的最右侧节点
         * 后继节点：右树的最左侧节点
         *
         *
         * 对于AVL树来说 删除节点也需要维持平衡性
         * 因此维护平衡性的过程也是比较麻烦的
         * 针对第一种情况 左树右树都没有的情况下 当前节点删除   从这个节点开始一直到父节点都需要进行AVL树的调整
         * 针对第二种情况 左树不为空 右树不为空的情况下 使用左子树替代当前节点的环境 那么就需要左子树的头结点出发一直向上到父节点进行平衡性的调整
         * 针对第三种情况：右树不为空 左树为空的情况下 使用右子树替代当前节点的环境 那么就需要右子树的头结点出发一直向上到父节点进行平衡性的调整
         * 针对第四种情况：左树与右树都不为空的情况下 需要找到当前节点的前驱节点或者后继节点 使用前驱节点或者后继节点替换当前节点的环境 这时候
         * 需要注意，进行平衡性的调整需要从前驱结点或者后继节点开始向上调整一直到根节点
         *
         * @param cur
         */
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K key) {
            if (cur == null) {
                return null;
            }

            if (key.compareTo(cur.k) < 0) {
                cur.left = delete(cur.left, key);
            } else if (key.compareTo(cur.k) > 0) {
                cur.right = delete(cur.right, key);
            } else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else if (cur.right != null && cur.left == null) {
                    cur = cur.right;
                } else {
                    // 左右都不为空
                    // 找到前驱节点或者后继节点
                    // 这里实现的方式是找到后继节点
                    AVLNode<K, V> des = cur.right;
                    while (des.left != null) {
                        des = des.left;
                    }
                    // 如果要删除一个节点  左孩子右孩子都存在 那么需要找到右孩子的后继节点
                    // 使用后继节点替换当前节点 并且在在向上进行平衡度调整的过程中 使用后继节点的index位置的节点进行向上调整 一直到根节点

                    // 这一步的动作是在右树上删除后继节点 并且返回后继节点 将当前节点的右孩子替换为后继节点
                    // 这也是为什么要讲删除函数设计成带有返回值
                    // 这里很重要 为什么要这么处理
                    // 我们现在实现一个功能是什么呢？ 在删除一个左右节点都存在的节点的时候 我们需要在des的位置
                    // 向上查找直到到达跟节点 压栈 当前节点的右树进行删除右树的最左侧节点
                    // 这个递归的作用就是在des的位置向上查找直到根节点 在右树上删除右树的最左侧节点
                    // 当递归到要删除节点的时候 这行代码应该要返回的是要删除的后继节点
                    //
                    cur.right = delete(cur.right, des.k);
                    // 将当前节点的左树与cur断开链接 后继节点的左孩子接管cur的左孩子
                    des.left = cur.left;
                    // 将当前节点的右树与cur断开链接 后继节点的右孩子接管cur的右孩子
                    des.right = cur.right;
                    // 将des替换cur
                    cur = des;
                }

            }
            // 替换之前 cur的高度要进行更新
            if (cur != null) {
                cur.height = Math.max(cur.left == null ? 0 : cur.left.height, cur.right == null ? 0 : cur.right.height) + 1;
            }
            return maintain(cur);
        }

        public void remove(K key) {
            if (key == null) {
                return;
            }
            if (contains(root,key)) {
                size--;
                root = delete(root,key);
            }
        }

        /**
         * 在cur这棵树找到是否存在key
         * @param cur
         * @return
         */
        public boolean contains(AVLNode cur, K key) {
            if (cur == null) {
                return false;
            }
            if (cur.k.compareTo(key) < 0) {
                return contains(cur.right,key);
            } else if (cur.k.compareTo(key) > 0) {
                return contains(cur.left,key);
            } else {
                return true;
            }
        }

    }



}
