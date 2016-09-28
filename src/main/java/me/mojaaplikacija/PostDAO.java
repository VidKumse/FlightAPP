package me.mojaaplikacija;
import me.mojaaplikacija.Post;

import java.util.List;

public interface PostDAO {
    public List<Post> getAllPosts();
    public Post getPost(int id);
    public boolean createPost(String... args);
    public void deletePost(int id);
    public boolean update(int id, String... args);
    public String [] getParams();
}