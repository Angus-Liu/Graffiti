package ch08.se03;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 自定义的线程工厂
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(@NotNull Runnable r) {
        return new MyAppThread(r, poolName);
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(100, new MyThreadFactory("demo"));
        System.out.println("run...");
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            exec.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println("end...");
    }
}
