package ua.nure.matchenko.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
    private int n;
    private int m;
    private boolean reverse;

    public Range(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public Range(int n, int m, boolean reverse) {
        this.n = n;
        this.m = m;
        this.reverse = reverse;
    }

    @Override
    public Iterator<Integer> iterator() {
        int step = !reverse ? 1 : -1;
        return new Iterator<Integer>() {
            private int current = !reverse ? (n - 1) : (m + 1);
            private int end = !reverse ? m : n;
            @Override
            public boolean hasNext() {
                return current * step < end * step;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    current += step;
                    return current;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
