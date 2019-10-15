package ua.nure.matchenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private Node firstNode;
    private Node lastNode;
    private int size;

    @Override
    public void addFirst(Object element) {
        Node tmpFirst = firstNode;
        Node node = new Node(null, element, tmpFirst);
        firstNode = node;
        if (tmpFirst == null) {
            lastNode = node;
        } else {
            tmpFirst.prev = node;
        }
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node tmpLast = lastNode;
        Node node = new Node(tmpLast, element, null);
        lastNode = node;
        if (tmpLast == null) {
            firstNode = node;
        } else {
            tmpLast.next = node;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        Node tmpFirst = firstNode;
        Node tmpNext = tmpFirst.next;
        firstNode = tmpNext;
        if (tmpNext == null) {
            lastNode = null;
        } else {
            tmpNext.prev = null;
        }
        size--;
    }

    @Override
    public void removeLast() {
        Node tmpLast = lastNode;
        Node tmpPrev = tmpLast.prev;
        lastNode = tmpPrev;
        if (tmpLast.prev == null) {
            firstNode = null;
        } else {
            tmpPrev.next = null;
        }
        size--;
    }

    @Override
    public Object getFirst() {
        return firstNode.item;
    }

    @Override
    public Object getLast() {
        return lastNode.item;
    }

    @Override
    public Object search(Object element) {
        if (element != null) {
            for (Node node = firstNode; node != null; node = node.next) {
                if (element.equals(node.item)) {
                    return node.item;
                }
            }
        } else {
            for (Node node = firstNode; node != null; node = node.next) {
                if (node.item == null) {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (element != null) {
            for (Node node = firstNode; node != null; node = node.next) {
                if (element.equals(node.item)) {
                    unlink(node);
                    return true;
                }
            }
        } else {
            for (Node node = firstNode; node != null; node = node.next) {
                if (node.item == null) {
                    unlink(node);
                    return true;
                }
            }
        }
        return false;
    }

    private void unlink(Node node) {
        Node tmpNext = node.next;
        Node tmpPrev = node.prev;

        if (tmpPrev == null) {
            firstNode = tmpNext;
        } else {
            tmpPrev.next = tmpNext;
            node.prev = null;
        }

        if (tmpNext == null) {
            lastNode = tmpPrev;
        } else {
            tmpNext.prev = tmpPrev;
            node.next = null;
        }

        node.item = null;
        size--;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (Node node = firstNode; node != null; node = node.next) {
            builder.append(node.item + ", ");
        }
        if (firstNode != null) {
            builder.delete(builder.length() - 2, builder.length());
        }
        builder.append("]");
        return builder.toString();
    }

    private static class Node {
        private Object item;
        private Node next;
        private Node prev;

        public Node(Node prev, Object item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private class IteratorImpl implements Iterator<Object> {
        private int index;
        private Node currentNode;
        private Node nextNode;

        public IteratorImpl() {
            nextNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            currentNode = nextNode;
            nextNode = currentNode.next;
            index++;
            return currentNode.item;
        }

        @Override
        public void remove() {
            if (currentNode == null) {
                throw new IllegalStateException();
            }
            unlink(currentNode);
            currentNode = null;
            index--;
        }
    }

    public static void main(String[] args) {
        //main method for Jenkins
    }
}
