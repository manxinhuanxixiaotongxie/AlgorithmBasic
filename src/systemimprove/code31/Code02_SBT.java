package systemimprove.code31;

public class Code02_SBT {
    public class SBTNode<k extends Comparable<k>,v> {
        public k key;
        public v value;
        public SBTNode<k,v> left;
        public SBTNode<k,v> right;
        public int size;

        SBTNode(k key,v value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    public class SBTTree<k extends Comparable<k>,v> {

        private SBTNode root;
        private int size;
        /**
         *
         * BST size balance tree
         * 特性：
         * 任何一个叔叔节点的数量不能比侄子节点要少
         * 与AVL树一样  有四种情形
         * LL  RR  LR  RL
         *
         * 旋转方式与AVL树一致  左旋 右旋
         *
         */

        // 左旋
        public SBTNode leftRotata(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            // 左旋 先找到右树节点作为返回值
            SBTNode right = cur.right;
            cur.right = right.left;
            right.left = cur;
            // 旋转之后调整size大小
            cur.size = cur.left.size + cur.right.size + 1;
            right.size = right.left.size + right.right.size + 1;
            return right;
        }

        // 右旋
        public SBTNode rightRotata(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            // 找到左节点作为的新的头结点
            SBTNode left = cur.left;
            cur.left = left.right;
            left.right = cur;
            // 旋转之后调整size大小
            cur.size = cur.left.size + cur.right.size + 1;
            left.size = left.left.size + left.right.size + 1;
            return cur;
        }

        private SBTNode maintein(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            int leftSize = cur.left == null ? 0 : cur.left.size;
            int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            int rightSize = cur.right == null ? 0 : cur.right.size;
            int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;

            // 根据SBT的定义 叔叔节点数量不能比侄子数量要小
            // 这里根据不同的情况进行调整
            // LL型 向右旋转 BST的过程其实跟AVL树的过程是一样的 只是AVL数调整结束之后 高度已经平衡了
            // 但是SBT调整结束之后 由于size的变化 需要将所有更换了左右孩子的树重新进行调平衡
            if (leftLeftSize > rightSize) {
                // 右旋 cur节点的右孩子发生了变化 并且cur自身的size也发生了变化
                cur = rightRotata(cur);
                cur.right = maintein(cur.right);
                cur = maintein(cur);
            } else if (leftRightSize > rightSize) {
                // LR型 先左旋再右旋
                cur.left = leftRotata(cur.left);
                cur = rightRotata(cur);
                cur.left = maintein(cur.left);
                cur.right = maintein(cur.right);
                cur = maintein(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotata(cur);
                cur.left = maintein(cur.left);
                cur = maintein(cur);
            } else if (rightLeftSize > leftSize) {
                cur.right = rightRotata(cur.right);
                cur = leftRotata(cur);
                cur.left = maintein(cur.left);
                cur.right = maintein(cur.right);
                cur = maintein(cur);
            }
            return cur;
        }

        public void put(k key,v value) {
            SBTNode<k,v> lastIndex = findLastIndex(key);
            if (lastIndex != null && key.compareTo(lastIndex.key) == 0) {
                lastIndex.value = value;
            }else {
                size++;
                root = add(root,key,value);
            }
        }

        private SBTNode add(SBTNode<k,v> cur,k key,v value) {
            if (cur == null) {
                return new SBTNode(key,value);
            } else {
                cur.size++;
                if (key.compareTo(cur.key) < 0) {
                    cur.left = add(cur.left,key,value);
                } else {
                    cur.right = add(cur.right,key,value);
                }
                return maintein(cur);
            }
        }

        /**
         * 找到是否存在节点 找到返回 找不到返回空
         * @param key
         * @return
         */
        public SBTNode contains(k key) {
            if (key == null) {
                return  null;
            }
            SBTNode<k,v> cur = root;
            SBTNode<k,v> ans = null;
            while (cur != null) {
                if (key.compareTo(cur.key) == 0) {
                    ans = cur;
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans;
        }

        /**
         * 如果存在 返回节点
         * 不存在返回叶子节点
         * @param key
         * @return
         */
        private SBTNode findLastIndex(k key) {
            SBTNode<k,v> ans = root;
            SBTNode<k,v> cur = root;
            while (cur != null) {
                ans = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                } else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return ans;
        }

        public boolean containsKey(k key) {
            SBTNode<k,v> cur = root;
            while (cur != null) {
                if (cur.key.compareTo(key) == 0) {
                    return true;
                }else if (cur.key.compareTo(key) < 0) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }

            }
            return false;
        }

        public void remove(k key) {
            if (key == null) {
                return;
            }
            size--;
            if (containsKey(key)) {
                root = delete(root, key);
            }
        }



        /**
         * 删除节点
         * @param cur
         * @param key
         * @return
         */
        private SBTNode delete(SBTNode<k,v> cur,k key) {
            cur.size--;
            if (key.compareTo(cur.key) < 0) {
                cur.left = delete(cur.left,key);
            } else if (key.compareTo(cur.key) > 0) {
                cur.right = delete(cur.right,key);
            } else {
                if (cur.left == null && cur.right == null) {
                    cur = null;
                } else if (cur.left == null && cur.right != null) {
                    cur = cur.right;
                } else if (cur.left != null && cur.right == null) {
                    cur = cur.left;
                } else {

                    SBTNode<k, v> pre = null;
                    SBTNode<k ,v> des = cur.right;
                    des.size--;
                    while (des.left != null) {
                        pre = des;
                        des = des.left;
                        des.size--;
                    }
                    // sbt删除节节点的时候  不需要进行平衡度的调整 在加入的时候 会进行调整
                    /**
                     * pre不为空 意味着右树的最左侧节点不是要删除节点的右孩子
                     * 那么 找到右树的最左侧节点之后  将最左侧节点的父节点同时找到
                     * 并且需要将最左侧节点得右树挂在最左节点的左树上
                     *
                     *
                     * 如果pre为空 说明cur.right就是删除节点右侧最左侧节点
                     * 直接使用该节点替换掉当前节点 该节点的右树不需要进行调整
                     */
                    if (pre != null) {
                        pre.left = des.right;
                        des.right = cur.right;
                    }
                    des.left = cur.left;
                    des.size = des.left.size + (des.right == null ? 0 : des.right.size) + 1;
                    // free cur memory -> C++
                    cur = des;
                }
            }
            if (cur != null) {
                cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            }
            // SBT删除可以不进行平衡性的调整
//            cur =  maintein(cur);
            return cur;
        }
    }
}
