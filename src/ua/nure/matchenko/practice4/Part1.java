package ua.nure.matchenko.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    private static final String ENCODING = "Cp1251";

    private static String invertSymbolsOfLongWords(String path) {
        StringBuilder builder = new StringBuilder();
        String line;
        Pattern pattern = Pattern.compile("([^\\p{Alpha}]*)(\\p{Alpha}+)([^\\p{Alpha}]*)",
                Pattern.UNICODE_CHARACTER_CLASS);

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path), ENCODING))) {
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    char[] chars = matcher.group(2).toCharArray();
                    if (chars.length > 3) {
                        changeCase(chars);
                    }
                    builder.append(matcher.group(1))
                            .append(chars)
                            .append(matcher.group(3));
                }
                builder.append("\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    private static void changeCase(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i])) {
                chars[i] = Character.toLowerCase(chars[i]);
            } else {
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(invertSymbolsOfLongWords("4part1.txt"));
    }
}
