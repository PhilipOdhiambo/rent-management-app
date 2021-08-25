package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void getNameReturnsCorrectName() {
        Property property = setupProperty();
        assertEquals("Atlantic Edge", property.getName());
    }

    @Test
    void getTypeReturnsCorrectPropertyType() {
        Property property = setupProperty();
        assertEquals("Apartment", property.getType());
    }

    @Test
    void getLocationReturnsCorrectLocation() {
        Property property = setupProperty();
        assertEquals("Karen", property.getLocation());
    }

    @Test
    void getDescriptionReturnsCorrectDescription() {
        Property property = setupProperty();
        assertEquals("five bedroom", property.getDescription());
    }

    @Test
    void getRentReturnsCorrectRent() {
        Property property = setupProperty();
        assertEquals(30000, property.getRent());
    }

    //helper
    private Property setupProperty() {
        return new Property("Atlantic Edge", "Apartment", "Karen", "five bedroom", 30000 );
    }
}