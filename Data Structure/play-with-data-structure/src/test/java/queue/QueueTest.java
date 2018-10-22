package queue;

import org.junit.Test;

import java.util.Random;

/**
 * @author Angus
 * @date 2018/10/8
 */
public class QueueTest {

    private static int opCount = 100000;

    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt());
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    @Test
    public void arrayQueue() {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time + "s");
    }

    @Test
    public void loopQueue() {
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time + "s");
    }

    @Test
    public void linkedListQueue() {
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double time = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time: " + time + "s");
    }

    @Test
    public void compare() {
        loopQueue();
        loopQueue();
        loopQueue();
//        arrayQueue();
//        arrayQueue();
//        arrayQueue();
        linkedListQueue();
        linkedListQueue();
        linkedListQueue();
    }
}