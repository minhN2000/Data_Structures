package linkedlist;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;

public class Queue<T> extends LinkedList<T> {
    public Queue() { }

    @Override
    public void add(T value) {
        super.pushBack(value);
    }

    @Override
    public T remove() {
        return super.popFront();
    }

    public T peek() {
        return super.peekFront();
    }

    public void add(int index, T value) { throw new RuntimeException();}

    public T remove (int index) { throw new RuntimeException();}

    public T peekFront() { throw new RuntimeException();}

    public T peekBack() { throw new RuntimeException();}

    public T popFront() { throw new RuntimeException();}

    public T popBack() { throw new RuntimeException();}

    public Iterator<T> iterator() { throw new RuntimeException();}

    public Iterator<T> reverseIterator() { throw new RuntimeException();}
}
