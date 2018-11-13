package sort;

/**
 * 希尔排序
 *
 * @author Angus
 * @date 2018/11/13
 */
public class ShellSort implements Sort {
    @Override
    public void sort(Comparable[] arr) {
        // 步长
        int step = arr.length / 2;
        while (step >= 1) {
            for (int i = step; i < arr.length; i++) {
                Comparable e = arr[i];
                int j;
                for (j = i; j >= step && e.compareTo(arr[j - step]) < 0; j -= step) {
                    arr[j] = arr[j - step];
                }
                arr[j] = e;
            }
            step /= 2;
        }
    }
}
