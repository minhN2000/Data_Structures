package test;


import linkedlist.Stack;
import org.junit.Test;

import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class StackTest {
    @Test
    public void StackBehaviorTest() {
        Stack<String> stack = new Stack<>();
        assertEquals(0, stack.size());

        stack.push("Foo");
        stack.push("Bar");
        stack.push("Baz");

        assertEquals(3, stack.size());
        assertEquals("Baz", stack.peek());

        assertEquals("Baz", stack.pop());
        assertEquals(2, stack.size());
        assertEquals("Bar", stack.pop());
        assertEquals(1, stack.size());
        assertEquals("Foo", stack.pop());
        assertEquals(0, stack.size());

        try {
            stack.pop();
            fail();
        } catch (NoSuchElementException e) { };
    }
}
