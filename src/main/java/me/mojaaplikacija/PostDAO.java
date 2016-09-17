package me.mojaaplikacija;
import me.mojaaplikacija.Post;

import java.util.List;

public interface PostDAO {
    public List<Post> getAllPosts();
    public Post getPost(int id);
    public void createPost(Post post);
    public void deletePost(int id);
    public void update(Post post);
}