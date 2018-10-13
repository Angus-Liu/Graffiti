package heap;

import array.Array;

/**
 * 最大堆的简单实现（借助动态数组实现）
 *
 * @author Angus
 * @date 2018/10/11
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        // 将数组转换为最大堆
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            // 从最后一个不为叶子的节点开始做下浮操作，使数组中每个元素满足堆的性质
            siftDown(i);
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中欧冠，一个索引所表示的元素的父亲节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent!");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        int left = index * 2 + 1;
//        if (left >= size()) {
//            throw new IllegalArgumentException("index-" + index + " doesn't have left child!");
//        }
        return left;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        int right = index * 2 + 2;
//        if (right >= size()) {
//            throw new IllegalArgumentException("index-" + index + " doesn't have right child!");
//        }
        return right;
    }

    /**
     * 向堆中添加数据
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 根据堆的性质，父节点要大于子节点，所以每次向堆中添加元素后，需要根据情况上浮添加后的元素
     *
     * @param k 待上浮元素索引
     */
    private void siftUp(int k) {
        // 未达到根节点，且父节点比待上浮节点的元素小
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // 交换 k 与其 parent
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 查看堆中最大元素
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalStateException("Can not run findMax when heap is empty!");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大元素
     *
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        // 将最大元素（堆中第一个元素）与堆中最后一个元素交换位置，删除最后一个元素（原最大元素）
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        // 根据最大堆的性质，此时可能需要下浮第一个元素
        siftDown(0);
        return ret;
    }

    /**
     * 根据堆的性质，父节点要大于子节点，所以每次向堆中删除堆顶元素后，需要根据情况下浮新的堆顶元素（原堆尾元素）
     *
     * @param k
     */
    private void siftDown(int k) {
        // 当 k 不可再下浮时停止循环
        while (leftChild(k) < data.getSize()) {
            // 索引 j 存储的是左右孩子的中元素较大的对应的索引
            int j = leftChild(k);
            // 右孩子存在，且值比左孩子大
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = j + 1;
            }
            // data[j] 是 leftChild 和 rightChild 中的较大值
            if (data.get(k).compareTo(data.get(j)) < 0) {
                data.swap(k, j);
                // 交换后，记得更新 k 的值
                k = j;
            } else {
                break;
            }
        }
    }

    /**
     * 取出堆中最大元素，并且替换成元素 e
     *
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        // 堆顶元素替换为新元素
        data.set(0, e);
        // 将新的堆顶元素下浮
        siftDown(0);
        return ret;
    }


}
