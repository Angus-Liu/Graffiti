package chapter04;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author Angus
 * @date 2019/5/4
 */
public class Demo {
    public static void main(String[] args) {
        Executor executor = Executors.newCachedThreadPool();
        executor.execute(() -> System.out.println("Hello World"));
    }
}
