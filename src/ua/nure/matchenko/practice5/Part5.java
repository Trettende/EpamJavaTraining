package ua.nure.matchenko.practice5;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {
    private int k;
    private Thread[] threads;
    private RandomAccessFile file;
    private int counter;
    private int n;

    public Part5(int k, String path) {
        this.k = k;
        threads = new Thread[k];
        try {
            file = new RandomAccessFile(path, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {
        try {
            for (int i = 0; i < k; i++) {
                threads[i] = new NewThread();
            }
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(path)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line)
                        .append(System.lineSeparator());
            }
            builder.deleteCharAt(builder.length() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private class NewThread extends Thread {
        @Override
        public void run() {
            synchronized (file) {
                for (int j = 0; j < 20; j++) {
                    try {
                        Thread.sleep(1);
                        file.seek(counter++);
                        file.write('0' + n);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    file.seek(counter++);
                    file.write(System.lineSeparator().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                n++;
            }
        }
    }

    public static void main(String[] args) {
        Part5 part5 = new Part5(10, "5part5.txt");
        part5.writeFile();
        System.out.println(part5.readFile("5part5.txt"));
    }
}