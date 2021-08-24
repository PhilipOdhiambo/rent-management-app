package dao;

import models.Property;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

class Sql2oPropertyDao implements PropertyDao {
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
        return null;
    }

    @Override
    public void update(int id, String name, String type, String location, String description, int rent) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
