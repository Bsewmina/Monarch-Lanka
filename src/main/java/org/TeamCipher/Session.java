package org.TeamCipher;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Session {

    private SimpleIntegerProperty id;
    private SimpleStringProperty lecturer1;
    private SimpleStringProperty lecturer2;
    private SimpleStringProperty tag;
    private SimpleStringProperty groudId;
    private SimpleStringProperty subject;
    private SimpleStringProperty sessionType;
    private SimpleIntegerProperty noOfStudent;
    private SimpleIntegerProperty duration;


    public Session(int s_id, String s_lecturer1, String s_lecturer2, String s_tag, String s_groudId, String s_subject, String s_sessionType, int s_noOfStudent, int s_duration) {
        this.id = new SimpleIntegerProperty(s_id);
        this.lecturer1 =new SimpleStringProperty(s_lecturer1) ;
        this.lecturer2 =new SimpleStringProperty(s_lecturer2) ;
        this.tag = new SimpleStringProperty(s_tag);
        this.groudId =new SimpleStringProperty(s_groudId);
        this.subject =new SimpleStringProperty(s_subject);
        this.sessionType =new SimpleStringProperty(s_sessionType);
        this.noOfStudent =new SimpleIntegerProperty(s_noOfStudent);
        this.duration = new SimpleIntegerProperty(s_duration);
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

    public String getLecturer1() {
        return lecturer1.get();
    }

    public SimpleStringProperty lecturer1Property() {
        return lecturer1;
    }

    public void setLecturer1(String lecturer1) {
        this.lecturer1.set(lecturer1);
    }

    public String getLecturer2() {
        return lecturer2.get();
    }

    public SimpleStringProperty lecturer2Property() {
        return lecturer2;
    }

    public void setLecturer2(String lecturer2) {
        this.lecturer2.set(lecturer2);
    }

    public String getTag() {
        return tag.get();
    }

    public SimpleStringProperty tagProperty() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag.set(tag);
    }

    public String getGroudId() {
        return groudId.get();
    }

    public SimpleStringProperty groudIdProperty() {
        return groudId;
    }

    public void setGroudId(String groudId) {
        this.groudId.set(groudId);
    }

    public String getSubject() {
        return subject.get();
    }

    public SimpleStringProperty subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getSessionType() {
        return sessionType.get();
    }

    public SimpleStringProperty sessionTypeProperty() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType.set(sessionType);
    }

    public int getNoOfStudent() {
        return noOfStudent.get();
    }

    public SimpleIntegerProperty noOfStudentProperty() {
        return noOfStudent;
    }

    public void setNoOfStudent(int noOfStudent) {
        this.noOfStudent.set(noOfStudent);
    }

    public int getDuration() {
        return duration.get();
    }

    public SimpleIntegerProperty durationProperty() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration.set(duration);
    }
}
