package ch05.se05;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 使用
 */
public class Preloader {


    private final FutureTask<ProductInfo> future = new FutureTask<>(this::loadProductInfo);

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    private ProductInfo loadProductInfo() throws InterruptedException {
        Thread.sleep(10000);
        return new ProductInfo();
    }

    private static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        else if (t instanceof Error)
            throw (Error) t;
        else
            throw new IllegalStateException("Not unchecked", t);
    }

    public ProductInfo get() throws InterruptedException, DataLoadException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException)
                throw (DataLoadException) cause;
            throw launderThrowable(e);
        }
    }
}

class DataLoadException extends Exception {

}

class ProductInfo {
}