package ua.nure.matchenko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static String convert(String input) {
        Pattern pattern = Pattern.compile("(\\p{Alpha}+)(\\s*\\p{Punct}*\\s*|$)",
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            if (matcher.group(1).length() > 2) {
                char[] chars = matcher.group(1).toCharArray();
                if (Character.isUpperCase(chars[0])) {
                    chars[0] = Character.toLowerCase(chars[0]);
                } else {
                    chars[0] = Character.toUpperCase(chars[0]);
                }
                builder.append(chars);
            } else {
                builder.append(matcher.group(1));
            }
            builder.append(matcher.group(2));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert(Util.readFile("3part3.txt")));
    }
}
