package dao;

import models.Property;
import models.Tenant;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oPropertyDaoTest {

    private Connection conn;
    private Sql2oPropertyDao propertyDao;
    private Sql2oTenantDao tenantDao;

    @BeforeEach
    void setUp() {
        String connectionString =  "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        propertyDao = new Sql2oPropertyDao(sql2o);
        tenantDao = new Sql2oTenantDao(sql2o);


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

    @Test
    void addedPropertiesAreReturnedFromGetAll() {
        Property property = setupProperty();
        propertyDao.add(property);
        assertEquals(1, propertyDao.getAll().size());
    }

    @Test
    void noPropertiesReturnsEmptyList() {
        assertEquals(0, propertyDao.getAll().size());
    }

    @Test
    void findBy_IdReturnsCorrectProperty() {
        Property property = setupProperty();
        propertyDao.add(property);
        int id = property.getId();
        assertEquals(id, propertyDao.findById(property.getId()).getId());
    }

    @Test
    void update_CorrectlyUpdatesAllFields() {
        Property property = setupProperty();
        propertyDao.add(property);
        Property otherProperty = setupProperty();
        propertyDao.add(otherProperty);

        String name = property.getName();
        String type =property.getType();
        String location = property.getLocation();
        String description =property.getDescription();
        int rent =property.getRent();

        assertEquals(name,property.getName());
        assertEquals(type,property.getType());
        assertEquals(location,property.getLocation());
        assertEquals(description,property.getDescription());
        assertEquals(rent,property.getRent());
    }

    @Test
    void deleteByIdDeletesCorrectProperty() {
        Property property = setupProperty();
        propertyDao.add(property);
        Property otherProperty = setupProperty();
        propertyDao.add(otherProperty);
        propertyDao.deleteById(property.getId());
        assertEquals(1, propertyDao.getAll().size());
    }


    @Test
    void getTenantByPropertyId() {
        Tenant tenant = setUpTenant();
        tenantDao.addTenant(tenant);
        int propid = tenant.getPropertyId();//698574

        Tenant tenant1 = setUpTenant();
        tenantDao.addTenant(tenant1);

        assertEquals(propid, propertyDao.getTenantByPropertyId(tenant.getPropertyId()).getPropertyId());

    }

    @Test
    void clearAll() {
        Property property = setupProperty();
        propertyDao.add(property);
        Property otherProperty = setupProperty();
        propertyDao.add(otherProperty);
        propertyDao.clearAll();
        assertEquals(0, propertyDao.getAll().size());
    }


    //helper
    private Property setupProperty() {
        return new Property("Atlantic Edge", "Apartment", "Karen", "five bedroom", 30000 );

    }

    public Tenant setUpTenant(){
        return new Tenant("issah","54789654",698574);
    };


}