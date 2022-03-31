package linkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private class ListNode<T> {
        private T payload;
        private ListNode<T> next;
        private ListNode<T> prev;

        public ListNode(T v) {
            this.payload = v;
        }
    }

    protected ListNode<T> frontNode;
    protected ListNode<T> backNode;
    protected int size;

    public LinkedList() {
        frontNode = null;
        backNode = null;
        size = 0;
    }

    public LinkedList(Iterable<? extends T> c) {
        for(T val : c) {
            if(frontNode == null) {
                frontNode = new ListNode<>(val);
                backNode = frontNode;
            } else if (frontNode == backNode) { // or size = 1
                backNode = new ListNode<>(val);
                frontNode.next = backNode;
                backNode.prev = frontNode;// or oldBackNode (same)
            } else {
                ListNode<T> oldBackNode = backNode;
                backNode = new ListNode<>(val);
                backNode.prev =  oldBackNode;
                oldBackNode.next = backNode;
            }
            size ++;
        }
    }

    public T peekFront() {
        return (frontNode != null ) ? frontNode.payload : null;
    }

    public T peekBack() {
        return (backNode != null) ? backNode.payload : null;
    }

    public T popFront() {
        T res;
        if(size == 0) throw new NoSuchElementException();
        else if(size == 1) {
            res = frontNode.payload;
            frontNode = null;
            backNode = frontNode;
        }else {
            res = frontNode.payload;
            frontNode = frontNode.next;
            frontNode.prev = null;
        }
        size--;
        return res;
    }

    public T popBack() {
        T res;
        if (size == 0) throw new NoSuchElementException();
        else if( size == 1){
            res = backNode.payload;
            backNode = null;
            frontNode = backNode;
        }else {
            res = backNode.payload;
            backNode = backNode.prev;
            backNode.next = null;
        }
        size--;
        return res;
    }

    public void pushBack(T value) {
        if(size == 0) {
            backNode = new ListNode<>(value);
            frontNode = backNode;
        } else if(size == 1) {
            backNode = new ListNode<>(value);
            frontNode.next = backNode;
            backNode.prev = frontNode;
        } else {
            ListNode<T> oldBackNode = backNode;
            backNode = new ListNode<>(value);
            backNode.prev =  oldBackNode;
            oldBackNode.next = backNode;
        }
        size++;
    }

    public void pushFront(T value) {
        if(size == 0) {
            frontNode = new ListNode<>(value);
            backNode = frontNode;
        } else if(size == 1) {
            frontNode = new ListNode<>(value);
            frontNode.next = backNode;
            backNode.prev = frontNode;
        } else {
            ListNode<T> oldFrontNode = frontNode;
            frontNode = new ListNode<>(value);
            frontNode.next = oldFrontNode;
            oldFrontNode.prev = frontNode;
        }
        size++;
    }

    public void add(T value) {
        pushFront(value);
    }

    public void add(int index, T value) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if(index == 0) {
            pushFront(value);
        } else if(index == size) {
            pushBack(value);
        } else {
            ListNode<T> curr = frontNode;
            for(int i = 0; i < index; ++i) {
                curr = curr.next;
            }
            ListNode<T> oldNode = curr;
            curr = new ListNode<>(value);
            curr.next = oldNode;
            curr.prev = oldNode.prev;
            oldNode.prev.next = curr;
            oldNode.prev = curr;
            size++;
        }
    }

    public T remove() {
        return popFront();
    }

    public T remove(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if(index == 0) {
            return popFront();
        } else if(index == size - 1) {
            return popBack();
        } else {
            ListNode<T> curr = backNode;
            for(int i = 1; i < (size - index); ++i) {
                curr = curr.prev;
            }
            T res = curr.payload;
            ListNode<T> oldNode = curr;
            curr = oldNode.next;
            curr.prev = oldNode.prev;
            oldNode.prev.next = curr;
            size--;
            return res;
        }
    }

    private void remove(ListNode<T> node) {
        if(size == 1) {
            frontNode = null;
            backNode = frontNode;
        } else {
            if(node == frontNode) {
                frontNode = frontNode.next;
                frontNode.prev = null;
            } else if(node == backNode) {
                backNode = backNode.prev;
                backNode.next = null;
            } else {
                ListNode<T> curr = frontNode;
                while (curr.next != null) {
                    curr = curr.next;
                    if(curr == node) {
                        break;
                    }
                }
                // copy remove(index):
                ListNode<T> oldNode = curr;
                curr = oldNode.next;
                curr.prev = oldNode.prev;
                oldNode.prev.next = curr;
            }
        }
        size--;
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(frontNode);
    }

    public class LinkedListIterator implements Iterator<T> {
        private ListNode<T> checkRemove;
        private ListNode<T> frontNode;

        public LinkedListIterator(ListNode<T> frontNode) {
            this.frontNode = frontNode;
        }
        @Override
        public boolean hasNext() {
            return frontNode != null;
        }

        @Override
        public T next() {
            if(hasNext()) {
                T res = frontNode.payload;
                checkRemove = frontNode;
                frontNode = frontNode.next;
                return res;
            } else throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (checkRemove == null) throw new IllegalStateException();
            else LinkedList.this.remove(checkRemove);
        }
    }

    public Iterator<T> reverseIterator() {
        return new LinkedListReverseIterator(backNode);
    }

    public class LinkedListReverseIterator implements Iterator<T> {
        private ListNode<T> checkRemove;
        private ListNode<T> backNode;

        public LinkedListReverseIterator(ListNode<T> backNode) {
            this.backNode = backNode;
        }
        @Override
        public boolean hasNext() {
            return backNode != null;
        }

        @Override
        public T next() {
            if(hasNext()) {
                T res = backNode.payload;
                checkRemove = backNode;
                backNode = backNode.prev;
                return res;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(checkRemove == null) throw new IllegalStateException();
            else LinkedList.this.remove(checkRemove);
        }
    }


    // toString requires Iterator to be partially implemented to function
    // as it uses the for-each loop construct
    @Override
    public String toString() {
        String result = "[";

        for (T value : this) {
            result += String.format("%s, ", value.toString());
        }

        if (result.length() > 1) {
            result = result.substring(0, result.length() - 2);
        }

        result += "]";
        return result;
    }
}
