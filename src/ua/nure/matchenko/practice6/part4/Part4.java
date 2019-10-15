package ua.nure.matchenko.practice6.part4;

public class Part4 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        if (n >= m) {
            throw new IllegalArgumentException();
        }
        Range range = new Range(n, m);
        for (Integer el : range) {
            System.out.printf("%d ", el);
        }
        System.out.println();
        // result: 3 4 5 6 7 8 9 10

        range = new Range(n, m, true);
        for (Integer el : range) {
            System.out.printf("%d ", el);
        }
        System.out.println();
        // result: 10 9 8 7 6 5 4 3
    }
}
