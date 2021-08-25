package dao;

import models.Property;
import models.Tenant;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oPropertyDao implements PropertyDao {
    private final Sql2o sql2o;


    public Sql2oPropertyDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Property property) {
        String sql = "INSERT INTO properties (name, type, location, description, rent) VALUES (:name, :type, :location, :description, :rent)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(property)
                    .executeUpdate()
                    .getKey();
            property.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Property> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM properties")
                    .executeAndFetch(Property.class);
        }
    }

    @Override
    public Property findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM properties WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Property.class);
        }
    }

    @Override
    public Tenant getTenantByPropertyId(int id) {
        String sql = "SELECT * FROM tenants WHERE propertyid = :id";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Tenant.class);
        }
    }

    @Override
    public void update(int id, String name, String type, String location, String description, int rent) {
        String sql = "UPDATE properties SET (name, type, location, description, rent) = (:name, :type, :location, :description, :rent) WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("type", type)
                    .addParameter("location", location)
                    .addParameter("description", description)
                    .addParameter("rent", rent)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from properties WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from properties";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
