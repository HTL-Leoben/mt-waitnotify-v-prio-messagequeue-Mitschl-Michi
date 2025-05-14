package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

public class Producer extends Thread {
    private final String name;
    private final PriorityMessageQueue queue;
    private final Random rand = new Random();

    public Producer(String name, PriorityMessageQueue queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            boolean priority = rand.nextBoolean();
            String content = "Message from " + name + ", priority: " + priority;
            Message msg = new Message(priority, content);
            queue.sendMessage(msg);
            System.out.println(name + " sendMessage(): " + content);
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
