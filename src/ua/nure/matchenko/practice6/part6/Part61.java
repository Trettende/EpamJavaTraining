package ua.nure.matchenko.practice6.part6;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Part61 {

    public static void main(String[] args) {
        Map<String, Integer> map = new LinkedHashMap<>();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(args[0])
                )
        )) {
            String line;
            Integer frequency;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    frequency = map.get(word);
                    if (map.get(word) == null) {
                        map.put(word, 1);
                    } else {
                        map.put(word, ++frequency);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(map);
        FrequencyComparator comparator = new FrequencyComparator(map);
        TreeMap<String, Integer> fullTreeMap = new TreeMap<>(comparator);
        fullTreeMap.putAll(map);
        //System.out.println(fullTreeMap);

        TreeMap<String, Integer> finalTreeMap = fullTreeMap.entrySet()
                .stream()
                .limit(3)
                .collect(TreeMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
        //System.out.println(finalTreeMap);

        //System.out.println(finalTreeMap.descendingMap());
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : finalTreeMap.descendingMap().entrySet()) {
            builder.append(entry.getKey())
                    .append(" ==> ")
                    .append(entry.getValue())
                    .append("\n");
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder);

    }

    static class FrequencyComparator implements Comparator<String>, Serializable {
        private static final long serialVersionUID = 1;
        private Map<String, Integer> map;

        public FrequencyComparator(Map<String, Integer> map) {
            this.map = map;
        }

        @Override
        public int compare(String o1, String o2) {
            if (map.get(o1) > map.get(o2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
