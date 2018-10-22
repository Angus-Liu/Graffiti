package queue;

/**
 * 循环队列的简单实现
 *
 * @author Angus
 * @date 2018/10/8
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    /**
     * 队首和队尾
     */
    private int front, tail;
    /**
     * 队列元素个数
     */
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public LoopQueue() {
        this(DEFAULT_CAPACITY);
    }

    public LoopQueue(int capacity) {
        // 比用户传入的 capacity 多 1，用以区别队列为空和队列满的情况
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        // 判断队列是否是满的，进行数组的扩容操作
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        // 此时为循环队列，注意 tail 的计算
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue from an empty queue!");
        }
        E ret = data[front];
        data[front] = null;
        // 此时为循环队列，注意 front 的计算
        front = (front + 1) % data.length;
        size--;
        // 判断队列的大小，进行数组的缩容操作
        if (getSize() <= getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot getFront from an empty queue!");
        }
        return data[front];
    }

    public int getCapacity() {
        // 实际容量应该除去有意空出的一个（不能存放元素）
        return data.length - 1;
    }

    /**
     * 数组容量调整
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // 将原循环队列中的数据放到新数组中，并将front置0
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("queue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        // 与 resize 稍有区别的遍历方法，效果相同
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if (i + 1 != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
