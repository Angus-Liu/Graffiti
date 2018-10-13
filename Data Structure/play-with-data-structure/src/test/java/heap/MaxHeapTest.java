package heap;


import org.junit.Test;

import java.util.Random;

/**
 * @author Angus
 * @date 2018/10/11
 */
public class MaxHeapTest {

    @Test
    public void extractMax() {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] > arr[i]) {
                throw new IllegalStateException("Error!");
            }
        }
        System.out.println("Test MaxHeap completed!");
    }

    private double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int num : testData) {
                maxHeap.add(num);
            }
        }
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 0; i < testData.length - 1; i++) {
            if (arr[i + 1] > arr[i]) {
                throw new IllegalStateException("Error!");
            }
        }
        System.out.println("Test MaxHeap completed!");
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    @Test
    public void heapify() {
        int n = 1000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("Without heapify: " + testHeap(testData, false) + "s");
        System.out.println("With heapify: " + testHeap(testData, true) + "s");
    }
}