package heaps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Min-heap implementation where the smallest element is the root of the heap.
 * The heap should be stored in an ArrayList<T> container, with a null element
 * at index 0, and the root element starting at index 1. Use the indexing
 * formulas discussed in class to retrieve and assign child nodes.
 *
 * @param <T> The generic type stored in the heap. Must be a Comparable type.
 *           For more information on the Comparable interface, please read:
 *           https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Comparable.html
 */
public class MinHeap<T extends Comparable<T>> {
    public ArrayList<T> container = new ArrayList<>();
    /* YOUR CODE HERE */

    public MinHeap() {
        container.add(0, null);
    }

    public MinHeap(Collection<T> collection) {
        container.add(0, null);
    }

    private int getParentIndex(int childIndex) {
        return childIndex / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return parentIndex * 2;
    }

    private int getRightChildIndex(int parentIndex) {
        return getLeftChildIndex(parentIndex) + 1;
    }

    private void swap(int left, int right) {
        T leftValue = container.get(left);
        container.set(left, container.get(right));
        container.set(right, leftValue);
    }

    /**
     * Inserts a value into the heap, "bubbling up" to the correct position.
     * @param value The value to be inserted.
     */
    public void insert(T value) {
        if(container.size() == 1) container.add(value);
        else {
            container.add(value);
            int i = container.size() - 1;
            int pI = getParentIndex(i);// parent index
            while(i != 1) {
                if(container.get(pI).compareTo(container.get(i)) > 0) {
                    swap(i,pI);
                    i = pI;
                    pI = getParentIndex(i);
                } else break;
            }
        }

    }

    public T peek() {
        if(container.size() == 1) throw new NoSuchElementException();

        return container.get(1);
    }

    public T remove() {
        if(container.size()==1){
            throw new NoSuchElementException();
        }
        else{
            T res= peek();
            swap(1,container.size()-1);
            container.remove(container.size()-1);
            int i = 1;
            while(getLeftChildIndex(i)<container.size()){
                int j= getLeftChildIndex(i);
                if(getRightChildIndex(i)<container.size()){
                    T rightVal = container.get(getRightChildIndex(i));
                    T leftVal = container.get(getLeftChildIndex(i));
                    if(container.get(getRightChildIndex(i)).compareTo(container.get(getLeftChildIndex(i))) < 0){
                        j = getRightChildIndex(i);
                    }
                }
                if(container.get(i).compareTo(container.get(j))>0){
                    swap(i,j);
                }
                else {
                    break;
                }
                i = j;
            }
            return res;

        }
    }

    public int size() {
        return container.size()-1;
    }
}
