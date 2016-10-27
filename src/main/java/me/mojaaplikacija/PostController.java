package me.mojaaplikacija;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark.*;

import java.io.Console;
import java.util.HashMap;

import static me.mojaaplikacija.JsonUtil.json;
import static me.mojaaplikacija.JsonUtil.toJson;
import static spark.Spark.*;

/**
 * Created by Vid on 19.9.2016.
 */
public class PostController {

    PostDAO postDAO;

    private String[] getValuesFromParams(String[] params, Request req) {
        String[] values = new String[params.length];

        for (int i = 0; i < params.length; i++) {
            values[i] = req.queryParams(params[i]);
        }

        return values;
    }

    public PostController(final PostDAO postDAO) {

        this.postDAO = postDAO;
        String[] fieldInfoArray = postDAO.getParams();

        HashMap<String, String> p = new HashMap<String, String>();
        p.put("Day", "f");

        //dobimo vse poste
        spark.Spark.get("/api/posts", (req, res) -> postDAO.getAllPosts(), json());

        spark.Spark.get("/api/posts/:search", (req, res) -> postDAO.searchPost(ConvertReqToHashMap(req)), json());

        //dobimo post po id-ju
        spark.Spark.get("/api/posts/:id", (req, res) -> {
            System.out.println("GET");
            String id = req.params(":id");
            Post post = postDAO.getPost(Integer.parseInt(id));
            if (post != null) {
                return post;
            }
            res.status(400);
            return new ResponseError("There is no user with id %s", id);
        }, json());

        spark.Spark.post("/api/posts", (req, res) -> postDAO.createPost(getValuesFromParams(fieldInfoArray, req)), json());

        //http://localhost:4567/api/posts/3?title=cetrti&content=cetrti
        spark.Spark.put("api/posts/:id", (req, res) -> postDAO.update(Integer.parseInt(req.params(":id")), getValuesFromParams(fieldInfoArray, req)), json());

        spark.Spark.delete("api/posts/:id", (req, res)->postDAO.deletePost(Integer.parseInt(req.params(":id"))));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });

        //filter, ki doda v json tip
        after((req, res) -> {
            res.type("application/json");
        });

        enableCORS("*", "*", "*");

    }

    private static HashMap<String, String> ConvertReqToHashMap(Request req) {
        //System.out.println(req.queryParams().size());
        HashMap<String, String> p = new HashMap<String, String>();

        for(String param : req.queryParams()) {
            p.put(param, req.queryParams(param));
            //System.out.println(param+" "+ req.queryParams(param));
        }
        return p;
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before(new Filter() {
            @Override
            public void handle(Request request, Response response) {
                response.header("Access-Control-Allow-Origin", origin);
                response.header("Access-Control-Request-Method", methods);
                response.header("Access-Control-Allow-Headers", headers);
            }
        });
    }
}
