package pe.area51.sqlitecontentproviderapp;

public class Note {

    private final long id;
    private final String title;
    private final String content;

    public Note(final long id, final String title, final String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
