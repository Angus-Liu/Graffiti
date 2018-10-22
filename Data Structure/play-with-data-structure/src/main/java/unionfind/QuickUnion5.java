package unionfind;

/**
 * 第 3 版基础上添加路径压缩（递归实现，深度压缩），进一步优化
 *
 * @author Angus
 * @date 2018/10/20
 */
public class QuickUnion5 implements UnionFind{
    private int[] parent;

    /**
     * rank[i] 表示以 i 为根的集合中所表示的树的层数（深度）
     */
    private int[] rank;

    public QuickUnion5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            // 初始时每个节点的父节点都是自己，每个节点所在的集合的树的层数都是 1
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int geiSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        // p 所在根节点指向 q 所在根节点
        if (rank[pRoot] < rank[qRoot]) {
            // qRoot 深度不变，rank[qRoot] 不用更新
            parent[pRoot] = qRoot;
        } else if (rank[qRoot] < rank[pRoot]) {
            // pRoot 深度不变，rank[pRoot] 不用更新
            parent[qRoot] = pRoot;
        } else {
            // rank[pRoot] = rank[qRoot]
            parent[pRoot] = qRoot;
            // 更新 rank
            rank[qRoot] += 1;
        }
    }

    /**
     * 获取 p 节点所在的根节点
     * O(h) 复杂度，h 为树的高度
     *
     * @param p
     * @return
     */
    private int find(int p) {

        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound!");
        }

        if (p != parent[p]) {
           parent[p] = find(parent[p]);
        }
        return parent[p];
    }
}
