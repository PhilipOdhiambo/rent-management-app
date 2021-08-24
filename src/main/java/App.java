import com.google.gson.Gson;
import dao.Sql2oPropertyDao;
import exceptions.ApiException;
import models.Property;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        Sql2oPropertyDao propertyDao;
        Connection conn;
        Gson gson = new Gson();

        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/jadle.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        propertyDao = new Sql2oPropertyDao(sql2o);


        //READ
        get("/properties", "application/json", (req, res) -> {
            System.out.println(propertyDao.getAll());

            if(propertyDao.getAll().size() > 0){
                return gson.toJson(propertyDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no properties are currently listed in the database.\"}";
            }
        });

        // Get property by id
        get("/properties/:id", "application/json", (req, res) -> {
            int restaurantId = Integer.parseInt(req.params("id"));
            Property propertyToFind = propertyDao.findById(restaurantId);
            if (propertyDao == null){
                throw new ApiException(404, String.format("No property with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(propertyDao);
        });

        //CREATE
        post("/properties/new", "application/json", (req, res) -> {
            Property property = gson.fromJson(req.body(), Property.class);
            propertyDao.add(property);
            res.status(201);
            return gson.toJson(property);
        });

        //FILTERS
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });


        after((req, res) ->{
            res.type("application/json");
        });

    }
}
