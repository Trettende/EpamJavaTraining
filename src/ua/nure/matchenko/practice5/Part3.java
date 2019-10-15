package ua.nure.matchenko.practice5;

public class Part3 {
    private int counter;
    private int counter2;
    private int n;
    private int k;
    private int t;

    private Thread[] threads;

    public Part3(int n, int k, int t) {
        this.n = n;
        this.k = k;
        this.t = t;
    }

    private class NewThread extends Thread {
        private int k;
        private int t;

        public NewThread(int k, int t) {
            this.k = k;
            this.t = t;
        }

        @Override
        public void run() {
            for (int i = 0; i < k; i++) {
                System.out.printf("%s %s%n", counter, counter2);
                counter++;
                try {
                    Thread.sleep(t);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
                counter2++;
            }
        }
    }

    public void reset() throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
        counter = 0;
        counter2 = 0;

    }

    public void test() throws InterruptedException {
        threads = new Thread[n];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new NewThread(k, t);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    public void testSync() {
        threads = new Thread[n];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new NewThread(k, t);
        }
        for (Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Part3 p = new Part3(3, 5, 100);
        p.test();
        p.reset();
        p.testSync();
    }
}
