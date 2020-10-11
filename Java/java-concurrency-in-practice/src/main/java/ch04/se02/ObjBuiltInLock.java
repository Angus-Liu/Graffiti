package ch04.se02;

/**
 * 使用对象内置锁
 */
public class ObjBuiltInLock {

    synchronized void someMethod() {
        try {
            System.out.println("someMethod run...");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ObjBuiltInLock lock = new ObjBuiltInLock();
        new Thread(lock::someMethod).start();
        synchronized (lock) {
            System.out.println("main run...");
            Thread.sleep(5000);
        }
    }
}
