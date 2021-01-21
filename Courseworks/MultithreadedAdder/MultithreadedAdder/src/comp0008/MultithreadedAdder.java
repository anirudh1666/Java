package comp0008;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * A multithreaded implementation of the Adder which uses a specified number of threads to add all the numbers together.
 * This class needs to be implemented in TASK 3 ...
 */

public class MultithreadedAdder implements Adder {

    private int N;                        // change this to change number of threads
    private volatile double sum;
    private String[] values;
    private volatile boolean ready = false;
    private CountDownLatch latch;

    public synchronized void run() {

        double[] sums = new double[N];
        SerialAdder[] adders = new SerialAdder[N];

        int partition = this.values.length / N;
        // Setting up adders to do addition.
        for (int i = 0; i != this.N; ++i) {
            SerialAdder adder = new SerialAdder();
            adders[i] = adder;
            if (i == this.N - 1) {
                // on last loop give the last thread all remaining elements.
                adder.setValues(Arrays.copyOfRange(this.values, i * partition, this.values.length));
            } else {
                adder.setValues(Arrays.copyOfRange(this.values, i * partition, (i * partition) + partition));
            }
            new Thread(adder).start();
        }

        for (int i = 0; i != N; ++i) {
            try {
                sums[i] = adders[i].getSum();
                this.latch.countDown();
            } catch (InterruptedException e) {
                System.err.println("Addition was interrupted.");
            }
        }

        this.sum = 0.0;

        try {
            this.latch.await();
            for (int i = 0; i != this.N; ++i) {
                this.sum += sums[i];
            }
            this.ready = true;
            notifyAll();
        } catch (InterruptedException e) {
            System.err.println("Calculating sum was interrupted.");
        }
    }

    public void setValues(String[] values) { this.values = values; }

    public void setThreads(int threads) {

        this.N = threads;
        this.latch = new CountDownLatch(threads);
    }

    public synchronized double getSum() throws InterruptedException {

        if (!ready) { wait(); }
        return this.sum;
    }
}
