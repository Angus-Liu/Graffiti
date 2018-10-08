package queue;

/**
 * @author Angus
 * @date 2018/10/8
 */
public interface Queue<E> {
    /**
     * 获取队列大小
     *
     * @return
     */
    int getSize();

    /**
     * 判断队列是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 入队
     *
     * @param e
     */
    void enqueue(E e);

    /**
     * 出队
     *
     * @return
     */
    E dequeue();

    /**
     * 获取队首元素
     *
     * @return
     */
    E getFront();
}
