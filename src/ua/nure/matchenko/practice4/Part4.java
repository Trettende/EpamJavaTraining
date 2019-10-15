package ua.nure.matchenko.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {

    private static final String ENCODING = "Cp1251";

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl();
    }

    private static final class IteratorImpl implements Iterator<String> {
        private Pattern pattern = Pattern.compile(("(\\s*|^)([^.?!]+)([.?!])"),
                Pattern.UNICODE_CHARACTER_CLASS);
        private Matcher matcher = pattern.matcher(readFile("4part4.txt"));
        private boolean flag;

        private IteratorImpl() {
        }

        @Override
        public boolean hasNext() {
            flag = matcher.find();
            return flag;
        }

        @Override
        public String next() {
            if (!flag) {
                throw new NoSuchElementException();
            }
            return matcher.group(2) + matcher.group(3);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private static String readFile(String path) {
            StringBuilder builder = new StringBuilder();
            String line;

            try(BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path), ENCODING
                    )
            )) {
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return builder.toString();
        }
    }

    private static String cutSentencesFromFile() {
        Part4 part4 = new Part4();
        Iterator<String> it = part4.iterator();
        StringBuilder builder = new StringBuilder();
        while (it.hasNext()) {
            builder.append(it.next())
                    .append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(cutSentencesFromFile());
    }
}
