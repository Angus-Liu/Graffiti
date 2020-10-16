package ch05.se05;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁 CountDownLatch 使用
 */
public class TestHarness {

    public long timeTasks(int nThreads, Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                        endGate.countDown();
                    }
                } catch (InterruptedException ignored) {
                }
            }).start();
        }

        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }


    public static void main(String[] args) throws InterruptedException {
        long costTime = new TestHarness().timeTasks(10, () -> {
            long s = 0;
            for (int i = 0; i < 1000000000; i++) {
                s = s + i;
            }
        });
        System.out.println("costTime = " + costTime);
    }
}
