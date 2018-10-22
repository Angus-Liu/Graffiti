package queue;

/**
 * 基于链表的队列简单实现
 * @author Angus
 * @date 2018/10/9
 */
public class LinkedListQueue<E> implements Queue<E> {

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
     * 头结点和尾节点
     */
    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
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
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            // 插入第一个节点时，不要忘记维护 head
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue from an empty queue!");
        }
        Node retNode = head;
        head = head.next;
        retNode.next = null;
        // 判断此时链表是否为空，修改 tail 的引用
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue from an empty queue!");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("queue: front ");
        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }
}
