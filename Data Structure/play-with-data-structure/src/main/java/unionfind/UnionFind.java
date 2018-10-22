package unionfind;

/**
 * @author Angus
 * @date 2018/10/20
 */
public interface UnionFind {

    int geiSize();

    /**
     * p,q 索引对应的两个元素是否相连
     *
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p, int q);

    /**
     * 将 p,q 索引对应的两个元素取并
     *
     * @param p
     * @param q
     */
    void unionElements(int p, int q);
}
