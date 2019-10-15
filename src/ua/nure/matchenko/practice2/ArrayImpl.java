package ua.nure.matchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] data;
    private int capacity = 4;
    private int size;

    public ArrayImpl() {
        data = new Object[capacity];
    }

    @Override
    public void add(Object element) {
        if (size + 1 == capacity) {
            capacity = capacity * 2 + 1;
            Object[] newData = new Object[capacity];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
        }
        data[size++] = element;
    }

    @Override
    public void set(int index, Object element) {
        indexCheck(index);
        data[index] = element;
    }

    @Override
    public Object get(int index) {
        indexCheck(index);
        return data[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(data[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        indexCheck(index);
        if (index != size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[--size] = null;
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

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
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
            ArrayImpl.this.remove(currentIndex);
            nextIndex = currentIndex;
            currentIndex = -1;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                builder.append(data[i] + ", ");
            }
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        //main method for Jenkins
    }
}
