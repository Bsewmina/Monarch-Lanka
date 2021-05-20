package org.TeamCipher;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Room {

    private SimpleIntegerProperty id;
    private SimpleStringProperty session;
    private SimpleStringProperty room;
    private SimpleStringProperty sessionDetails;

    public Room(int id,String session, String room, String sessionDetails) {
        this.id = new SimpleIntegerProperty(id);
        this.session = new SimpleStringProperty(session);
        this.room = new SimpleStringProperty(room);
        this.sessionDetails =new SimpleStringProperty(sessionDetails);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSession() {
        return session.get();
    }

    public SimpleStringProperty sessionProperty() {
        return session;
    }

    public void setSession(String session) {
        this.session.set(session);
    }

    public String getRoom() {
        return room.get();
    }

    public SimpleStringProperty roomProperty() {
        return room;
    }

    public void setRoom(String room) {
        this.room.set(room);
    }

    public String getSessionDetails() {
        return sessionDetails.get();
    }

    public SimpleStringProperty sessionDetailsProperty() {
        return sessionDetails;
    }

    public void setSessionDetails(String sessionDetails) {
        this.sessionDetails.set(sessionDetails);
    }
}
