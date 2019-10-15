package ua.nure.matchenko.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String ENCODING = "Cp1251";

    private static void printAppropriateTypes() {
        Scanner scanner = new Scanner(System.in);
        String input;
        String regex = null;
        int group = 1;
        boolean flag = scanner.hasNext();
        while (flag) {
            input = scanner.next();
            switch (input) {
                case "char" :
                    regex = "(\\b\\p{Alpha}\\b)";
                    break;
                case "String" :
                    regex = "(\\p{Alpha}{2,})";
                    break;
                case "int" :
                    regex = "(\\s|\\G)(\\d+)(\\s)";
                    group = 2;
                    break;
                case "double" :
                    regex = "(\\d*\\.\\d*)";
                    break;
                case "stop" :
                    flag = false;
                    break;
                default:
                    System.out.println("Incorrect input");
                    break;
            }

            if (regex != null) {
                useRegex(regex, group);
                regex = null;
                group = 1;
            }
        }
    }

    private static void useRegex(String regex, int group) {
        StringBuilder builder = new StringBuilder();
        Pattern pattern = Pattern.compile(regex,
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(readFile("4part3.txt"));
        while (matcher.find()) {
            builder.append(matcher.group(group))
                    .append(" ");
        }
        if (builder.length() == 0) {
            builder.append("No such values");
        }
        System.out.println(builder);
    }

    private static String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), ENCODING
                )
        )) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        printAppropriateTypes();
    }
}
