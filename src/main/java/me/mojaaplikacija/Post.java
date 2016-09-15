package me.mojaaplikacija;

public class Post {
    private String title;
    private String content;

    public Post(String t, String c) {
        title = t;
        content = c;
    }

    public static FieldInfo[] Init() {
        FieldInfo [] fi;
        fi = new FieldInfo[2];
        fi[0] = new FieldInfo("title", 5);
        fi[1] = new FieldInfo("content", 6);
        return fi;

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