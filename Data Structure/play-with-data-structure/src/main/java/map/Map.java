package map;

/**
 * @author Angus
 * @date 2018/10/10
 */
public interface Map<K, V> {
    /**
     * 添加键值对
     *
     * @param key
     * @param value
     */
    void add(K key, V value);

    /**
     * 移除 key 对应的键值对，返回 value
     *
     * @param key
     * @return
     */
    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V value);

    int getSize();

    boolean isEmpty();
}
