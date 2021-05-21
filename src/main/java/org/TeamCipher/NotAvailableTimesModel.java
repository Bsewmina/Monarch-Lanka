package org.TeamCipher;

public class NotAvailableTimesModel {


    public int id;
    public String lecturer;
    public String group_id;
    public String sub_group_id;
    public String session_id;
    public String date;
    public String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getSub_group_id() {
        return sub_group_id;
    }

    public void setSub_group_id(String sub_group_id) {
        this.sub_group_id = sub_group_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NotAvailableTimesModel(int id, String lecturer, String group_id, String sub_group_id, String session_id, String date, String time) {
        this.id = id;
        this.lecturer = lecturer;
        this.group_id = group_id;
        this.sub_group_id = sub_group_id;
        this.session_id = session_id;
        this.date = date;
        this.time = time;
    }


}
