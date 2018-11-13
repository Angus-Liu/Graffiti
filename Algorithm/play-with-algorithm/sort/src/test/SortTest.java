package test;

import org.junit.Test;
import sort.*;

import java.util.Arrays;

/**
 * @author Angus
 * @date 2018/10/23
 */
public class SortTest {

    @Test
    public void sort() {
        Integer[] arr = SortTestHelper.generateRandomArray(10000, 1, 10000);
        SortTestHelper.testSort(new SelectionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new InsertionSort(), Arrays.copyOf(arr, arr.length));
        System.out.println(Arrays.toString(Arrays.copyOf(arr, arr.length)));
    }

    @Test
    public void bubbleSort() {
        Integer[] arr = SortTestHelper.generateRandomArray(10000, 1, 20000);
        SortTestHelper.testSort(new BubbleSort(), Arrays.copyOf(arr, arr.length));
    }

    @Test
    public void mergeSort() {
        Integer[] arr = SortTestHelper.generateRandomArray(20000, 1, 20000);
        SortTestHelper.testSort(new SelectionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new InsertionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new MergeSort(), Arrays.copyOf(arr, arr.length));
    }

    @Test
    public void mergeSort2() {
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(20000, 0);
//        SortTestHelper.testSort(new InsertionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new MergeSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new MergeSortTD(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new MergeSortBU(), Arrays.copyOf(arr, arr.length));
    }

    @Test
    public void quickSort() {
//        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 100000);
//        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(1000000, 0);
        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 1000000);
        SortTestHelper.testSort(new QuickSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new QuickSort2(), Arrays.copyOf(arr, arr.length));
    }

    @Test
    public void shellSort() {
        Integer[] arr = SortTestHelper.generateRandomArray(100000, 1, 10000000);
        SortTestHelper.testSort(new QuickSort(), Arrays.copyOf(arr, arr.length));
//        SortTestHelper.testSort(new MergeSort(), Arrays.copyOf(arr, arr.length));
//        SortTestHelper.testSort(new InsertionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new ShellSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new HeapSort(), Arrays.copyOf(arr, arr.length));

    }
}