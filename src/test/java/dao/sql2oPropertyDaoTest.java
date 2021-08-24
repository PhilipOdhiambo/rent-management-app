package dao;

import models.Property;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class sql2oPropertyDaoTest {

    private Connection conn;
    private Sql2oPropertyDao propertyDao;

    @BeforeEach
    void setUp() {
        String connectionString =  "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        propertyDao = new Sql2oPropertyDao(sql2o);

        conn = sql2o.open();
    }

    @AfterEach
    void tearDown() {
        conn.close();
    }

    @Test
    void addingPropertySetsId() {
        Property property = setupProperty();
        propertyDao.add(property);
        assertNotEquals(0, property.getId());
    }

    //helper
    private Property setupProperty() {
        return new Property("Atlantic Edge", "Apartment", "Karen", "five bedroom", 30000 );
    }
}