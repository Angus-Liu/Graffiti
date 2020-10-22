package ch07.se01;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * 取消操作
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask();
}
