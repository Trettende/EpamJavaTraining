package ua.nure.matchenko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    public static String convert(String input) {
        Pattern pattern = Pattern.compile("(\\w+)",
                Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String regex2 = "(\\b" + matcher.group() + "\\b)";
            Pattern pattern2 = Pattern.compile(regex2,
                    Pattern.UNICODE_CHARACTER_CLASS);
            Matcher matcher2 = pattern2.matcher(input);
            int cnt = 0;
            while (matcher2.find()) {
                cnt++;
                if (cnt > 1) {
                    input = input.replaceAll(regex2, "_" + "$1");
                    break;
                }
            }
        }

        return input;
    }

    public static void main(String[] args) {
        System.out.println(Util.readFile("3part6.txt"));
        System.out.println(convert(Util.readFile("3part6.txt")));
    }
}