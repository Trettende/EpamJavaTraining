package ua.nure.matchenko.practice6.part3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parking {
    private List<Integer> places;

    public Parking(int size) {
        places = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            places.add(0);
        }
    }

    private int getSize() {
        return places.size();
    }

    boolean arrive(int k) {
        if (k < 0 || k > getSize() - 1) {
            throw new IllegalArgumentException();
        }
        int index;
        for (int i = 0; i < getSize(); i++) {
            index = k + i < getSize() ? (k + i) : -(getSize() - k - i);
            if (places.get(index) == 0) {
                places.set(index, 1);
                return true;
            }
        }
      return false;
    }

    boolean depart(int k) {
        if (k < 0 || k > getSize() - 1) {
            throw new IllegalArgumentException();
        }
        if (places.get(k) == 1) {
            places.set(k, 0);
            return true;
        }
        return false;
    }

    void print() {
        //System.out.println(places);
        StringBuilder builder = new StringBuilder();
        Iterator iterator = places.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }
        System.out.println(builder);
    }
}
