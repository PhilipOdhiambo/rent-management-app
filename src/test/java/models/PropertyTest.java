package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    @Test
    void getNameReturnsCorrectName() {
        Property property = setupProperty();
        assertEquals("Atlantic Edge", property.getName());
    }

//    helper
    private Property setupProperty() {
        return new Property("Atlantic Edge", "Apartment", "Lavinghton", "five bedroom", 30000 );
    }
}