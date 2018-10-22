package unionfind;

/**
 * 并速度快的并查集
 *
 * @author Angus
 * @date 2018/10/20
 */
public class QuickUnion implements UnionFind {

    private int[] parent;

    public QuickUnion(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            // 初始时每个节点的父节点都是自己
            parent[i] = i;
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
        parent[pRoot] = qRoot;
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

        while(p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
