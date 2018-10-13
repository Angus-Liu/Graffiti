package linkedlist;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Angus
 * @date 2018/10/9
 */
public class LinkedListTest {

    @Test
    public void add() {
    }

    @Test
    public void addFirst() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
    }

    @Test
    public void addLast() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList);
        }
    }

    @Test
    public void getFirst() {
    }

    @Test
    public void getLast() {
    }

    @Test
    public void set() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.set(5, 5555);
        System.out.println(linkedList);
    }

    @Test
    public void contains() {
    }

    @Test
    public void remove() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList);
        System.out.println(linkedList.removeLast());
        System.out.println(linkedList);
        System.out.println(linkedList.remove(2));
        System.out.println(linkedList);
    }
}