package org.TeamCipher;

public class NotAvailableRoomsModel {

    public int sid;
    public String session_id;
    public String s_date;
    public String group_id;
    public String room_id;
    public String s_time;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public NotAvailableRoomsModel(int sid, String session_id, String s_date, String group_id, String room_id, String s_time) {
        this.sid = sid;
        this.session_id = session_id;
        this.s_date = s_date;
        this.group_id = group_id;
        this.room_id = room_id;
        this.s_time = s_time;
    }



    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getS_date() {
        return s_date;
    }

    public void setS_date(String s_date) {
        this.s_date = s_date;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

}
