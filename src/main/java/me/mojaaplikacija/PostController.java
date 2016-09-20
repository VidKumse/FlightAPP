package me.mojaaplikacija;
import spark.Spark.*;

import static me.mojaaplikacija.JsonUtil.json;

/**
 * Created by Vid on 19.9.2016.
 */
public class PostController {
    public PostController(final PostDAO postDAO) {
        spark.Spark.get("/api/posts", (req, res) -> postDAO.getAllPosts(), json());
    }
}
