package sort;

/**
 * 选择排序
 *
 * @author Angus
 * @date 2018/10/23
 */
public class SelectionSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 保留每一次遍历时的最小值对应的索引
            int minIndex = i;
            // 遍历得到当前未排序中的最小值所在索引
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            // 将最小值与当前值交换位置
            SortTestHelper.swap(arr, i, minIndex);
        }
    }
}
