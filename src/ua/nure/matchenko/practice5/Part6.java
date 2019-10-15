package ua.nure.matchenko.practice5;

public class Part6 {
    private static final Object M = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        synchronized (M) {
                            M.wait();
                        }
                    }
                } catch (InterruptedException e1) {
                     interrupt();
                }
            }
        };

        //NEW
        //System.out.println(thread.getState());

        //RUNNABLE
        thread.start();
        //System.out.println(thread.getState());

        synchronized (M) {
            M.notifyAll();
            thread.join(1);
            System.out.println(thread.getState());
        }

        thread.join(1);
        System.out.println(thread.getState());

        thread.interrupt();
        thread.join();
        System.out.println(thread.getState());
    }
}
