package ua.nure.matchenko.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public static final int RANDOM_BOUND = 10000;
    public static final int MAX_SIZE = 1024;

    public static String convert1(String input) {
        Pattern pattern = Pattern.compile("(?m)^(\\w+);(\\w+\\s\\w+);(\\S+)$",
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            builder.append(matcher.group(1))
                    .append(": ")
                    .append(matcher.group(3))
                    .append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    public static String convert2(String input) {
        Pattern pattern = Pattern.compile("(?m)^(\\w+);(\\w+)\\s(\\w+);(\\S+)$",
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            builder.append(matcher.group(3))
                    .append(" ")
                    .append(matcher.group(2))
                    .append(" (email: ")
                    .append(matcher.group(4))
                    .append(")")
                    .append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    public static String convert3(String input) {
        Pattern pattern = Pattern.compile("(\\w+);(.*)@(\\S+)",
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        String[] arr = new String[MAX_SIZE];
        int index = 0;
        boolean exists = false;
        while (matcher.find()) {
            for (int i = 0; i < index; i++) {
                if (matcher.group(3).equals(arr[i])) {
                    exists = true;
                }
            }
            if (!exists) {
                arr[index++] = matcher.group(3);
                builder.append( arr[index - 1])
                        .append(" ==> ");
                Matcher matcher2 = pattern.matcher(input);
                while (matcher2.find()) {
                    if (matcher2.group().contains(arr[index - 1])) {
                        builder.append(matcher2.group(1));
                        builder.append(", ");
                    }
                }
                builder.delete(builder.length() - 2, builder.length());
                builder.append("\n");
            }
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    public static String convert4(String input) {
        Pattern pattern = Pattern.compile("(?m)^(.*);(.*);(.*)$");
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String password;
        while (matcher.find()) {
            password = String.format("%04d", random.nextInt(RANDOM_BOUND));
            builder.append(matcher.group())
                    .append(";");
            if (matcher.group(3).contains("@")) {
                builder.append(password);
            } else {
                builder.append("Password");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        //main
    }
}
