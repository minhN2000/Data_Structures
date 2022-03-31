package hashing;

import lib.Pair;

import java.lang.reflect.Array;
import java.util.*;

/**
 * HashMap implementation using hashing w/ linked list buckets.
 *
 * @param <K> Type of the keys.
 * @param <V> Type of the values.
 */
public class HashMap<K, V> implements Iterable<Pair<K, V>> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    // Normally this would be private, but we'll make it public
    // for testing purposes
    public LinkedList<Pair<K, V>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        table = (LinkedList<Pair<K, V>>[]) Array.newInstance(LinkedList.class, DEFAULT_CAPACITY);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        table = (LinkedList<Pair<K, V>>[]) Array.newInstance(LinkedList.class, initialCapacity);
        size = 0;
    }

    public int getSlot(K key) {
        return key == null ? 0 : (key.hashCode() % table.length);
    }

    public V put(K key, V value) {
        int slot = getSlot(key);
        if (table[slot] == null) {
            table[slot] = new LinkedList<>();
        }
        ListIterator<Pair<K,V>> i = table[slot].listIterator();
        while(i.hasNext()) {
            Pair<K,V> curr = i.next();
            if(curr.left != null) {
                if(curr.left.equals(key)) {
                    V val = curr.right;
                    i.set(new Pair<>(key,value));
                    return val;
                }
            } else if (curr.left == null && key == null) {
                V val = curr.right;
                i.set(new Pair<>(null, value));
                return val;
            }
        }

        table[slot].add(table[slot].size(), new Pair<>(key,value));
        size++;

        if(size / table.length > LOAD_FACTOR) this.expand();

        return null;

    }

    public V get(K key) {
        int slot = getSlot(key);
        if (table[slot] == null) {
            return null;
        }
        ListIterator<Pair<K,V>> i = table[slot].listIterator();
        while ((i.hasNext())) {
            Pair<K,V> curr = i.next();
            if(curr.left != null && curr.left.equals(key)) {
                return curr.right;
            } else if(curr.left == null && key == null) {
                return curr.right;
            }
        }
        return null;
    }

    public V remove(K key) {
        int slot = getSlot(key);
        if (table[slot] == null) {
            return null;
        }
        ListIterator<Pair<K, V>> i = table[slot].listIterator();
        while(i.hasNext()) {
            Pair<K,V> curr = i.next();
            if(curr.left != null && curr.left.equals(key)) {
                V value = curr.right;
                i.remove();
                size--;
                return value;
            } else if(curr.left == null && key == null) {
                V value = curr.right;
                i.remove();;
                size--;
                return value;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }


    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new HashMapIterator(this);
    }

    private class HashMapIterator implements Iterator<Pair<K, V>> {
        HashMap<K, V> hashMap;
        ArrayList<Pair<K,V>> list1 = new ArrayList<>();
        Pair<K,V> p;
        int i1;

        HashMapIterator(HashMap<K, V> hashMap) {
            this.hashMap = hashMap;
            for(int i = 0; i < table.length; i++) {
                if(table[i] != null) {
                    if(!table[i].isEmpty()) {
                        list1.addAll(table[i]);
                    }
                }
            }
            i1 = 0;
            p = null;
        }

        @Override
        public boolean hasNext() {
            return i1 < list1.size();
        }

        @Override
        public Pair<K, V> next() {
            if(i1 < list1.size()) {
                p = list1.get(i1++);
                return p;
            } else {
                p = null;
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(p != null) {
                hashMap.remove(p.left);
                p = null;
            } else throw new IllegalStateException();
        }
    }

    @SuppressWarnings("unchecked")
    private void expand() {
        LinkedList<Pair<K, V>>[] newTable = (LinkedList<Pair<K, V>>[]) Array.newInstance(LinkedList.class, table.length * 2);
        LinkedList<Pair<K,V>>[] tempTable = this.table;
        this.table = newTable;
        size = 0;
        for(LinkedList<Pair<K,V>> l : tempTable) {
            if(l != null) {
                ListIterator<Pair<K, V>> i = l.listIterator();
                while(i.hasNext()) {
                    Pair<K,V> curr = i.next();
                    put(curr.left,curr.right);
                }
            }
        }

        this.table = newTable;
    }

}
