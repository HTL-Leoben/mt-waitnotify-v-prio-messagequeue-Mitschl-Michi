package at.htlle.pos4.prio_messagequeue;
public class Message {
    private boolean isPriority;
    private String content;

    public Message(boolean isPriority, String content) {
        this.isPriority = isPriority;
        this.content = content;
    }

    public boolean isPriority() {
        return isPriority;
    }

    public String getContent() {
        return content;
    }
}
