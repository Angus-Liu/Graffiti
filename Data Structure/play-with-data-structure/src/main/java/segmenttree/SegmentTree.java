package segmenttree;

/**
 * 线段树（区间树）的简单实现
 *
 * @author Angus
 * @date 2018/10/13
 */
public class SegmentTree<E> {

    /**
     * 线段树对应的数组
     */
    private E[] tree;

    /**
     * 用户传入数据的一个副本
     */
    private E[] data;

    /**
     * 融合器用于融合左右孩子得到父节点的值
     */
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        // 获得用户传入数据的副本
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        /**
         * 假设区间有 n 个元素，
         * 如果 n = 2^k，则只需要大概 2n 的空间
         * 如果 n = 2^k + 1，则需要大概 4n 的空间，比上一种情况再多一行叶子节点（空间浪费）
         */
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在 treeIndex 的位置创建表示区间[l, r]的线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        // 递归退出条件
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        // 计算出左右孩子所在的索引
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        // 直接写为 (l + r) / 2 ，l + r 的值可能会发生溢出
        int mid = l + (r - l) / 2;
        // 递归创建左子树根节点对应的线段树
        buildSegmentTree(leftTreeIndex, l, mid);
        // 递归创建右子树根节点对应的线段树（注意此时的 l 为 mid + 1）
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        // 综合左右子树获得根节点的值（与业务相关）
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    /**
     * 返回区间 [queryL, queryR] 的值
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0
                || queryL >= data.length
                || queryR < 0
                || queryR > data.length
                || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以 treeIndex 为根，[l, r] 范围的线段树中，搜索区间 [queryL, queryR] 的值
     *
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        // 退出条件为搜索范围即为该线段树范围
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }
        // 根据 [queryL, queryR] 区间范围开始递归
        int mid = l + (r - l) / 2;
        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            // 搜索范围在改线段树的右子树中
            return query(rightIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            // 搜索范围在改线段树的左子树中
            return query(leftIndex, l, mid, queryL, queryR);
        }
        // 搜索范围同时在该线段树的左右子树中
        E leftResult = query(leftIndex, l, mid, queryL, mid);
        E rightResult = query(rightIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    /**
     * 将 index 位置的值，更新为 e
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在以 treeIndex 为根的线段树中更新 index 的值为 e
     *
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index <= mid) {
            // 待更新节点在左子树中
            set(leftTreeIndex, l, mid, index, e);
        } else {
            // 待更新节点在右子树中
            set(rightTreeIndex, mid + 1, r, index, e);
        }
        // 更新该节点的值
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
