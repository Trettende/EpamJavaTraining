package ua.nure.matchenko.practice6;

import ua.nure.matchenko.practice6.part1.Part1;
import ua.nure.matchenko.practice6.part2.Part2;
import ua.nure.matchenko.practice6.part3.Part3;
import ua.nure.matchenko.practice6.part4.Part4;
import ua.nure.matchenko.practice6.part5.Part5;
import ua.nure.matchenko.practice6.part6.Part6;

public class Demo {
    public static void main(String[] args) {
        System.out.println("~~~~~~~~~~~~Part1");
        Part1.main(new String[]{});
        System.out.println("~~~~~~~~~~~~Part2");
        Part2.main(new String[]{"10000", "4"});
        System.out.println("~~~~~~~~~~~~Part3");
        Part3.main(new String[]{"4"});
        System.out.println("~~~~~~~~~~~~Part4");
        Part4.main(new String[]{"3", "10"});
        System.out.println("~~~~~~~~~~~~Part5");
        Part5.main(new String[]{});
        System.out.println("~~~~~~~~~~~~Part6");
        Part6.main(new String[] {"--input", "6part6.txt", "--task", "frequency"});
        Part6.main(new String[] {"--input", "6part6.txt", "--task", "length"});
        Part6.main(new String[] {"--input", "6part6.txt", "--task", "duplicates"});
    }

}
