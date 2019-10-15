package ua.nure.matchenko.practice6.part1;

import java.io.ByteArrayInputStream;

public class Part1 {
    public static void main(String[] args) {
        System.setIn(new ByteArrayInputStream(
                "q^a^a^r^qwe^qwa^qws^s^d^q^a^d^stop^qwe".replace("^",
                        System.lineSeparator()).getBytes()));
        System.setIn(new ByteArrayInputStream(
                ("asd 43 asdf asd 43\n" +
                        "434 asdf\n" +
                        "kasdf asdf stop asdf").getBytes()
        ));
        WordContainer.main(args);
        System.setIn(System.in);
    }

}
