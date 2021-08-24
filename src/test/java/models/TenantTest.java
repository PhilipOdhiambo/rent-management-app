package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TenantTest {


    @Test
    void getId() {
        Tenant testTenant = setUpTenant();
        assertEquals(0,testTenant.getId());
    }


    @Test
    void getName() {
        Tenant testTenant = setUpTenant();
        assertEquals("issah",testTenant.getName());
    }

    @Test
    void setName() {
        Tenant testTenant = setUpTenant();
        testTenant.setName("stephen");
        assertEquals("stephen",testTenant.getName());
    }

    @Test
    void getPhoneNumber() {
        Tenant testTenant = setUpTenant();
        assertEquals("54789654",testTenant.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        Tenant testTenant = setUpTenant();
        testTenant.setPhoneNumber("665547895");
        assertEquals("665547895",testTenant.getPhoneNumber());
    }

    @Test
    void getPropertyId() {
        Tenant testTenant = setUpTenant();
        assertEquals(698574,testTenant.getPropertyId());
    }

    @Test
    void setPropertyId() {
        Tenant testTenant = setUpTenant();
        testTenant.setPropertyId(5478964);
        assertEquals(5478964,testTenant.getPropertyId());
    }

    @Test
    void testEquals() {
        Tenant testTenant = setUpTenant();
        Tenant otherTenant = setUpTenant();
        assertTrue(testTenant.equals(otherTenant));
    }

    //helpers

    public Tenant setUpTenant(){
        return new Tenant("issah","54789654",698574);
    };
}