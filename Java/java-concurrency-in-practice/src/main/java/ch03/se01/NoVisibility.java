package ch03.se01;

/**
 * 在没有同步的情况下共享变量出现不可见性
 */
public class NoVisibility {
    private static boolean ready;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                // 因可见性导致的死循环
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(100);
        ready = true;
    }
}
