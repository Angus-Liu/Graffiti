package queue;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Angus
 * @date 2018/10/8
 */
public class ArrayQueueTest {

    @Test
    public void getSize() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void enqueue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
    }

    @Test
    public void dequeue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 100 ; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        for (int i = 0; i < 100 ; i++) {
            queue.dequeue();
            System.out.println(queue);
        }
        for (int i = 0; i < 100 ; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
    }

    @Test
    public void getFront() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        System.out.println(queue.getFront());
    }

    @Test
    public void getCapacity() {
    }
}