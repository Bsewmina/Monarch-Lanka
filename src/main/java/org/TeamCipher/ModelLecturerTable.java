package org.TeamCipher;

public class ModelLecturerTable {
    private int id,level;
    private String name,faculty;


    public ModelLecturerTable(int id, String name, String faculty, int level) {
        this.id = id;
        this.level = level;
        this.name = name;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }


}
