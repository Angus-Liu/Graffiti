package sort;

/**
 * @author Angus
 * @date 2018/10/23
 */
@FunctionalInterface
public interface Sort<T extends Comparable<T>> {
    /**
     * 排序方法
     *
     * @param arr 待排序数组
     */
    void sort(T[] arr);
}
