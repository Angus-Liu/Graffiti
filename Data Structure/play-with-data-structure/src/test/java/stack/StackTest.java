package stack;

import org.junit.Test;

import java.util.Random;

/**
 * @author Angus
 * @date 2018/10/9
 */
public class StackTest {

    private static int opCount = 10000000;

    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt());
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    private void arrayStack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        System.out.println("ArrayStack, time: " + testStack(arrayStack, opCount) + "s");
    }

    private void linkedListStack() {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        System.out.println("LinkedListStack, time: " + testStack(linkedListStack, opCount) + "s");
    }

    @Test
    public void compare() {
        arrayStack();
        arrayStack();
        arrayStack();
        linkedListStack();
        linkedListStack();
        linkedListStack();
    }
}