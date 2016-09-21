package me.mojaaplikacija;
import me.mojaaplikacija.Post;

import java.util.List;

public interface PostDAO {
    public List<Post> getAllPosts();
    public Post getPost(int id);
    public Post createPost(String title, String content);
    public void deletePost(int id);
    public Post update(int id, String title, String content);
}