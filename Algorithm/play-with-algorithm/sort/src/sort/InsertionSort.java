package sort;

/**
 * 插入排序
 *
 * @author Angus
 * @date 2018/10/23
 */
public class InsertionSort<T extends Comparable<T>> implements Sort<T> {
    @Override
    public void sort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 寻找元素 arr[i] 合适的插入位置
            T e = arr[i];
            int j;
            // 插入排序在数据较为有序的情况下效率极高
            for (j = i; j > 0 && e.compareTo(arr[j - 1]) < 0; j--) {
                // 这一步的作用在于，把比 e 大的数字依次后移一位
                arr[j] = arr[j - 1];
            }
            // 将待排序元素插入合适位置
            arr[j] = e;
        }
    }

    /**
     * 对 arr[l...r]范围的数组进行插入排序
     *
     * @param arr 待排序数组
     * @param l   左边界（含）
     * @param r   右边界（含）
     */
    public void sort(T[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            // 寻找元素 arr[i] 合适的插入位置
            T e = arr[i];
            int j;
            // 插入排序在数据较为有序的情况下效率极高
            for (j = i; j > 0 && e.compareTo(arr[j - 1]) < 0; j--) {
                // 这一步的作用在于，把比 e 大的数字依次后移一位
                arr[j] = arr[j - 1];
            }
            // 将待排序元素插入合适位置
            arr[j] = e;
        }
    }
}
