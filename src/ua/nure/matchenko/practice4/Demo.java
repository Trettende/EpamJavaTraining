package ua.nure.matchenko.practice4;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Demo {
    private static final InputStream STD_IN = System.in;

    public static void main(String[] args) {
        System.out.println("=========================== PART1");
        Part1.main(args);

        System.out.println("=========================== PART2");
        Part2.main(args);

        System.out.println("=========================== PART3");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "char^String^int^double^stop".replace("^", System.lineSeparator()).getBytes()));
        Part3.main(args);
        // restore the standard input
        System.setIn(STD_IN);

        System.out.println("=========================== PART4");
        Part4.main(args);

        System.out.println("=========================== PART5");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "apple ru^apple en^asdf^table ru^table en^stop".replace("^", System.lineSeparator()).getBytes()));
        Part5.main(args);
        // restore the standard input
        System.setIn(STD_IN);

        System.out.println("=========================== PART6");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "Latn^Cyrl^asdf^latn^cyrl^stop".replace("^", System.lineSeparator()).getBytes()));
        Part6.main(args);
        // restore the standard input
        System.setIn(STD_IN);
    }
}
