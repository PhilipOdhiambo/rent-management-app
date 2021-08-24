package models;

public class Property {
    private  int id;
    private String name;
    private String location;
    private int rent;

    public Property (String name, String location, int rent) {
        this.name = name;
        this.location = location;
        this.rent = rent;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getRent() {
        return rent;
    }

}
