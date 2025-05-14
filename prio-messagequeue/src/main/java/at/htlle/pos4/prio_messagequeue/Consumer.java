package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

public class Consumer extends Thread {
    private final String name;
    private final PriorityMessageQueue queue;
    private final Random rand = new Random();

    public Consumer(String name, PriorityMessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Message msg = queue.receiveMessage();
            System.out.println(name + " receiveMessage(): " + msg.isPriority() + ", " + msg.getContent());
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

