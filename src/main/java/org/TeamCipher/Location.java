package org.TeamCipher;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Location {
    private SimpleIntegerProperty id;
    private SimpleStringProperty building;
    private SimpleStringProperty rname;
    private SimpleStringProperty type;
    private SimpleIntegerProperty capacity;


    public Location(int id, String building, String rname, String type, int capacity) {
        this.id = new SimpleIntegerProperty(id);
        this.building = new SimpleStringProperty(building);
        this.rname =new SimpleStringProperty(rname) ;
        this.type =new SimpleStringProperty(type) ;
        this.capacity = new SimpleIntegerProperty(capacity);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id =new SimpleIntegerProperty(id);
    }

    public String getBuilding() {
        return building.get();
    }

    public SimpleStringProperty buildingProperty() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = new SimpleStringProperty(building);
    }

    public String getRname() {
        return rname.get();
    }

    public SimpleStringProperty rnameProperty() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = new SimpleStringProperty(rname);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type = new SimpleStringProperty(type);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = new SimpleIntegerProperty(capacity);
    }
}
