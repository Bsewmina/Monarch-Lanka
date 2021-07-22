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
    private Spinner<Integer> spinner_noLecHours, spinner_noTutHours, spinner_noLabHours, spinner_noEvalHours;

    @FXML
    private ChoiceBox<?> choiceBox_year;

    @FXML
    private RadioButton rButton1, rButton2;


    public void Subject(ActionEvent event) throws IOException {
        App.setRoot("Subject_Managment");
    }

    public void WorkingDH(ActionEvent event) throws IOException {
        App.setRoot("DaysHours");
    }

    public void lecturers(ActionEvent event) throws IOException {
        App.setRoot("Lecturer_Managment");
    }

    public void session(ActionEvent event) throws IOException {
        App.setRoot("manageRoom");
    }

    public void statistic(ActionEvent event) throws IOException {
        App.setRoot("statistic");
    }

    public void studentGoups(ActionEvent event) throws IOException {
        App.setRoot("student_groups_menu");
    }

    public void location(ActionEvent event ) throws IOException {
        App.setRoot("location");
    }

    public void tags(ActionEvent event) throws IOException {
        App.setRoot("tags_menu");
    }

    public void timeTables(ActionEvent event) throws IOException {
    }
    //------------------------------------------------------------------------------------------

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
            return "Insert Year";
        }
        else if(getSemester() == 0){
            return "Select Semester";
        }
        else if(txtField_subName.getText().equals("")){
            return  "Insert SubjectName";
        }
        else if(txt_Field_subCode.getText().equals("")){
            return  "Insert SubjectCode";
        }
        else if(spinner_noLecHours.getValueFactory().getValue() == null){
            return  "Insert Lecture Hours";
        }
        else if(spinner_noTutHours.getValueFactory().getValue() == null){
            return  "Insert Tute Hours";
        }
        else if(spinner_noLabHours.getValueFactory().getValue() == null){
            return  "Insert Lab Hours";
        }
        else if(spinner_noEvalHours.getValueFactory().getValue() == null){
            return  "Insert Evaluation Hours";
        }
        return  "true";
    }

    public void clearDetails(ActionEvent event) {
        txtField_subName.setText("");
        txt_Field_subCode.setText("");
        spinner_noLecHours.getValueFactory().setValue(0);
        spinner_noTutHours.getValueFactory().setValue(0);
        spinner_noLabHours.getValueFactory().setValue(0);
        spinner_noEvalHours.getValueFactory().setValue(0);
    }

    public void saveDetails(ActionEvent event) {
    String check = validate();
    int semester = getSemester();

        if(!check.equals("true")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(check);
            alert.showAndWait();

        } else {

            insert(txtField_subName.getText(),txt_Field_subCode.getText(),Integer.parseInt((String) choiceBox_year.getValue()),semester,Integer.parseInt(spinner_noLecHours.getValue().toString()),Integer.parseInt(spinner_noTutHours.getValue().toString()),Integer.parseInt(spinner_noLabHours.getValue().toString()),Integer.parseInt(spinner_noEvalHours.getValue().toString()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Data Added successfully");
            alert.setHeaderText(check);
            alert.showAndWait();
        }
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