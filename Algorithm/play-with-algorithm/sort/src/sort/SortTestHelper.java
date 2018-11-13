package sort;

import java.util.Random;

public class SortTestHelper {

    /**
     * 生成有 n 个元素的随机数组，每个元素的范围为 [rangeL, rangeR]
     *
     * @param n
     * @param rangeL
     * @param rangeR
     * @return
     */
    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert rangeL <= rangeR;

        Integer[] randomArr = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            // 偏移实现范围控制
            randomArr[i] = random.nextInt(rangeR - rangeL + 1) + rangeL;
        }
        return randomArr;
    }

    /**
     * 生成一个近乎有序的数组
     * 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
     * swapTimes定义了数组的无序程度:
     * swapTimes == 0 时, 数组完全有序
     * swapTimes 越大, 数组越趋向于无序
     *
     * @param n
     * @param swapTimes
     * @return
     */
    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes) {

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Integer(i);
        }

        for (int i = 0; i < swapTimes; i++) {
            int a = (int) (Math.random() * n);
            int b = (int) (Math.random() * n);
            int t = arr[a];
            arr[a] = arr[b];
            arr[b] = t;
        }

        return arr;
    }


    /**
     * 判断arr数组是否有序
     *
     * @param arr
     * @return
     */
    public static boolean isSorted(Comparable[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
     *
     * @param sort
     * @param arr
     */
    public static void testSort(Sort sort, Comparable[] arr) {

        try {
            long startTime = System.nanoTime();
            // 调用排序函数
            sort.sort(arr);
            long endTime = System.nanoTime();
            assert isSorted(arr);
            System.out.println(sort.getClass().getSimpleName() + " : " + (endTime - startTime) / 1000000000.0 + " s");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
