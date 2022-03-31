package test;

import linkedlist.LinkedList;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.*;

public class LinkedListTest {
    private Collection<BigInteger> numberCollection = Arrays.asList(
            BigInteger.ZERO,
            BigInteger.ONE,
            BigInteger.TWO
    );
    private LinkedList<BigInteger> numberList;

    @Before
    public void setUp() {
        this.numberList = new LinkedList<>();
    }

    public void populate(LinkedList<BigInteger> list) {
        int size = list.size();
        list.pushBack(BigInteger.ZERO);
        list.pushBack(BigInteger.ONE);
        list.pushBack(BigInteger.TWO);
        assertEquals(3 + size, list.size());
    }

    public <T> void assertEmpty(LinkedList<T> list) {
        assertEquals(0, list.size());

        try {
            list.popFront();
            fail();
        } catch (NoSuchElementException e) { }
        try {
            list.popBack();
            fail();
        } catch (NoSuchElementException e) { }
    }

    @Test
    public void BasicConstructorTest() {
        LinkedList<Object> list = new LinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    public void CollectionEmptyConstructorTest() {
        LinkedList<Object> list = new LinkedList<>(Arrays.asList());
        assertEquals(0, list.size());
    }

    @Test
    public void CollectionConstructorSizeTest() {
        LinkedList<BigInteger> list = new LinkedList<>(numberCollection);
        assertEquals(numberCollection.size(), list.size());
    }

    @Test
    public void EmptyListTest() {
        assertEquals(0, numberList.size());
        assertEquals(null, numberList.peekFront());
        assertEquals(null, numberList.peekBack());

        assertEmpty(numberList);
    }

    @Test
    public void PushPopFrontTest() {
        numberList.pushFront(BigInteger.ZERO);
        assertEquals(BigInteger.ZERO, numberList.peekFront());

        numberList.pushFront(BigInteger.ONE);
        assertEquals(BigInteger.ONE, numberList.peekFront());

        numberList.pushFront(BigInteger.TWO);
        assertEquals(BigInteger.TWO, numberList.peekFront());

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.TWO, numberList.popFront());
        assertEquals(BigInteger.ONE, numberList.peekFront());
        assertEquals(BigInteger.ONE, numberList.popFront());
        assertEquals(BigInteger.ZERO, numberList.peekFront());
        assertEquals(BigInteger.ZERO, numberList.popFront());

        assertEmpty(numberList);
    }
    
    @Test
    public void PushPopBackTest() {
        numberList.pushBack(BigInteger.ZERO);
        assertEquals(BigInteger.ZERO, numberList.peekBack());

        numberList.pushBack(BigInteger.ONE);
        assertEquals(BigInteger.ONE, numberList.peekBack());

        numberList.pushBack(BigInteger.TWO);
        assertEquals(BigInteger.TWO, numberList.peekBack());

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.TWO, numberList.popBack());
        assertEquals(BigInteger.ONE, numberList.peekBack());
        assertEquals(BigInteger.ONE, numberList.popBack());
        assertEquals(BigInteger.ZERO, numberList.peekBack());
        assertEquals(BigInteger.ZERO, numberList.popBack());

        assertEmpty(numberList);
    }

    @Test
    public void BasicBidirectionalTest() {
        numberList.pushFront(BigInteger.ZERO);
        numberList.pushBack(BigInteger.ONE);

        assertEquals(BigInteger.ZERO, numberList.peekFront());
        assertEquals(BigInteger.ONE, numberList.peekBack());

        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(1, numberList.size());
        assertEquals(BigInteger.ONE, numberList.peekFront());
        assertEquals(BigInteger.ONE, numberList.peekBack());
        assertEquals(BigInteger.ONE, numberList.popBack());

        assertEmpty(numberList);
    }

    @Test
    public void CollectionConstructorPopTest() {
        LinkedList<BigInteger> list = new LinkedList<>(numberCollection);
        assertEquals(numberCollection.size(), list.size());

        assertEquals(BigInteger.ZERO, list.peekFront());
        assertEquals(BigInteger.TWO, list.peekBack());

        assertEquals(BigInteger.ZERO, list.popFront());
        assertEquals(2, list.size());

        assertEquals(BigInteger.TWO, list.popBack());
        assertEquals(1, list.size());

        assertEquals(BigInteger.ONE, list.popFront());
        assertEmpty(numberList);
    }


    @Test
    public void BasicAddTest() {
        numberList.add(BigInteger.ZERO);
        assertEquals(BigInteger.ZERO, numberList.peekFront());

        numberList.add(BigInteger.ONE);
        assertEquals(BigInteger.ONE, numberList.peekFront());

        numberList.add(BigInteger.TWO);
        assertEquals(BigInteger.TWO, numberList.peekFront());

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.TWO, numberList.popFront());
        assertEquals(BigInteger.ONE, numberList.peekFront());
        assertEquals(BigInteger.ONE, numberList.popFront());
        assertEquals(BigInteger.ZERO, numberList.peekFront());
        assertEquals(BigInteger.ZERO, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void AddIndexEndsTest() {
        this.populate(numberList);

        numberList.add(0, BigInteger.valueOf(-1));
        assertEquals(4, numberList.size());
        assertEquals(BigInteger.valueOf(-1), numberList.peekFront());

        numberList.add(4, BigInteger.valueOf(3));
        assertEquals(5, numberList.size());
        assertEquals(BigInteger.valueOf(3), numberList.peekBack());

        numberList.popFront();
        numberList.popBack();
        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(BigInteger.TWO, numberList.popBack());
        assertEquals(BigInteger.ONE, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void AddOutOfBoundsTest() {
        this.populate(numberList);

        try {
            numberList.add(numberList.size() + 1, BigInteger.ZERO);
            fail();
        } catch (IndexOutOfBoundsException e) { };

        try {
            numberList.add(-1, BigInteger.ZERO);
            fail();
        } catch (IndexOutOfBoundsException e) { };
    }

    @Test
    public void AddInsertEmptyTest() {
        BigInteger five = BigInteger.valueOf(5);
        numberList.add(0, five);
        assertEquals(1, numberList.size());
        assertEquals(five, numberList.peekFront());
        assertEquals(five, numberList.peekBack());
        assertEquals(five, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void AddInsertTest() {
        this.populate(numberList);
        // [0 1 2]
        assertEquals(3, numberList.size());
        numberList.add(1, BigInteger.valueOf(9));
        // [0 9 1 2]
        assertEquals(4, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popFront());
        // [9 1 2]
        assertEquals(3, numberList.size());
        assertEquals(BigInteger.valueOf(9), numberList.popFront());
        // [1 2]
        assertEquals(2, numberList.size());
        numberList.add(1, BigInteger.valueOf(4));
        // [1 4 2]
        numberList.add(2, BigInteger.valueOf(8));
        // [1 4 8 2]
        numberList.add(2, BigInteger.valueOf(6));
        // [1 4 6 8 2]
        assertEquals(5, numberList.size());
        assertEquals(BigInteger.TWO, numberList.popBack());
        assertEquals(BigInteger.ONE, numberList.popFront());
        // [4 6 8]
        assertEquals(BigInteger.valueOf(4), numberList.popFront());
        assertEquals(BigInteger.valueOf(6), numberList.popFront());
        assertEquals(BigInteger.valueOf(8), numberList.popBack());

        assertEmpty(numberList);
    }

    @Test
    public void BasicRemoveTest() {
        numberList.pushFront(BigInteger.ZERO);
        numberList.remove();
        assertEmpty(numberList);
    }

    @Test
    public void BasicRemoveMultipleTest() {
        this.populate(numberList);
        numberList.remove();

        assertEquals(2, numberList.size());
        assertEquals(BigInteger.ONE, numberList.peekFront());
        assertEquals(BigInteger.TWO, numberList.peekBack());

        numberList.remove();
        assertEquals(1, numberList.size());
        assertEquals(BigInteger.TWO, numberList.peekFront());
        assertEquals(BigInteger.TWO, numberList.peekBack());

        numberList.remove();
        assertEmpty(numberList);
    }

    // Removing from index zero has the same behavior
    // as removing without passing any argument (from the front)
    @Test
    public void RemoveIndexZeroTest() {
        this.populate(numberList);
        numberList.remove(0);

        assertEquals(2, numberList.size());
        assertEquals(BigInteger.ONE, numberList.peekFront());
        assertEquals(BigInteger.TWO, numberList.peekBack());

        numberList.remove(0);
        assertEquals(1, numberList.size());
        assertEquals(BigInteger.TWO, numberList.peekFront());
        assertEquals(BigInteger.TWO, numberList.peekBack());

        numberList.remove(0);
        assertEmpty(numberList);
    }

    @Test
    public void RemoveIndexLastTest() {
        this.populate(numberList);

        numberList.remove(numberList.size() - 1);
        assertEquals(2, numberList.size());

        assertEquals(BigInteger.ZERO, numberList.peekFront());
        assertEquals(BigInteger.ONE, numberList.peekBack());

        numberList.remove(numberList.size() - 1);
        assertEquals(1, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.peekFront());

        numberList.remove(numberList.size() - 1);
        assertEmpty(numberList);
    }

    @Test
    public void RemoveOutOfBoundsTest() {
        this.populate(numberList);

        try {
            numberList.remove(-1);
            fail();
        } catch (IndexOutOfBoundsException e) { }

        try {
            numberList.remove(numberList.size());
            fail();
        } catch (IndexOutOfBoundsException e) { }

        try {
            numberList.remove(999);
            fail();
        } catch (IndexOutOfBoundsException e) { }
    }

    @Test
    public void RemoveIndexTest() {
        this.populate(numberList);
        // [0, 1, 2]
        numberList.remove(1);

        // [0, 2]
        assertEquals(2, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.peekFront());
        assertEquals(BigInteger.TWO, numberList.peekBack());

        numberList.pushFront(BigInteger.valueOf(-4));
        numberList.pushBack(BigInteger.valueOf(9));
        // [-4, 0, 2, 9]

        numberList.remove(2);
        // [-4, 0, 9]
        assertEquals(3, numberList.size());
        assertEquals(BigInteger.valueOf(-4), numberList.peekFront());
        assertEquals(BigInteger.valueOf(9), numberList.peekBack());

        numberList.remove(0);
        // [0, 9]
        numberList.remove(1);
        // [0]
        assertEquals(1, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popBack());

        assertEmpty(numberList);
    }

    @Test
    public void AddInsertRemoveTest() {
        this.populate(numberList);
        // [0 1 2]

        numberList.add(BigInteger.valueOf(9));
        numberList.pushFront(BigInteger.valueOf(-1));
        numberList.pushBack(BigInteger.valueOf(12));
        // [-1, 9, 0, 1, 2, 12]

        assertEquals(6, numberList.size());
        numberList.add(1, BigInteger.valueOf(4));
        // [-1, 4, 9, 0, 1, 2, 12]
        numberList.add(4, BigInteger.valueOf(8));
        // [-1, 4, 9, 0, 8, 1, 2, 12]

        assertEquals(BigInteger.valueOf(12), numberList.popBack());
        assertEquals(BigInteger.valueOf(-1), numberList.popFront());
        // [4, 9, 0, 8, 1, 2]

        numberList.remove(3);
        assertEquals(5, numberList.size());
        assertEquals(BigInteger.valueOf(4), numberList.peekFront());
        assertEquals(BigInteger.valueOf(2), numberList.peekBack());
        // [4, 9, 0, 1, 2]

        numberList.remove(numberList.size() - 1);
        // [4, 9, 0, 1]
        assertEquals(4, numberList.size());
        assertEquals(BigInteger.valueOf(4), numberList.popFront());
        // [9, 0, 1]
        assertEquals(BigInteger.valueOf(1), numberList.popBack());
        // [9, 0]

        numberList.remove();
        // [0]
        assertEquals(1, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popBack());
        assertEmpty(numberList);
    }

    @Test
    public void EmptyIteratorTest() {
        Iterator i = numberList.iterator();
        assertFalse(i.hasNext());

        i = numberList.iterator();
        assertFalse(i.hasNext());
    }

    @Test
    public void EmptyIteratorThrowsOnNextTest() {
        Iterator i = numberList.iterator();

        try {
            i.next();
            fail();
        } catch (NoSuchElementException e) { }

        i = numberList.iterator();

        try {
            i.next();
            fail();
        } catch (NoSuchElementException e) { }
    }

    @Test
    public void SimpleIteratorTest() {
        populate(numberList);

        Iterator i = numberList.iterator();
        assertTrue(i.hasNext());
        assertEquals(BigInteger.ZERO, i.next());
        assertEquals(BigInteger.ONE, i.next());
        assertEquals(BigInteger.TWO, i.next());

        assertFalse(i.hasNext());
        try {
            i.next();
            fail();
        } catch (NoSuchElementException e) { }

        i = numberList.iterator();
        assertTrue(i.hasNext());
        assertEquals(BigInteger.ZERO, i.next());
        assertEquals(BigInteger.ONE, i.next());
        assertEquals(BigInteger.TWO, i.next());

        assertFalse(i.hasNext());
        try {
            i.next();
            fail();
        } catch (NoSuchElementException e) { }

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(BigInteger.ONE, numberList.popFront());
        assertEquals(BigInteger.TWO, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void IteratorForEachTest() {
        populate(numberList);

        int i = 0;
        for (BigInteger value : numberList) {
            assertEquals(BigInteger.valueOf(i++), value);
        }

        i = 0;
        for (BigInteger value : numberList) {
            assertEquals(BigInteger.valueOf(i++), value);
        }

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(BigInteger.ONE, numberList.popFront());
        assertEquals(BigInteger.TWO, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void IteratorRemoveThrowsWhenNextNotCalledTest() {
        populate(numberList);

        Iterator i = numberList.iterator();
        assertTrue(i.hasNext());

        try {
            i.remove();
            fail();
        } catch (IllegalStateException e) { }

        populate(numberList);

        // Remove may only be called when next() is called
        i = numberList.iterator();
        assertTrue(i.hasNext());

        try {
            i.remove();
            fail();
        } catch (IllegalStateException e) { }
    }

    @Test
    public void IteratorRemoveFrontTest() {
        populate(numberList);

        Iterator i = numberList.iterator();
        assertTrue(i.hasNext());

        assertEquals(BigInteger.ZERO, i.next());
        i.remove();
        assertEquals(2, numberList.size());

        assertEquals(BigInteger.ONE, i.next());
        i.remove();
        assertEquals(1, numberList.size());

        assertEquals(BigInteger.TWO, i.next());
        i.remove();

        assertEmpty(numberList);
    }

    @Test
    public void IteratorRemoveBackTest() {
        populate(numberList);

        Iterator i = numberList.iterator();
        i.next();
        i.next();
        i.next();

        assertFalse(i.hasNext());
        i.remove();
        assertEquals(2, numberList.size());

        i = numberList.iterator();
        assertEquals(BigInteger.ZERO, i.next());
        assertEquals(BigInteger.ONE, i.next());
        assertFalse(i.hasNext());
    }

    @Test
    public void IteratorRemoveMiddleTest() {
        populate(numberList);

        Iterator i = numberList.iterator();
        i.next();
        i.next();
        i.remove();

        assertTrue(i.hasNext());
        assertEquals(BigInteger.TWO, i.next());
        assertFalse(i.hasNext());

        assertEquals(2, numberList.size());
        i = numberList.iterator();
        assertEquals(BigInteger.ZERO, i.next());
        assertEquals(BigInteger.TWO, i.next());
    }

    @Test
    public void IteratorToNewLinkedListTest() {
        populate(numberList);

        LinkedList<BigInteger> otherList = new LinkedList<>(numberList);

        Iterator<BigInteger> i = otherList.iterator();
        assertEquals(BigInteger.ZERO, i.next());
        i.remove();
        assertEquals(2, otherList.size());

        assertEquals(BigInteger.ONE, i.next());
        i.remove();
        assertEquals(1, otherList.size());

        assertEquals(BigInteger.TWO, i.next());
        i.remove();

        assertEmpty(otherList);
    }

    @Test
    public void EmptyReverseIteratorTest() {
        Iterator i = numberList.reverseIterator();
        assertFalse(i.hasNext());

        try {
            i.next();
            fail();
        } catch (NoSuchElementException e) { }
    }

    @Test
    public void SimpleReverseIteratorTest() {
        populate(numberList);

        Iterator i = numberList.reverseIterator();
        assertTrue(i.hasNext());
        assertEquals(BigInteger.TWO, i.next());
        assertEquals(BigInteger.ONE, i.next());
        assertEquals(BigInteger.ZERO, i.next());

        assertFalse(i.hasNext());
        try {
            i.next();
            fail();
        } catch (NoSuchElementException e) { }

        i = numberList.reverseIterator();
        assertTrue(i.hasNext());
        assertEquals(BigInteger.TWO, i.next());
        assertEquals(BigInteger.ONE, i.next());
        assertEquals(BigInteger.ZERO, i.next());

        assertFalse(i.hasNext());
        try {
            i.next();
            fail();
        } catch (NoSuchElementException e) { }

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(BigInteger.ONE, numberList.popFront());
        assertEquals(BigInteger.TWO, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void ReverseIteratorForEachTest() {
        populate(numberList);

        int i = 2;
        Iterator iter = numberList.reverseIterator();

        while (iter.hasNext()) {
            assertEquals(BigInteger.valueOf(i--), iter.next());
        }

        i = 2;
        iter = numberList.reverseIterator();
        while (iter.hasNext()) {
            assertEquals(BigInteger.valueOf(i--), iter.next());
        }

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(BigInteger.ONE, numberList.popFront());
        assertEquals(BigInteger.TWO, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void ReverseIteratorRemoveThrowsWhenNextNotCalledTest() {
        populate(numberList);

        Iterator i = numberList.reverseIterator();
        assertTrue(i.hasNext());

        try {
            i.remove();
            fail();
        } catch (IllegalStateException e) { }

        assertEquals(3, numberList.size());
        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(BigInteger.ONE, numberList.popFront());
        assertEquals(BigInteger.TWO, numberList.popFront());

        assertEmpty(numberList);
    }

    @Test
    public void ReverseIteratorRemoveBackTest() {
        populate(numberList);

        Iterator i = numberList.reverseIterator();
        assertTrue(i.hasNext());

        assertEquals(BigInteger.TWO, i.next());
        i.remove();
        assertEquals(2, numberList.size());

        assertEquals(BigInteger.ONE, i.next());
        i.remove();
        assertEquals(1, numberList.size());

        assertEquals(BigInteger.ZERO, i.next());
        i.remove();

        assertEmpty(numberList);
    }

    @Test
    public void ReverseIteratorRemoveFrontTest() {
        populate(numberList);

        Iterator i = numberList.reverseIterator();
        i.next();
        i.next();
        i.next();

        assertFalse(i.hasNext());
        i.remove();
        assertEquals(2, numberList.size());

        i = numberList.iterator();
        assertEquals(BigInteger.ONE, i.next());
        assertEquals(BigInteger.TWO, i.next());
        assertFalse(i.hasNext());
    }

    @Test
    public void ReverseIteratorRemoveMiddleTest() {
        populate(numberList);

        Iterator i = numberList.reverseIterator();
        i.next();
        i.next();
        i.remove();

        assertTrue(i.hasNext());
        assertEquals(BigInteger.ZERO, i.next());
        assertFalse(i.hasNext());

        assertEquals(2, numberList.size());
        i = numberList.iterator();
        assertEquals(BigInteger.ZERO, i.next());
        assertEquals(BigInteger.TWO, i.next());
    }

    @Test
    public void LinkedListTest() {
        assertEmpty(numberList);

        numberList = new LinkedList<BigInteger>(
                Arrays.asList(BigInteger.ZERO, BigInteger.ONE, BigInteger.TWO));
        assertEquals(BigInteger.ZERO, numberList.peekFront());
        assertEquals(BigInteger.TWO, numberList.peekBack());
        assertEquals(BigInteger.ZERO, numberList.popFront());
        assertEquals(BigInteger.TWO, numberList.popBack());
        assertEquals(1, numberList.size());

        numberList.pushBack(BigInteger.valueOf(100));
        numberList.pushFront(BigInteger.valueOf(-100));
        numberList.remove(1);

        assertEquals(2, numberList.size());
        Iterator i = numberList.iterator();
        assertEquals(BigInteger.valueOf(-100), i.next());
        assertEquals(BigInteger.valueOf(100), i.next());

        i.remove();
        assertEquals(1, numberList.size());
        numberList.remove();

        assertEmpty(numberList);
    }
}
