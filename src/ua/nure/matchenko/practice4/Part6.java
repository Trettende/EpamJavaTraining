package ua.nure.matchenko.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private static final String ENCODING = "Cp1251";

    private static void printAppropriateWords() {
        Scanner scanner = new Scanner(System.in);
        Pattern pattern;
        String regex = null;
        Matcher matcher;
        StringBuilder builder;
        boolean flag = scanner.hasNext();
        while (flag) {
            builder = new StringBuilder();
            String input = scanner.next();
            switch (input) {
                case "Latn":
                case "latn":
                    regex = "([a-zA-Z]+)";
                    builder.append(input)
                            .append(": ");
                    break;
                case "Cyrl":
                case "cyrl":
                    regex = "([à-ÿÀ-ß¸¨¥´ªº²³¯¿]+)";
                    builder.append(input)
                            .append(": ");
                    break;
                case "Stop":
                case "stop":
                    flag = false;
                    break;
                default:
                    builder.append(input)
                            .append(": Incorrect input");
                    break;
            }

            if (regex != null) {
                pattern = Pattern.compile(regex,
                        Pattern.UNICODE_CHARACTER_CLASS);
                matcher = pattern.matcher(readFile("4part6.txt"));
                while (matcher.find()) {
                    builder.append(matcher.group())
                            .append(" ");
                }
                regex = null;
            }
            System.out.println(builder);
        }
    }

    private static String readFile(String path) {
        String line;
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), ENCODING
                )
        )) {
            while ((line = reader.readLine()) != null) {
                builder.append(line)
                        .append(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        printAppropriateWords();
    }
}
