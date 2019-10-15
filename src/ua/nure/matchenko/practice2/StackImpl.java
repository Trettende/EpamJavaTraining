package ua.nure.matchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private Object[] data;
    private int size;
    private int capacity = 4;


    public StackImpl() {
        data = new Object[capacity];
    }

    @Override
    public void push(Object element) {
        if (size + 1 == capacity) {
            capacity = capacity * 2 + 1;
            Object[] newData = new Object[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size++] = element;
    }

    @Override
    public Object pop() {
        Object top = data[size - 1];
        data[--size] = null;
        return top;
    }

    @Override
    public Object top() {
        return data[size - 1];
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

        private int currentIndex = size;
        private int nextIndex = size - 1;

        @Override
        public boolean hasNext() {
            return nextIndex > -1;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            currentIndex = nextIndex;
            nextIndex--;
            return data[currentIndex];
        }

        @Override
        public void remove() {
            if (currentIndex == size) {
                throw new IllegalStateException();
            }
            if (currentIndex != size - 1) {
                System.arraycopy(data, currentIndex + 1, data, currentIndex, size - 1 - currentIndex);
            }
            data[--size] = null;
            currentIndex = size;
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
