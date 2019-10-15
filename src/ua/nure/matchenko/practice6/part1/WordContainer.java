package ua.nure.matchenko.practice6.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordContainer {
    private final List<Word> wordSet = new ArrayList<>();

    private void read() {
        String line;
        String[] strings;
        boolean isStop = false;
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in))) {
            while (!isStop && (line = reader.readLine()) != null) {
                strings = line.split(" ");
                for (String s : strings) {
                    if ("stop".equals(s)) {
                        isStop = true;
                        break;
                    } else {
                        Word word = new Word(s);
                        if (!wordSet.contains(word)){
                            wordSet.add(word);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Word word : wordSet) {
            builder.append(word)
                    .append("\n");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        WordContainer wordContainer = new WordContainer();
        wordContainer.read();
        Collections.sort(wordContainer.wordSet);
        System.out.println(wordContainer);
    }

}
