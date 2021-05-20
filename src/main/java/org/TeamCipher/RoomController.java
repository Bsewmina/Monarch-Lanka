package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class RoomController implements Initializable {


    @FXML
    private Label mainLabel;

    @FXML
    private ChoiceBox sessionId,roomID;



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



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getSeccion();
        getRoom();




    }



    public void getSeccion(){

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Sessions");

            while (rs.next()) {
                sessionId.getItems().add(rs.getString("session"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void getRoom(){

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Sessions");

            while (rs.next()) {
                roomID.getItems().add(rs.getString("room"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }





    public void InsertData(ActionEvent event)throws IOException{

        if(sessionId.getSelectionModel().isEmpty()){

            alertError("Plese Select Session",null);


        }
        else if(roomID.getSelectionModel().isEmpty()){

            alertError("Plese Select Room",null);

        }
        else if(sessionDesID.getText().isEmpty()){

            alertError("Session description",null);

        }

        else {
            String session = sessionId.getValue().toString();
            String room = roomID.getValue().toString();
            String textArea = sessionDesID.getText();

            SendToDataBase(session, room, textArea);
        }

    }

   public void SendToDataBase(String session,String room,String text) throws IOException {


        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;

        try {
            String sql = "INSERT INTO Sessions  VALUES (null,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,session);
            ps.setString(2,room);
            ps.setString(3,text);
            ps.execute();
            System.out.println("Data added successfully !!!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

       alert(null);
       session(null);


    }


   public void ClearFields(ActionEvent event) {


       sessionId.setValue(null);
       roomID.setValue(null);
       sessionDesID.setText(null);



    }

    @FXML
    private void alertError(String text,ActionEvent event){

        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("ERROR");
        al.setContentText(text);
        al.setHeaderText(null);
        al.showAndWait();


    }

    @FXML
    private void alert(ActionEvent event){

        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Successful");
        al.setContentText("Your Record Add to System");
        al.setHeaderText(null);
        al.showAndWait();


    }



}
