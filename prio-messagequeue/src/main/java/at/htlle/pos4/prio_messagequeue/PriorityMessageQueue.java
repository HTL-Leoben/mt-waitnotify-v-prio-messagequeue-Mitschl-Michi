package at.htlle.pos4.prio_messagequeue;

import java.util.LinkedList;
import java.util.Queue;

public class PriorityMessageQueue {
    private final Queue<Message> priorityQueue = new LinkedList<>();
    private final Queue<Message> normalQueue = new LinkedList<>();
    private final int maxSize;

    public PriorityMessageQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void sendMessage(Message msg) {
        while (size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (msg.isPriority()) {
            priorityQueue.offer(msg);
        } else {
            normalQueue.offer(msg);
        }
        notifyAll();
    }

    public synchronized Message receiveMessage() {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Message msg = !priorityQueue.isEmpty() ? priorityQueue.poll() : normalQueue.poll();
        notifyAll();
        return msg;
    }

    private int size() {
        return priorityQueue.size() + normalQueue.size();
    }

    private boolean isEmpty() {
        return priorityQueue.isEmpty() && normalQueue.isEmpty();
    }
}