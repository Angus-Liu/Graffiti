package array;

/**
 * 动态数组的简单实现
 *
 * @author Angus
 * @date 2018/10/7
 */
public class Array<E> {

    private E[] data;
    /**
     * 数组实际大小
     */
    private int size;

    private static int defaultCapacity = 10;

    /**
     * 含参构造函数，传入的容量构造 array
     *
     * @param capacity
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 无参构造函数，默认容量构造 array
     */
    public Array() {
        this(defaultCapacity);
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 返回数组中元素的个数
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     *
     * @return
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在指定索引处插入新元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        // 判断索引是否合法
        // 索引不能为负数，且不能大于 size，以防数组中元素不紧密
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Require index >= 0 & index <= size!");
        }
        // 判断数组是否还剩余空间，将数组进行扩容
        if (size == data.length) {
            resize(2 * data.length);
        }
        // 将 [index, size) 之间的元素依次往后移动
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        // 在 index 处插入元素
        data[index] = e;
        size++;
    }

    /**
     * 在头添加新元素
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 向尾添加新元素
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
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        return data[index];
    }

    /**
     * 获取数组中第一个元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获取数组中最后一个元素
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
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否包含元素 e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素 e 所在的索引，不存在则返回 -1
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除指定索引处的元素，返回被删除的元素值
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        E ret = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        // loitering objects != memory leak
        data[size] = null;
        // size 小于当前容量的四分之一时才将数组缩小一半（不要在小于一半时就减半，防止复杂度震荡），减少空间浪费
        if (size <= data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除数组中第一个元素
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除数组中最后一个元素
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 如果数组中存在指定元素 e，就删除
     *
     * @param e
     * @return
     */
    public boolean removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * 数组容量调整
     *
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 交换索引 i 和索引 j 处的元素
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        if(i < 0 || i >= size || j < 0 ||j >= size) {
            throw new IllegalArgumentException("Index is illegal!");
        }
        E temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
