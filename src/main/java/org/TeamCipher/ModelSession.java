package org.TeamCipher;

public class ModelSession {

    private int id;
    private String lec1,lec2,tag,group,subject,ssType;

    public ModelSession(int id, String lec1, String lec2, String tag, String group, String subject, String ssType) {
        this.id = id;
        this.lec1 = lec1;
        this.lec2 = lec2;
        this.tag = tag;
        this.group = group;
        this.subject = subject;
        this.ssType = ssType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLec1() {
        return lec1;
    }

    public void setLec1(String lec1) {
        this.lec1 = lec1;
    }

    public String getLec2() {
        return lec2;
    }

    public void setLec2(String lec2) {
        this.lec2 = lec2;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSsType() {
        return ssType;
    }

    public void setSsType(String ssType) {
        this.ssType = ssType;
    }
}
