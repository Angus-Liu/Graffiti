package queue;

import heap.MaxHeap;

/**
 * 基于最大堆实现的优先队列
 *
 * @author Angus
 * @date 2018/10/12
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {
    /**
     * 最大堆
     */
    private MaxHeap<E> maxHeap;

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
