package org.TeamCipher;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Group {

    private int id;
    private String sem;
    private String program;
    private int groupNo;
    private String groupID;
    private int subgroupNo;
    private String getSubgroupID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(int groupNo) {
        this.groupNo = groupNo;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public int getSubgroupNo() {
        return subgroupNo;
    }

    public void setSubgroupNo(int subgroupNo) {
        this.subgroupNo = subgroupNo;
    }

    public String getGetSubgroupID() {
        return getSubgroupID;
    }

    public void setGetSubgroupID(String getSubgroupID) {
        this.getSubgroupID = getSubgroupID;
    }

    public Group(int id, String sem, String program, int groupNo, String groupID, int subgroupNo, String getSubgroupID) {
        this.id = id;
        this.sem = sem;
        this.program = program;
        this.groupNo = groupNo;
        this.groupID = groupID;
        this.subgroupNo = subgroupNo;
        this.getSubgroupID = getSubgroupID;
    }
}
