package me.mojaaplikacija;
import spark.Request;
import spark.Spark.*;

import static me.mojaaplikacija.JsonUtil.json;
import static me.mojaaplikacija.JsonUtil.toJson;
import static spark.Spark.after;
import static spark.Spark.exception;

/**
 * Created by Vid on 19.9.2016.
 */
public class PostController {

    PostDAO postDAO;

    private String [] getValuesFromParams(String [] params, Request req) {
        String[] values = new String[params.length];

        for (int i = 0; i<params.length; i++) {
            values[i] = req.queryParams(params[i]);
        }

        return values;
    }

    public PostController(final PostDAO postDAO) {

        this.postDAO = postDAO;
        String [] fieldInfoArray = postDAO.getParams();

        //dobimo vse poste
        spark.Spark.get("/api/posts", (req, res) -> postDAO.getAllPosts(), json());

        //dobimo post po id-ju
        spark.Spark.get("/api/post/:id", (req, res) -> {
                String id = req.params(":id");
                Post post = postDAO.getPost(Integer.parseInt(id));
                if(post != null) {
                    return post;
                }
                res.status(400);
                return new ResponseError("There is no user with id %s", id);
                }, json());

        spark.Spark.post("/api/posts", (req, res) -> postDAO.createPost(getValuesFromParams(fieldInfoArray, req)), json());

        //http://localhost:4567/api/posts/3?title=cetrti&content=cetrti
        spark.Spark.put("api/posts/:id", (req, res) -> postDAO.update(Integer.parseInt(req.params(":id")), getValuesFromParams(fieldInfoArray, req)), json());

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        //filter, ki doda v json tip
        after((req, res) -> {
            res.type("application/json");
        });
    }
}
