package stack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Angus
 * @date 2018/10/7
 */
public class ArrayStackTest {

    @Test
    public void getSize() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void push() {
        Stack<String> stack = new ArrayStack<>();
        stack.push("Angus");
        System.out.println(stack);
        stack.push("Liu");
        System.out.println(stack);
    }

    @Test
    public void pop() {
        Stack<String> stack = new ArrayStack<>();
        stack.push("Angus");
        System.out.println(stack);
        stack.push("Liu");
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }

    @Test
    public void peek() {
        Stack<String> stack = new ArrayStack<>();
        stack.push("Angus");
        System.out.println(stack.peek());
        stack.push("Liu");
        System.out.println(stack.peek());
    }

    @Test
    public void getCapacity() {
    }
}