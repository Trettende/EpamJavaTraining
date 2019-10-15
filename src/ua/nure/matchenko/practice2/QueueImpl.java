package ua.nure.matchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Object[] data;
    private int size;
    private int capacity = 4;

    public QueueImpl() {
        data = new Object[capacity];
    }

    @Override
    public void enqueue(Object element) {
        if (size + 1 == capacity) {
            capacity = capacity * 2 + 1;
            Object[] newData = new Object[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size++] = element;
    }

    @Override
    public Object dequeue() {
        Object head = data[0];
        data[0] = null;
        System.arraycopy(data, 1, data, 0, size - 1);
        data[size--] = null;
        return head;
    }

    @Override
    public Object top() {
        return data[0];
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currentIndex = -1;
        private int nextIndex;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            currentIndex = nextIndex;
            nextIndex++;
            return data[currentIndex];
        }

        @Override
        public void remove() {
            if (currentIndex == -1) {
                throw new IllegalStateException();
            }
            System.arraycopy(data, nextIndex, data, currentIndex, size - 1 - currentIndex);
            nextIndex = currentIndex;
            data[size--] = null;
            currentIndex = -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(data[i] + ", ");
        }
        if (size > 0) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        //main method for Jenkins
    }
}
