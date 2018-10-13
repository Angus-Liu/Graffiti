package map;

/**
 * 基于链表实现的映射
 *
 * @author Angus
 * @date 2018/10/10
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    /**
     * 虚拟头节点
     */
    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 返回 key 所在的节点
     *
     * @param key
     * @return
     */
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        // 映射中不允许 key 相同
        if (node == null) {
            // 在链表头头（虚拟头节点之后）插入新的键值对
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            // 将已有键值对的 value 进行更新
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        // 找到待删除的节点的前驱节点
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }
        // 删除节点
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist！");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
