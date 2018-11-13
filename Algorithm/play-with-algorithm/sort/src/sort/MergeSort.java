package sort;

import java.util.Arrays;

/**
 * 归并排序的简单实现
 *
 * @author Angus
 * @date 2018/10/25
 */
public class MergeSort implements Sort {

    private InsertionSort insertionSort = new InsertionSort();

    /**
     * 归并排序
     *
     * @param arr
     */
    @Override
    public void sort(Comparable[] arr) {
        // 自顶向下实现，递归
         topDownSort(arr, 0, arr.length - 1);
        // 自底向上实现，迭代
//        bottomUpSort(arr);
    }

    /**
     * 自顶向下，递归方式。对 arr[l...r] 的范围进行排序
     *
     * @param arr 待排序数组
     * @param l   待排序部分的开始索引
     * @param r   待排序部分的结尾索引
     */
    private void topDownSort(Comparable[] arr, int l, int r) {
        // if (l >= r) {
        //   return;
        // }
        // 优化，在划分到一定范围时（近乎有序），使用插入排序来提高效率
        if (r - l <= 15) {
            insertionSort.sort(arr, l, r);
            return;
        }
        // 进行归并排序
        int mid = l + (r - l) / 2;
        // 将待排序数组继续划分，排序
        topDownSort(arr, l, mid);
        topDownSort(arr, mid + 1, r);
        // 优化，只有左边最大值大于右边最小值才进行合并，否者直接是有序的
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            // 归并
            merge(arr, l, mid, r);
        }
    }


    /**
     * 自底向上，迭代方式
     *
     * @param arr 待排序数组
     */
    private void bottomUpSort(Comparable[] arr) {
        int n = arr.length;
        // 自底向上归并的区间每次扩大一倍
        for (int size = 1; size < n; size += size) {
            // 每次是两个小区间进行合并
            for (int i = 0; i + size < n; i += size + size) {
                // 对 arr[i...i+size-1] 和 arr[i+size...i+size+size-1]进行归并
                merge(arr, i, i + size - 1, Math.min(i + size + size - 1, n - 1));
            }
        }
    }

    /**
     * 将排好序的两部分归并
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    private void merge(Comparable[] arr, int l, int mid, int r) {
        // temp 为辅助数组，存储待合并的部分（空间换时间）
        Comparable[] temp = Arrays.copyOfRange(arr, l, r + 1);
        // 比较两部分的值，进行合并
        // 初始化，i 指向左半部分的起始索引位置l；j 指向右半部分起始索引位置 mi + 1
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            // 考虑一边已经归并完，而另一边还有值的情况
            if (i > mid) {
                // l 是偏移值
                arr[k] = temp[j - l];
                j++;
            } else if (j > r) {
                arr[k] = temp[i - l];
                i++;
            } else {
                // 这下面是左右两部分都还有值的情况
                if (temp[i - l].compareTo(temp[j - l]) <= 0) {
                    arr[k] = temp[i - l];
                    i++;
                } else {
                    arr[k] = temp[j - l];
                    j++;
                }
            }
        }
    }

    private void showMergeInfo(Comparable[] arr, int l, int mid, int r, Comparable[] temp) {
        System.out.println("l:" + l + "    mid:" + mid + "    r:" + r);
        System.out.println(Arrays.toString(temp));
        System.out.println(Arrays.toString(arr));
    }
}
