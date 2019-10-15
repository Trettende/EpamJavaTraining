package ua.nure.matchenko.practice5;

public class Part1 {
    static class ChildThread extends Thread {
        @Override
        public void run() {
            runThread();
        }
    }

    static class ImplementingThread implements Runnable {
        @Override
        public void run() {
            runThread();
        }
    }

    static final class UsingMethodThread {
        private UsingMethodThread() {
            throw new IllegalStateException("Utility class");
        }

        static Thread createThread() {
            //return new Thread(() -> runThread());
            return new Thread() {
                @Override
                public void run() {
                    runThread();
                }
            };
        }
    }

    private static void runThread() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(334);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread childThread = new ChildThread();
        childThread.start();

        Thread.sleep(1000);

        childThread.interrupt();

        Thread implementingThread = new Thread(new ImplementingThread());
        implementingThread.start();

        Thread.sleep(1000);

        implementingThread.interrupt();

        Thread usingMethodThread = UsingMethodThread.createThread();
        usingMethodThread.start();

        Thread.sleep(1000);

        usingMethodThread.interrupt();

        Thread.currentThread().interrupt();
    }
}
