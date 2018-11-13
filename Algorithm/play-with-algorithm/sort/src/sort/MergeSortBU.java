package sort;

/**
 * 归并排序-迭代法（Bottom-up)
 *
 * @author Angus
 * @date 2018/11/13
 */
public class MergeSortBU implements Sort {
    /**
     * 归并所需的辅助数组
     */
    private Comparable[] aux;

    /**
     * 自底向上，迭代实现
     */
    @Override
    public void sort(Comparable[] arr) {
        // 进行 logn 次两两归并
        int n = arr.length;
        aux = new Comparable[n];
        // 归并的区间 sz，每次扩大一倍
        for (int sz = 1; sz < n; sz += sz) {
            // lo 为每次归并时左区间的起始索引
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                // 合并区间 arr[lo..lo+sz-1] 和 arr[lo+sz..lo+sz+sz-1]
                // 注意 hi 的取值，防止下标越界
                merge(arr, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
        }
    }

    /**
     * 将 arr[lo..mid] 和 arr[mid+1..hi] 归并
     */
    private void merge(Comparable[] arr, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;
        // 将 arr[lo..hi] 复制到 aux[lo..hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = arr[k];
        }
        // 归并回 arr[lo..hi]
        for (int k = lo; k <= hi; k++) {
            // 先考虑一边已经归并完，而另一边还有值的情况
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            }
            // 将两边值进行比较，归并
            else if (aux[i].compareTo(aux[j]) < 0) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }
    }
}
