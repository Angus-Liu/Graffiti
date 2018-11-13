package sort;

/**
 * 快速排序的简单实现
 *
 * @author Angus
 * @date 2018/10/26
 */
public class QuickSort implements Sort {

    private InsertionSort insertionSort = new InsertionSort();

    @Override
    public void sort(Comparable[] arr) {
        sort2(arr, 0, arr.length - 1);
    }


    /**
     * 对 arr[l...r] 部分进行 partition 操作
     * 返回 p 使得 arr[l...p-1] < arr[p] < arr[p+1...r]
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private int partition1(Comparable[] arr, int l, int r) {
        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        // 保证在数组近乎有序的情况下也能良好完成排序
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        // j 指向小于 v 的（前半段）的最后一个值
        int j = l;
        for (int i = l; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                SortTestHelper.swap(arr, j + 1, i);
                j++;
            }
        }
        // 此时 j 指向 v 所在的值，且 v 把数组分成了两部分
        SortTestHelper.swap(arr, l, j);
        return j;
    }

    /**
     * 针对近乎有序的数据进行优化
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private int partition2(Comparable[] arr, int l, int r) {
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];
        // arr[l+1..i] <= v; arr[j...r] >= v
        int i = l + 1;
        int j = r;
        while (true) {
            // 等于的情况仍旧交换，这样比较平衡，使得重复元素能够平均的分到两边，不至于退化为 n^2 算法
            while (i <= r && arr[i].compareTo(v) < 0) {
                i++;
            }
            while (j >= l + 1 && arr[j].compareTo(v) > 0) {
                j--;
            }
            if (i > j) {
                break;
            }
            SortTestHelper.swap(arr, i, j);
            i++;
            j--;
        }
        SortTestHelper.swap(arr, l, j);
        return j;
    }

    /**
     * 递归实现快速排序
     *
     * @param arr 待排序数组
     * @param l   排序左边界（含）
     * @param r   排序右边界（含）
     */
    private void sort1(Comparable[] arr, int l, int r) {
//        if (l >= r) {
//            return;
//        }
        // 优化，在划分到一定范围时（近乎有序），使用插入排序来提高效率
        if (r - l <= 15) {
            insertionSort.sort(arr, l, r);
            return;
        }
        int p = partition2(arr, l, r);
        sort1(arr, l, p);
        sort1(arr, p + 1, r);
    }

    /**
     * 三路快排
     *
     * @param arr
     * @param l
     * @param r
     */
    private void sort2(Comparable[] arr, int l, int r) {
        if (r - l <= 15) {
            insertionSort.sort(arr, l, r);
            return;
        }
        // partition
        // arr[l=1...lt] < v = arr[lt+1...i] < arr[gt...r]
        SortTestHelper.swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);
        Comparable v = arr[l];

        // arr[l+1...lt] < v
        int lt = l;
        // arr[gt...r] > v
        int gt = r + 1;
        // arr[lt+1, i] = v
        int i = l + 1;
        while (i < gt) {
            if (arr[i].compareTo(v) < 0) {
                SortTestHelper.swap(arr, i, lt + 1);
                i++;
                lt++;
            } else if (arr[i].compareTo(v) > 0) {
                SortTestHelper.swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }
        SortTestHelper.swap(arr, l, lt);
        sort2(arr, l, lt - 1);
        sort2(arr, gt, r);
    }
}
