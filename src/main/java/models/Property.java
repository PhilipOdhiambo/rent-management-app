package models;

public class Property {
    private  int id;
    private String name;
    private String type;
    private String location;
    private String description;
    private int rent;

    public Property (String name, String type, String location, String description, int rent) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.description = description;
        this.rent = rent;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getRent() {
        return rent;
    }

    public void setId(int id) {
        this.id = id;
    }
}
