package org.TeamCipher;

public class ModelSubjectTable {

    private int id,year,semester;
    private String subjName,subjCode;

    public ModelSubjectTable(int id, String subjName, String subjCode, int year, int semester) {
        this.id = id;
        this.subjName = subjName;
        this.subjCode = subjCode;
        this.year = year;
        this.semester = semester;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getSubjName() {
        return subjName;
    }

    public void setSubjName(String subjName) {
        this.subjName = subjName;
    }

    public String getSubjCode() {
        return subjCode;
    }

    public void setSubjCode(String subjCode) {
        this.subjCode = subjCode;
    }
}
