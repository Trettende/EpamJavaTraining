package ua.nure.matchenko.practice6.part6;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Part62 {
    public static void main(String[] args) {
        Set<String> wordSet = new LinkedHashSet<>();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(args[0])
                )
        )) {
            String line;
            String[] words;
            while ((line = reader.readLine()) != null) {
                words = line.split(" ");
                for (String word : words) {
                    wordSet.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(wordSet);
        Set<String> treeSet = new TreeSet<>(new WordLengthComparator());
        treeSet.addAll(wordSet);
        //System.out.println(treeSet);

        Set<String> finalTreeSet = treeSet.stream().limit(3)
                .collect(TreeSet::new, (m, e) -> m.add(e), Set::addAll);

        Set<String> sortedTreeSet = new TreeSet<>(new WordLengthComparator());
        sortedTreeSet.addAll(finalTreeSet);


        StringBuilder builder = new StringBuilder();
        for (String word : sortedTreeSet) {
            builder.append(word)
                    .append(" ==> ")
                    .append(word.length())
                    .append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder);
    }

    static class WordLengthComparator implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 1;
        @Override
        public int compare(String o1, String o2) {
            if (o1.length() > o2.length()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
