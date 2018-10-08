package queue;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Angus
 * @date 2018/10/8
 */
public class LoopQueueTest {

    @Test
    public void getSize() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void enqueue() {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for (int i = 0; i < 100; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);
        }
    }

    @Test
    public void dequeue() {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for (int i = 0; i < 100; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);
        }
        for (int i = 0; i < 100; i++) {
            loopQueue.dequeue();
            System.out.println(loopQueue);
        }
        for (int i = 0; i < 100; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue);
        }
    }

    @Test
    public void getFront() {
    }

    @Test
    public void getCapacity() {
    }
}