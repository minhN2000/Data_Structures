package heaps;

import lib.Pair;

import java.util.Queue;

/**
 * A Priority Queue is a kind of queue where every element has a priority,
 * and the "highest priority" (lower values are higher priority) elements
 * are always removed first. We will be using Integers as priorities.
 *
 * In this Priority Queue implementation, the internal ordering of elements
 * of equal priority is arbitrary. In other words, elements inserted with
 * equal priority are not necessarily removed in FIFO order. We only need
 * to guarantee that the highest priority elements are removed before lower
 * priority elements.
 *
 * @param <T> The type of the elements stored in the queue.
 *
 */
public class PriorityQueue<T> {

    private static class ComparablePair<L extends Comparable<L>, R>
            extends Pair<L, R>
            implements Comparable<ComparablePair<L, R>> {

        public ComparablePair(L left, R right) {
            super(left, right);
        }

        @Override
        public int compareTo(ComparablePair<L, R> o) {
            /* YOUR CODE HERE */
            if(o.left.compareTo(this.left) < 0) return 1;
            else if(o.left.compareTo(this.left) == 0) return 0;
            return -1;
        }
    }

    MinHeap<ComparablePair<Integer, T>> heap = new MinHeap<>();

    public void insert(Integer priority, T value) {
        heap.insert(new ComparablePair<>(priority,value));
    }

    public T peek() {
        return heap.peek().right;
    }

    public T remove() {
        return heap.remove().right;
    }

    public int size() {
        return heap.size();
    }
}
