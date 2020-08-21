package item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 简单并发执行框架
 */
public class SimpleConcurrentExecutionFramework {

    public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
        CountDownLatch ready = new CountDownLatch(concurrency);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch done = new CountDownLatch(concurrency);

        for (int i = 0; i < concurrency; i++) {
            executor.execute(() -> {
                // 告诉 ready 计时器（或 time 所在线程），当前线程准备好了
                ready.countDown();
                try {
                    // 等待所有线程准备完毕
                    start.await();
                    action.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    // 告诉 done 计时器（或 time 所在线程），当前线程执行结束
                    done.countDown();
                }
            });
        }

        // 等待所有线程准备完毕
        ready.await();
        System.out.println("");
        long startNanoTime = System.nanoTime();
        // 所有线程准备完毕后开始任务
        start.countDown();
        // 等待所有线程执行结束
        done.await();
        return System.nanoTime() - startNanoTime;
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleConcurrentExecutionFramework.time(Executors.newFixedThreadPool(10), 10, () -> {
            long sum = 0L;
            for (int i = 0; i < 10000000; i++) {
                sum += i;
            }
            System.out.println(sum);
        });
    }
}
