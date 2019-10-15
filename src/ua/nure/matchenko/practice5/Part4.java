package ua.nure.matchenko.practice5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part4 {
    private int length;
    private int width;
    private int[][] matrix;
    private int[] maxes;

    public void readMatrixFromFile(String path) {
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path)))) {
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                lines.add(line);
                length++;
            }
            String[] lineValues;
            lineValues = lines.get(0).split(" ");
            width = lineValues.length;
            matrix = new int[length][width];
            maxes = new int[length];
            for (int i = 0; i < length; i++) {
                lineValues = lines.get(i).split(" ");
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = Integer.valueOf(lineValues[j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long findMaxByOneThread() {
        long start = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                try {
                    Thread.sleep(1);
                    if (matrix[i][j] > max) {
                        max = matrix[i][j];
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(max);
        long end = System.currentTimeMillis();
        return end - start;
    }

    public long findMaxByFewThreads() {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new NewThread(i, width);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getMax());
        long end = System.currentTimeMillis();
        return end - start;
    }

    public int getMax() {
        int absoluteMax = maxes[0];
        for (int max : maxes) {
            if (max > absoluteMax) {
                absoluteMax = max;
            }
        }
        return absoluteMax;
    }

    private class NewThread extends Thread {
        private int maxInLine = Integer.MIN_VALUE;
        private int i;
        private int width;

        public NewThread(int i, int width) {
            this.i = i;
            this.width = width;
        }

        @Override
        public void run() {
            for (int j = 0; j < width; j++) {
                try {
                    Thread.sleep(1);
                    if (matrix[i][j] > maxInLine) {
                        maxInLine = (matrix[i][j]);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            maxes[i] = maxInLine;
        }
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.readMatrixFromFile("5part4.txt");
        /*for (int i = 0; i < part4.length; i++) {
            for (int j = 0; j < part4.width; j++) {
                System.out.printf("%d ", part4.matrix[i][j]);
            }
            System.out.println();
        }*/
        System.out.println(part4.findMaxByFewThreads());
        System.out.println(part4.findMaxByOneThread());
    }
}
