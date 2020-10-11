package ch04.se02;

/**
 * 通过一个私有锁来保护状态
 */
public class PrivateLock {

    private final Object myLock = new Object();

    private Integer wiget;

    void someMethod() {
       synchronized (myLock) {
           // 访问或者修改 wiget
       }
    }
}
