package ua.nure.matchenko.practice6.part6;

import java.util.Locale;
import java.util.LinkedHashMap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Part63 {

    public static void main(String[] args) {
        HashSet wordSet = new HashSet();
        Set<String> set = new LinkedHashSet<>();
        Map<String, Integer> wordMap = new LinkedHashMap<>();

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(args[0])
                )
        )) {
            String line;
            String[] words;
            Integer frequency;
            while ((line = reader.readLine()) != null) {
                words = line.split(" ");
                for (String word : words) {
                    if (set.size() < 3 && wordSet.contains(word)) {
                        set.add(word);
                    }
                    frequency = wordMap.get(word);
                    if (wordMap.get(word) == null) {
                        wordMap.put(word, 1);
                    } else {
                        wordMap.put(word, ++frequency);
                    }
                    wordSet.add(word);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(wordMap);
        StringBuilder builder = new StringBuilder();
        StringBuilder wordBuilder;
        int count = 0;
            for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                if (entry.getValue() > 1) {
                    wordBuilder = new StringBuilder();
                    wordBuilder.append(entry.getKey().toUpperCase(Locale.ENGLISH))
                            .reverse();
                    builder.append(wordBuilder)
                            .append("\n");
                    count++;
                }
                if (count == 3) {
                    break;
                }
            }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder);
    }
}
