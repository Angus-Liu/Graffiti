package sort;

/**
 * 归并排序-递归法（Top-down）
 *
 * @author Angus
 * @date 2018/11/13
 */
public class MergeSortTD implements Sort {

    /**
     * 归并所需的辅助数组
     */
    private Comparable[] aux;

    /**
     * 自顶向下，递归实现
     */
    @Override
    public void sort(Comparable[] arr) {
        aux = new Comparable[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 将数组 arr[lo..hi] 进行归并排序
     */
    private void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        // 将左半边排序
        sort(arr, lo, mid);
        // 将右半边排序
        sort(arr, mid + 1, hi);
        // 归并结果
        merge(arr, lo, mid, hi);
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
