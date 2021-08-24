package models;

import java.util.Objects;

public class Tenant {

    private String name;
    private int id;
    private String phoneNumber;
    private int propertyId;

    public Tenant(String name, String phoneNumber, int propertyId) {
        this.id =id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.propertyId = propertyId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tenant)) return false;
        Tenant tenant = (Tenant) o;
        return getId() == tenant.getId() &&
                getPropertyId() == tenant.getPropertyId() &&
                getName().equals(tenant.getName()) &&
                getPhoneNumber().equals(tenant.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getPhoneNumber(), getPropertyId());
    }
}
