package linkedlist;

/**
 * 链表的简单实现
 *
 * @author Angus
 * @date 2018/10/8
 */
public class LinkedList<E> {

    /**
     * 节点用私有内部类实现
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    /**
     * 设置虚拟头节点，统一操作逻辑
     */
    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 获取链表中元素个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回链表是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在指定索引处（链表中）添加新元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed, Illefal index.");
        }
        // 遍历列表，找到插入处的前驱节点（不计 dummyHead）
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 插入新节点
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头添加元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表尾添加元素
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取指定索引处的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        // 注意 index 边界与插入时不同
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, Illefal index.");
        }
        // 排除虚拟头节点
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取链表的第一个元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获得链表的最后一个元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改指定索引处的元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        // 注意 index 边界与插入时不同
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed, Illefal index.");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 查找链表中是否含有元素 e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除指定索引处的节点
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed, Illefal index.");
        }

        Node prev = dummyHead;
        // 遍历找到待删除节点的前一个节点
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    /**
     * 删除第一个节点
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个节点
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElements(E e) {
        dummyHead.next = removeElements(dummyHead.next, e);
    }

    private Node removeElements(Node node, E e) {
        // 考虑问题规模最小的情况（递归退出条件）
        if (node == null) {
            return null;
        }
        // 减小问题规模
        node.next = removeElements(node.next, e);
        // 判断 node 节点是否需要删除
        return node.e.equals(e) ? node.next : node;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
