package leetcode;

public class Code73 {

    public static void main(String[] args) {
        Code73 code73 = new Code73();
        int[][] matrix = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        code73.setZeroes(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length;j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。
     * 请使用原地算法。
     *
     * 思路：
     * 设置一个标记数组跟原数组 一样   如原有数据是0的话  标记为未结算
     * 按照行列进行遍历 如果当前位置是0 并且没有被处理过 那么进行结算  将与当前位置同行同列的位置都设置为0 但是注意 原有已经是0的不参与结算
     *
     * 这个题目不需要使用并查集 没有效果
     * 并查集最大的作用是解决连通性问题
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        UnionSet unionSet = new UnionSet(matrix);
        boolean[][] help = new boolean[matrix.length][matrix[0].length];
        // 行
        for (int i = 0; i < matrix.length;i++) {
            // 列
            for (int j = 0;j < matrix[0].length;j++) {
                // 如果当前位置是0 并且没有被处理过
                if (matrix[i][j]==0 && !help[i][j]) {
                    // 列
                    for (int k = 0; k < matrix[0].length;k++) {
                        if (matrix[i][k] == 0 && !help[i][k]) {
                            unionSet.union(i,k,i,j);
                        } else {
                            matrix[i][k] = 0;
                            help[i][k] = true;
                        }
                    }
                    // 列
                    for (int k = 0; k < matrix.length;k++) {
                        if (matrix[k][j] == 0 && !help[k][j]) {
                            unionSet.union(k,j,i,j);
                        } else {
                            matrix[k][j] = 0;
                            help[k][j] = true;
                        }
                    }

                }
            }
        }

    }


}

class UnionSet {
    private int[] parents;
    private int[] sizes;
    private int[] help;
    private int colomn;
    UnionSet(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        colomn = n;
        parents = new int[m * n];
        sizes = new int[m * n];
        help = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }

    }

    public void makeSet(int i, int j) {
        int index = index(i, j);
        parents[index] = index;
        sizes[index] = 1;
    }

    public boolean isSameSet(int i1,int j1,int i2,int j2) {
        return findFather(i1,j1) == findFather(i2,j2);
    }

    // 找到父节点
    public int findFather(int i,int j) {
        int index = index(i,j);
        int hi = 0;
        // 向上遍历 一直找到父节点为止
        while (index != parents[index]) {
            help[hi++] = index;
            index = parents[index];
        }
        // 辅助数据的作用是 在向上找到父节点的过程中 把沿途的节点指向父节点
        // 从而使得整个并查集变得更佳扁平
        // 此时index==parents[index]意味着index已经来到了父节点
        for (int k = 0; k < hi;k++) {
            parents[help[k]] = index;
        }
        return index;

    }

    // 合并两个集合
    public void union(int i1,int j1,int i2,int j2) {
        int index1 = index(i1,j1);
        int index2 = index(i2,j2);
        if (findFather(i1,j1) != findFather(i2,j2)) {
            int big = sizes[index1] >= sizes[index2] ? index1 : index2;
            int small = big == index1 ? index2 : index1;
            parents[small] = big;
            sizes[big] = sizes[index1] + sizes[index2];
        }

    }

    public int index(int i, int j) {
        return i * colomn + j;
    }
}
