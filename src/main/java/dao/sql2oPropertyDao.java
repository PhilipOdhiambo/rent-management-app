package dao;

import models.Property;
import org.sql2o.Sql2o;

import java.util.List;

class Sql2oPropertyDao implements PropertyDao {
    private final Sql2o sql2o;


    public Sql2oPropertyDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Property property) {

    }

    @Override
    public List<Property> getAll() {
        return null;
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
