package test;

import linkedlist.Queue;
import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.*;

public class QueueTest {
    @Test
    public void QueueBehaviorTest() {
        Queue<String> queue = new Queue<>();
        assertEquals(0, queue.size());

        queue.add("Foo");
        queue.add("Bar");
        queue.add("Baz");

        assertEquals(3, queue.size());
        assertEquals("Foo", queue.peek());

        assertEquals("Foo", queue.remove());
        assertEquals(2, queue.size());
        assertEquals("Bar", queue.remove());
        assertEquals(1, queue.size());
        assertEquals("Baz", queue.remove());
        assertEquals(0, queue.size());

        try {
            queue.remove();
            fail();
        } catch (NoSuchElementException e) { };
    }

    @Test
    public void QueueInvalidOperationTest() {
        Queue<String> queue = new Queue<>();
        assertEquals(0, queue.size());

        queue.add("Foo");
        queue.add("Bar");
        queue.add("Baz");

        try {
            queue.add(2, "Fi");
            fail();
        } catch (RuntimeException e) { };

        try {
            queue.remove(2);
            fail();
        } catch (RuntimeException e) { };

        try {
            queue.peekFront();
            fail();
        } catch (RuntimeException e) { };

        try {
            queue.peekBack();
            fail();
        } catch (RuntimeException e) { };

        try {
            queue.popFront();
            fail();
        } catch (RuntimeException e) { };

        try {
            queue.popBack();
            fail();
        } catch (RuntimeException e) { };

        try {
            queue.iterator();
            fail();
        } catch (RuntimeException e) { };

        try {
            queue.reverseIterator();
            fail();
        } catch (RuntimeException e) { };
    }
}
