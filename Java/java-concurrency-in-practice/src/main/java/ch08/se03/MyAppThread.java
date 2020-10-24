package ch08.se03;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 定制 Thread 基类
 */
public class MyAppThread extends Thread {
    public static final String DEFAULT_NAME = "MyAppThread";
    private static volatile boolean debugLifecycle = true;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();
    private static final Logger log = Logger.getAnonymousLogger();

    public MyAppThread(Runnable target) {
        this(target, DEFAULT_NAME);
    }

    public MyAppThread(Runnable target, String name) {
        super(target, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler((t, e) -> {
            log.log(Level.SEVERE, "UNCAUGHT in thread " + t.getName(), e);
        });
    }

    @Override
    public void run() {
        // 复制 debug 标志已确保一直的值
        boolean debug = debugLifecycle;
        if (debug) log.log(Level.FINE, "Created " + getName());
        System.out.println("Created " + getName());
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) log.log(Level.FINE, "Exiting " + getName());
        }
    }

    public static int getThreadsCreated() {
        return created.get();
    }

    public static int getThreadAlive() {
        return alive.get();
    }

    public static boolean getDebug() {
        return debugLifecycle;
    }

    public static void setDebug(boolean b) {
        debugLifecycle = b;
    }
}
