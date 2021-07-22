package org.TeamCipher;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class LocationController {

    //------------------------------------------------------------------------------------------

    @FXML
    private Label mainLabel;
    @FXML
    private TextField bname,rname,capacity,fieldid;
    @FXML
    private RadioButton lab,lhall;

    @FXML
    private Button save,clear;






    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

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

    public void locationManage(ActionEvent event ) throws IOException {
        App.setRoot("manageLocation");
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







    public void InsertData(ActionEvent event) throws IOException {

        if(bname.getText().isEmpty()){

            alertError("Please Enter Building Name",null);


        }
        else if(rname.getText().isEmpty()){

            alertError("Please Enter Room Name",null);

        }
        else if(!lhall.isSelected() && !lab.isSelected() ){

            alertError("Please Select",null);

        }
        else if(capacity.getText().isEmpty()){

            alertError("Please Enter Capacity",null);

        }

        else {

            String cap = capacity.getText();
            int i = Integer.parseInt(cap);

            SendToDataBase(bname.getText(), rname.getText(), i);
        }

    }

    public void SendToDataBase(String builname,String roname,int capacity) throws IOException {

        String roomType;

        if(lhall.isSelected()){

            roomType = "Lecture Hall";

        }else{
            roomType = "Laboratory";

        }


        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Location  VALUES (null,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,builname);
            ps.setString(2,roname);
            ps.setInt(3,capacity);
            ps.setString(4,roomType);
            ps.execute();
            System.out.println("Data added successfully !!!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        alert(null);

        locationManage(null);

    }


    public void ClearFields(ActionEvent event) {

        capacity.clear();
        bname.clear();
        rname.clear();

    }


    @FXML
    private void alert(ActionEvent event){

        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Successful");
        al.setContentText("Your Record Add to System");
        al.setHeaderText(null);
        al.showAndWait();


    }

    @FXML
    private void alertError(String text,ActionEvent event){

        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("ERROR");
        al.setContentText(text);
        al.setHeaderText(null);
        al.showAndWait();


    }






}
