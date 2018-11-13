package sort;

/**
 * @author Angus
 * @date 2018/11/13
 */
public class HeapSort implements Sort {
    @Override
    public void sort(Comparable[] arr) {
        int n = arr.length;
        // 构建最大堆
        for (int k = n / 2; k >= 0; k--) {
            sink(arr, k, n);
        }
        while (n > 0) {
            // 将堆顶元素与堆最后一个元素交换，使得堆尾渐渐有序
            swap(arr, 0, --n);
            // 对新的堆顶元素做下层操作
            sink(arr, 0, n);
        }
    }

    /**
     * 下沉操作
     *
     * @param arr 待排序数组
     * @param k   待下沉元素索引
     * @param n   至多能下沉到 n - 1 处，n 及其之后的元素已排好序
     */
    private void sink(Comparable[] arr, int k, int n) {
        while (2 * k + 1 < n) {
            // 选出左右孩子中较大的那个，进行交换
            int j = 2 * k + 1;
            // j + 1 < n，是为了确保右孩子存在
            if (j + 1 < n && arr[j].compareTo(arr[j + 1]) < 0) {
                j++;
            }
            // 查看交换是否满足堆的性质，不满足就不交换
            if (arr[k].compareTo(arr[j]) >= 0) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    private void swap(Comparable[] arr, int i, int j) {
        Comparable e = arr[i];
        arr[i] = arr[j];
        arr[j] = e;
    }
}
