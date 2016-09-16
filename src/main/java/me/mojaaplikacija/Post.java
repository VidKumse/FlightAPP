package me.mojaaplikacija;

public class Post {
    private String title;
    private String content;

    public Post(String t, String c) {
        this.title = t;
        this.content = c;
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

    public boolean isValid() {
        return title != null && !title.isEmpty();
    }

    public String[] toStringArray() {

        String [] data = new String[2];
        data[0] = new String(title);
        data[1] = new String(content);
        return data;
    }
}