package dao;

import models.Property;

import java.util.List;

public interface PropertyDao {

    //create
    void add (Property property);

    //read
    List<Property> getAll();
    Property findById(int id);

    //update
    void update(int id, String name, String type, String location, String description, int rent);

    //delete
    void deleteById(int id);
    void clearAll();
}
