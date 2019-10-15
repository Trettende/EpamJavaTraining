package ua.nure.matchenko.practice6.part1;

import java.util.Objects;

public class Word implements Comparable<Word> {
    private String content;
    private int frequency = 1;

    public Word(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content + " : " + frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Word word = (Word) o;
        boolean result = Objects.equals(content, word.content);
        if (result) {
            word.frequency++;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public int compareTo(Word o) {
        int frequencyCompare = Integer.compare(o.frequency, frequency);
        if (frequencyCompare != 0) {
            return frequencyCompare;
        } else {
            return content.compareTo(o.content);
        }
    }
}
