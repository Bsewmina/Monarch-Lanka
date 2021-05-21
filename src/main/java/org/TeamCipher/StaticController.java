package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class StaticController implements Initializable {

    @FXML
    private Label mainLabel,reglecture,regiRooms,lectureID,studentCount;

    @FXML
    private Label subjectCount;

    @FXML
    private ChoiceBox sessionId,roomID;

    @FXML
    private PieChart locationChart;



    @FXML
    private TextArea sessionDesID;

    ObservableList<Room> observableList = FXCollections.observableArrayList();


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

    public void session(ActionEvent event) throws IOException {

        mainLabel.setText("session Clicked");
        App.setRoot("manageRoom");
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

    Connection con = SQliteConnection.DBconnect();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        getSeccion();//get the session count to Statics
        getStudentCount();// student count
        getSubjectCount(); // subject count

        getRegisteredRoom(); //

         int idnumber =  getUpdatedLecture();

         System.out.println(idnumber);

         getLatestLecture(idnumber);


         piechartShow();


    }



    public void piechartShow(){

        String name1 = "cars";
        String name2 = "Bike";

        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(


                new PieChart.Data(name1,13),
                new PieChart.Data(name2,24),
                new PieChart.Data("Heli",23),
                new PieChart.Data("lorry",40)

        );

        locationChart.setData(pieChartData);


    }



    public void getSeccion(){


        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT count(session) FROM Sessions");

            while (rs.next()) {
               // sessionId.getItems().add(rs.getString("session"));
                reglecture.setText(rs.getString("count(session)"));
            }




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void getRegisteredRoom() {

        Connection con = SQliteConnection.DBconnect();

        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT count(buildingName) FROM Location");
            rs.next();
            regiRooms.setText(rs.getString("count(buildingName)"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    //---------------Registered Student Count---------------------

    public void getStudentCount() {

        Connection con = SQliteConnection.DBconnect();

        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT count(buildingName) FROM Location");
            rs.next();
            studentCount.setText(rs.getString("count(buildingName)"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    //------------------------------ Subject Count --------------------------

    public void getSubjectCount() {

        Connection con = SQliteConnection.DBconnect();

        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT count(buildingName) FROM Location");
            rs.next();
            subjectCount.setText(rs.getString("count(buildingName)"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }










    public int getUpdatedLecture(){

        Connection con = SQliteConnection.DBconnect();

        int id =0;
        ResultSet rs = null;

        try {
            rs = con.createStatement().executeQuery("SELECT ID FROM Location ORDER BY id DESC LIMIT 1");
            rs.next();
            //lectureID.setText(rs.getString("ID"));
            id = Integer.parseInt(rs.getString("ID"));



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return id;




    }





    public void getLatestLecture(int id){

        Connection con = SQliteConnection.DBconnect();

        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Location where ID ="+id+"");

             while(rs.next()) {

               lectureID.setText(rs.getString("buildingName"));

           }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
