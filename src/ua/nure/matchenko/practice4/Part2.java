package ua.nure.matchenko.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.security.SecureRandom;

public class Part2 {

    private static final String ENCODING = "Cp1251";
    public static final int RANDOM_BOUND = 51;

    public static void createFile(String path) {

        try(PrintWriter out = new PrintWriter(
                new FileWriter(
                        new File(path)
                )
        )) {
            SecureRandom random = new SecureRandom();
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < 10; i++) {
                builder.append(random.nextInt(RANDOM_BOUND))
                        .append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            out.print(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sortFile(String path) {

        String[] stringInts = readFile(path).split(" ");
        int[] ints = new int[stringInts.length];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(stringInts[i]);
        }

        boolean isSorted = false;
        int tmp;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < ints.length - 1; i++) {
                if (ints[i] > ints[i + 1]) {
                    isSorted = false;
                    tmp = ints[i];
                    ints[i] = ints[i + 1];
                    ints[i + 1] = tmp;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i : ints) {
            builder.append(i)
                    .append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);

        try(PrintWriter writer = new PrintWriter(
                new FileWriter(
                        new File("4part2_sorted.txt")
                )
        )) {
            writer.print(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path) {
        String line = "";
        try(BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), ENCODING))) {
            line = in.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public static void main(String[] args) {
        String path = "4part2.txt";
        createFile(path);
        System.out.println(readFile(path));
        sortFile(path);
        System.out.println(readFile("4part2_sorted.txt"));
    }
}
