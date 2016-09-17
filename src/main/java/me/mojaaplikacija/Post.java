package me.mojaaplikacija;

public class Post {
    private String title;
    private String content;
    private int id;

    public Post(String t, String c) {
        this.title = t;
        this.content = c;
    }

    public Post(int id, String t, String c) {
        //podvajanje konstruktorjev!!!
        this.title = t;
        this.content = c;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public boolean isValid() {
        return title != null && !title.isEmpty();
    }

    public String[] toStringArray() {
        String [] data = new String[2];
        data[0] = new String(title);
        data[1] = new String(content);
        return data;
    }

    public String toString() {
        String write = "Title: "+title+"; Content: "+content;
        return write;
    }
}