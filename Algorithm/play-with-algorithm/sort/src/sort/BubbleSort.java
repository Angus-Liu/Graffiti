package sort;

/**
 * @author Angus
 * @date 2018/11/13
 */
public class BubbleSort implements Sort {
    @Override
    public void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 交换标志
            boolean swap = false;
            // arr.length - 1 - i 号元素已排好序
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
            }
            if (!swap) {
                // 如果没有发生交换，代表已经排好序，直接返回
                return;
            }
        }
    }
}
