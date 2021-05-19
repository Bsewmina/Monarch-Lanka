package org.TeamCipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LecturerManagmentController {

    //------------------------------------------------------------------------------------------
    String rank;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    public void Subject(ActionEvent event) {

        mainLabel.setText("Subject Clicked");
    }

    public void WorkingDH(ActionEvent event) {

        mainLabel.setText("WorkingDH Clicked");
    }

    public void lecturers(ActionEvent event) {

        mainLabel.setText("lecturers Clicked");
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

    public void studentGoups(ActionEvent event) throws IOException {

    }

    public void location(ActionEvent event ){
        mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }

    public void addLecturer(ActionEvent event) throws IOException {
        App.setRoot("new_lecturer");
    }
    //------------------------------------------------------------------------------------------

    @FXML
    private TextField txtField_id, txtField_name, texField_rank;

    @FXML
    private ChoiceBox<?> choiceBox_faculty, choiceBox_department, choiceBox_center, choiceBox_building, choiceBox_level;

    @FXML
    private Button btn_delete, btn_clear, btn_update;

    @FXML
    void Clear(ActionEvent event) {
        txtField_id.setText(null);
        txtField_name.setText(null);
        texField_rank.setText(null);
        choiceBox_faculty.setItems(null);
        choiceBox_department.setItems(null);
        choiceBox_center.setItems(null);
        choiceBox_building.setItems(null);

    }

    @FXML
    void Delete(ActionEvent event) {

        deleting(Integer.parseInt(txtField_id.getText().toString()));


    }
    @FXML
    void Update(ActionEvent event) {
        String id,l;
        id = txtField_id.getText().toString();
        l = (String) choiceBox_level.getValue();
        rank= l + "." + id;
        Updatedetails(Integer.parseInt(txtField_id.getText()),txtField_name.getText(),choiceBox_faculty.getValue().toString(),choiceBox_department.getValue().toString(),choiceBox_center.getValue().toString(),choiceBox_building.getValue().toString(),Integer.parseInt(choiceBox_level.getValue().toString()),rank);
    }

    public void deleting(int id){
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Lecturer WHERE empId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);

            ps.execute();
            System.out.println("Data Deleted Successfully !!!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void Updatedetails(int id,String name,String faculty,String dept,String center,String building,int level,String rank) {

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Lecturer SET empId=?, name=?, faculty=?, department=?, center=?, building=?, level=?, rank=? WHERE empId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,faculty);
            ps.setString(4,dept);
            ps.setString(5,center);
            ps.setString(6,building);
            ps.setInt(7,level);
            ps.setString(8,rank);
            ps.setInt(9,id);

            ps.execute();
            System.out.println("Data Updated Successfully !!!!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
