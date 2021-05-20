package org.TeamCipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class NewSubjectController {

    public TextField txtField_subName,txt_Field_subCode;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    @FXML
    private Spinner<?> spinner_noLecHours, spinner_noTutHours, spinner_noLabHours, spinner_noEvalHours;

    @FXML
    private ChoiceBox<?> choiceBox_year;

    @FXML
    private RadioButton rButton1, rButton2;


    //----------------------------------------------------------------------------------------

    public void Subject(ActionEvent event) throws IOException {

        App.setRoot("Subject_Managment");
    }

    public void WorkingDH(ActionEvent event) {

        mainLabel.setText("WorkingDH Clicked");
    }

    public void lecturers(ActionEvent event) throws IOException {

        App.setRoot("Lecturer_Managment");
    }

    public void logout(ActionEvent event) {

        mainLabel.setText("logout Clicked");
    }

    public void session(ActionEvent event) {

        mainLabel.setText("session Clicked");
    }

    public void statistic(ActionEvent event) {

        mainLabel.setText("statistics Clicked");
    }

    public void studentGoups(ActionEvent event) {

    }

    public void location(ActionEvent event) {
        mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) throws IOException {

        App.setRoot("new_lecturer");
        //mainLabel.setText("Time Table Clicked");

    }
    //----------------------------------
    public int getSemester(){
        if (rButton1.isSelected()){
            mainLabel.setText(rButton1.getText());
            return 1;
        }
        else if (rButton2.isSelected()){
            mainLabel.setText(rButton2.getText());
            return 2;
        }
        return 0;
    }

    public String validate() {
        if(choiceBox_year.getValue() == null){
            return "insert Year";
        }
        else if(txtField_subName.getText().equals("")){
            return  "insert SubjectName";
        }
        else if(txt_Field_subCode.getText().equals("")){
            return  "insert SubjectCode";
        }
        else if(spinner_noLecHours.getValue() == null){
            return  "insert Lecture Hours";
        }
        else if(spinner_noTutHours.getValue() == null){
            return  "insert Tute Hours";
        }
        else if(spinner_noLabHours.getValue() == null){
            return  "insert Lab Hours";
        }
        else if(spinner_noEvalHours.getValue() == null){
            return  "insert Evaluation Hours";
        }
        return  "true";
    }

    public void clearDetails(ActionEvent event) {
        txtField_subName.setText("");
        txt_Field_subCode.setText("");
    }

    public void saveDetails(ActionEvent event) {
    String check = validate();

    //validate fields
    int semester = getSemester();
        if(!check.equals("true")){
            System.out.println(check);
        }
        //validate year
        if(semester == 0){
            System.out.println("add semester");
        }
        else
            insert(txtField_subName.getText(),txt_Field_subCode.getText(),Integer.parseInt((String) choiceBox_year.getValue()),semester,Integer.parseInt(spinner_noLecHours.getValue().toString()),Integer.parseInt(spinner_noTutHours.getValue().toString()),Integer.parseInt(spinner_noLabHours.getValue().toString()),Integer.parseInt(spinner_noEvalHours.getValue().toString()));
    }


    public static void insert(String name,String code,int year,int semester,int lec_hours,int tut_hours,int lab_hours,int evaluation_hours){

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Subject VALUES (null,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,code);
            ps.setInt(3,year);
            ps.setInt(4,semester);
            ps.setInt(5,lec_hours);
            ps.setInt(6,tut_hours);
            ps.setInt(7,lab_hours);
            ps.setInt(8,evaluation_hours);

            ps.execute();
            System.out.println("Data added successfully !!!!!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while inserting data !!!!!");
        }

    }

}