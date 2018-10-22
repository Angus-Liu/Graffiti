package unionfind;

/**
 * 查询速度快的并查集实现
 *
 * @author Angus
 * @date 2018/10/20
 */
public class QuickFind implements UnionFind {


    private int[] id;

    public QuickFind(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            // 初始时每一个节点都处于一个独立的集合
            id[i] = i;
        }
    }

    @Override
    public int geiSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }
        // 将与 p 在同一集合的所有点改为与 q 同一集合
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    /**
     * 获取 p 节点所属的集合
     *
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound!");
        }
        return id[p];
    }
}
