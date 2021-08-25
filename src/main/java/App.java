import static spark.Spark.*;

import com.google.gson.Gson;
import dao.Sql2oPaymentDao;
import dao.Sql2oPropertyDao;
import dao.Sql2oTenantDao;
import exceptions.ApiException;
import models.Payment;
import models.Property;
import models.Tenant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        Sql2oPropertyDao propertyDao;
        Sql2oTenantDao tenantDao;
        Sql2oPaymentDao paymentDao;
        Connection conn;
        Gson gson = new Gson();

        //postgres://urjtfsaqjmosyk:b7f301231b92f36726c2c05612873906d1363734f876ffabcff80a65f3e1d14b@ec2-35-153-91-18.compute-1.amazonaws.com:5432/d200amuh98noo0

        //String connectionString = "jdbc:postgresql://localhost:5432/rent_management"; //connect to Organization_Portal, not Organization_Portal_test!
        //Sql2o sql2o = new Sql2o(connectionString, "damark", "password");

        String connectionString = "jdbc:postgresql://ec2-35-153-91-18.compute-1.amazonaws.com:5432/d200amuh98noo0"; //connect to Organization_Portal, not Organization_Portal_test!
        Sql2o sql2o = new Sql2o(connectionString, "urjtfsaqjmosyk", "b7f301231b92f36726c2c05612873906d1363734f876ffabcff80a65f3e1d14b");

        propertyDao = new Sql2oPropertyDao(sql2o);
        tenantDao = new Sql2oTenantDao(sql2o);
        paymentDao = new Sql2oPaymentDao(sql2o);

        Spark.options("/*", (request, response) -> {

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

        Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
        });




        //READ
        get("/properties", "application/json", (req, res) -> {
            System.out.println(propertyDao.getAll());

            if(propertyDao.getAll().size() > 0){
                return gson.toJson(propertyDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no properties are currently listed in the database.\"}";
            }
        });

        //READ tenants
        get("/tenants", "application/json", (req, res) -> {
            System.out.println(tenantDao.getAllTenants());

            if(tenantDao.getAllTenants().size() > 0){
                return gson.toJson(tenantDao.getAllTenants());
            } else {
                return "{\"message\":\"I'm sorry, but no tenants are currently listed in the database.\"}";
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

        // Get tenant by id
        get("/tenants/:id", "application/json", (req, res) -> {
            int tenantId = Integer.parseInt(req.params("id"));
            Tenant tenantToFind = tenantDao.findTenantById(tenantId);
            if (tenantDao == null){
                throw new ApiException(404, String.format("No tenant with the id: \"%s\" exists", req.params("id")));
            }
            return gson.toJson(tenantDao);
        });

        //CREATE
        post("/properties/new", "application/json", (req, res) -> {
            Property property = gson.fromJson(req.body(), Property.class);
            propertyDao.add(property);
            res.status(201);
            return gson.toJson(property);
        });

        //CREATE
        post("/tenants/new", "application/json", (req, res) -> {
            Tenant tenant = gson.fromJson(req.body(), Tenant.class);
            tenantDao.addTenant(tenant);
            res.status(201);
            return gson.toJson(tenant);
        });

        /*  -----------------------  Payment Routes  ------------------------------- */

        // CREATE

        //create new payment
        post("/payments/new","application/json",(req,res) -> {
            Payment payment = gson.fromJson(req.body(), Payment.class);
            paymentDao.add(payment);
            res.status(201);
            return gson.toJson(payment);
        });
        // READ
        //READ payments
        get("/payments", "application/json", (req, res) -> {
            if(paymentDao.getAll().size() > 0){
                return gson.toJson(paymentDao.getAll());
            } else {
                return "{\"message\":\"I'm sorry, but no payments are currently listed in the database.\"}";
            }
        });
        // UPDATE
        // DELETE

        //FILTERS
/*
        exception(ApiException.class, (exception, req, res) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            res.type("application/json");
            res.status(err.getStatusCode());
            res.body(gson.toJson(jsonMap));
        });

 */


        after((req, res) ->{
            res.type("application/json");
        });

    }
}
