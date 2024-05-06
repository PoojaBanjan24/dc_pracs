import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Clock {
    private int time = 0;
    private Lock lock = new ReentrantLock();

    public int getTime() {
        lock.lock();
        try {
            return time;
        } finally {
            lock.unlock();
        }
    }

    public void setTime(int newTime) {
        lock.lock();
        try {
            time = newTime;
        } finally {
            lock.unlock();
        }
    }
}

class Node extends Thread {
    private int nodeId;
    private Clock clock;

    public Node(int nodeId, Clock clock) {
        this.nodeId = nodeId;
        this.clock = clock;
    }

    public void run() {
        int iterations = 10; // Set the number of iterations
        for (int i = 0; i < iterations; i++) {
            // Simulate some computation
            doSomeWork();

            // Update the clock
            int currentClock = clock.getTime();
            currentClock++;
            clock.setTime(currentClock);

            // Print the clock of this node
            System.out.println("Node " + nodeId + " - Clock: " + currentClock);

            // Sleep for some time before repeating
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void doSomeWork() {
        // Simulate some computation
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a clock instance
        Clock clock = new Clock();

        // Create and start multiple nodes
        for (int i = 0; i < 3; i++) {
            Node node = new Node(i, clock);
            node.start();
        }
    }
}

