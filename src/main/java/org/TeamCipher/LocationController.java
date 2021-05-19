package org.TeamCipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LocationController {

    //------------------------------------------------------------------------------------------

    @FXML
    private Label mainLabel;
    @FXML
    private TextField bname,rname,capacity;
    @FXML
    private RadioButton lab,lhall;






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

    public void statistic(ActionEvent event) throws IOException {

        mainLabel.setText("statistics Clicked");
        App.setRoot("statistic");
    }

    public void studentGoups(ActionEvent event) throws IOException {
        App.setRoot("student_groups_menu");
    }

    public void location(ActionEvent event ) throws IOException {
        App.setRoot("location");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }
    //------------------------------------------------------------------------------------------

    @FXML
    private void switchToStatistics() throws IOException {
        App.setRoot("statistic");
    }

    @FXML
    private void switchToManageLocation() throws IOException {
        App.setRoot("manageLocation");
    }

    @FXML
    private void switchToNewLocation() throws IOException {
        App.setRoot("createLocation");
    }







    public void InsertData(ActionEvent event) {

       String cap = capacity.getText();
        int i=Integer.parseInt(cap);
        SendToDataBase(bname.getText(),rname.getText(),i);


    }



    public void SendToDataBase(String builname,String roname,int capacity){

        String roomType;

        if(lhall.isSelected()){

            roomType = "Lecture Hall";

        }else{
            roomType = "Laboratory";

        }




        int id=0;

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Location  VALUES (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,builname);
            ps.setString(3,roname);
            ps.setInt(4,capacity);
            ps.setString(5,roomType);
            ps.execute();
            System.out.println("Data added successfully !!!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }








}
