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
    public void mergeSort() {
        Integer[] arr = SortTestHelper.generateRandomArray(20000, 1, 20000);
        SortTestHelper.testSort(new SelectionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new InsertionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new MergeSort(), Arrays.copyOf(arr, arr.length));
    }

    @Test
    public void mergeSort2() {
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(20000, 0);
        SortTestHelper.testSort(new InsertionSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new MergeSort(), Arrays.copyOf(arr, arr.length));
    }

    @Test
    public void quickSort() {
//        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 100000);
//        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(1000000, 0);
        Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 10);
        SortTestHelper.testSort(new QuickSort(), Arrays.copyOf(arr, arr.length));
        SortTestHelper.testSort(new MergeSort(), Arrays.copyOf(arr, arr.length));
    }
}