package pe.area51.fragmentapp.models;

public class Message {

    private final String content;
    private final String title;

    public Message(final String content, final String title) {
        this.content = content;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }
}
