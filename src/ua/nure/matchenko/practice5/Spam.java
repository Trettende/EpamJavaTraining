package ua.nure.matchenko.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Spam {
    private Thread[] threads;

    public Spam(String[] messages, int[] intervals) {
        threads = new Thread[messages.length];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Worker(messages[i], intervals[i]);
        }
    }

    public void start() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }

    public void stop() {
        for (int i = 0; i < threads.length; i++) {
            threads[i].interrupt();
        }
    }

    private static class Worker extends Thread {
        private String message;
        private int interval;

        public Worker(String message, int interval) {
            this.message = message;
            this.interval = interval;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(message);
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] messages = new String[] {"@@@", "bbbbbbb"};
        int[] intervals = new int[] {333, 222};

        Spam spam = new Spam(messages, intervals);
        spam.start();

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        )) {
            String line = reader.readLine();
            spam.stop();
            if (line == null) {
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}