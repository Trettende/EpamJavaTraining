package ua.nure.matchenko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static String convert(String input) {
        Pattern pattern = Pattern.compile("(\\p{Alpha}+)",
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        int minLength = input.length();
        int maxLength = 0;
        while (matcher.find()) {
            if (matcher.group().length() < minLength) {
                minLength = matcher.group().length();
            }
            if (matcher.group().length() > maxLength) {
                maxLength = matcher.group().length();
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Min: ");
        Matcher matcherMin = pattern.matcher(input);
        while (matcherMin.find()) {
            if (matcherMin.group().length() == minLength &&
                    !builder.toString().contains(matcherMin.group())) {
                builder.append(matcherMin.group());
                builder.append(", ");
            }
        }
        builder.delete(builder.length() - 2, builder.length());

        builder.append("\nMax: ");
        Matcher matcherMax = pattern.matcher(input);
        while (matcherMax.find()) {
            if (matcherMax.group().length() == maxLength &&
                    !builder.toString().contains(matcherMax.group())) {
                builder.append(matcherMax.group());
                builder.append(", ");
            }
        }
        builder.delete(builder.length() - 2, builder.length());

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert(Util.readFile("3part2.txt")));
    }
}
