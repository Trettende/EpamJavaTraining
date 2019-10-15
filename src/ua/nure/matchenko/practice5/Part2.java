package ua.nure.matchenko.practice5;

import java.io.InputStream;

public class Part2 {
    public static void main(String[] args) {
        System.setIn(new NewInputStream());

        Thread thread = new Thread() {
            @Override
            public void run() {
                Spam.main(null);
            }
        };
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.setIn(System.in);
    }

    private static class NewInputStream extends InputStream {
        private boolean isFirstCalling = true;
        private static final byte[] BYTES = System.lineSeparator().getBytes();
        private int index;

        @Override
        public int read() {
            while (isFirstCalling) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isFirstCalling = false;
                System.out.println(BYTES[index++]);
            }
            return -1;
        }
    }
}
