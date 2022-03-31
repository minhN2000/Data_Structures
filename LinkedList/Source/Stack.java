package linkedlist;

public class Stack<T> {
    private LinkedList<T> list;

    public Stack() {
        this.list = new LinkedList<>();
    }

    public void push(T value) {
        list.pushFront(value);
    }

    public T pop() {
        return list.popFront();
    }

    public T peek() {
        return list.peekFront();
    }

    public int size() {
        return list.size();
    }
}
