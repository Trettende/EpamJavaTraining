package ua.nure.matchenko.practice6.part2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Part2 {
    private static void fillList(List<Integer> list, int n) {
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
    }

    public static long removeByIndex(List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        int indexOfRemoved = 0;
        while (list.size() != 1) {
            indexOfRemoved = (k + indexOfRemoved - 1) % list.size();
            list.remove(indexOfRemoved);
            //System.out.println(list);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static long removeByIterator(List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = list.iterator();
        int a1 = 0;
        int a2 = 0;
        while (list.size() != 1) {
            try {
                for (int i = 0; i < k - a2; i++) {
                    iterator.next();
                    a1 = i + 1;
                }
                iterator.remove();
                //System.out.println(list);
                a1 = 0;
                a2 = 0;
            } catch (NoSuchElementException e) {
                a2 = a1 + a2;
                iterator = list.iterator();
            }
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    /*public static long removeByIterator(List<Integer> list, int k) {
        long start = System.currentTimeMillis();
        Iterator<Integer> iterator = new Iterator<Integer>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return list.size() > 1;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new IllegalStateException();
                }
                index = (index + k - 1) % list.size();
                return list.get(index);
                //return null;
            }

            @Override
            public void remove() {
                list.remove(index);
                //list.remove(next());
            }
        };
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            //System.out.println(list);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }*/

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        fillList(arrayList, n);
        //System.out.println(arrayList);
        System.out.printf("ArrayList#Index: %d ms%n", removeByIndex(arrayList, k));

        fillList(linkedList, n);
        //System.out.println(linkedList);
        System.out.printf("LinkedList#Index: %d ms%n", removeByIndex(linkedList, k));

        arrayList = new ArrayList<>();
        fillList(arrayList, n);
        //System.out.println(arrayList);
        System.out.printf("ArrayList#Iterator: %d ms%n", removeByIterator(arrayList, k));

        linkedList = new LinkedList<>();
        fillList(linkedList, n);
        //System.out.println(linkedList);
        System.out.printf("LinkedList#Iterator: %d ms%n", removeByIterator(linkedList, k));
    }
}