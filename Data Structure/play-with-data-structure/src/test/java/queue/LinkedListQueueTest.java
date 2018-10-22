package queue;

import org.junit.Test;

/**
 * @author Angus
 * @date 2018/10/9
 */
public class LinkedListQueueTest {

    @Test
    public void enqueue() {
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        for (int i = 0; i <10; i++) {
            linkedListQueue.enqueue(i);
            System.out.println(linkedListQueue);
            if (i % 3 == 2) {
                linkedListQueue.dequeue();
                System.out.println(linkedListQueue);
            }
        }
    }

    @Test
    public void dequeue() {
    }
}