package sort;

/**
 * @author Angus
 * @date 2018/11/13
 */
public class QuickSort2 implements Sort {
    @Override
    public void sort(Comparable[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private void sort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        // 切分
        int j = partition(arr, lo, hi);
        // 将左半部分 arr[lo..j-1] 排序
        sort(arr, lo, j - 1);
        // 将右半部分 arr[j+1, hi] 排序
        sort(arr, j + 1, hi);
    }

    /**
     * 将数组分为 arr[lo..i-1], arr[i], arr[i+1.. hi]
     */
    private int partition(Comparable[] arr, int lo, int hi) {
        // 随机在arr[lo...hi]的范围中, 选择一个数值作为标定点pivot，保证在数组近乎有序的情况下也能良好完成排序
        swap(arr, lo, (int) (Math.random() * (hi - lo + 1)) + lo);
        Comparable v = arr[lo];
        // 左右扫描指针
        int i = lo + 1;
        int j = hi;
        while (true) {
            // 扫描左右，检查是否结束并交换元素
            // 注意条件，减少等值元素的交换，防止算法时间复杂度退化为 O(n^2)
            while (i <= hi && arr[i].compareTo(v) <= 0) {
                i++;
            }
            while (j >= lo && arr[j].compareTo(v) > 0) {
                j--;
            }
            if (i >= j) {
                // 此时 j 一定指向的是小于或等于的元素
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    private void swap(Comparable[] arr, int i, int j) {
        Comparable e = arr[i];
        arr[i] = arr[j];
        arr[j] = e;
    }
}
