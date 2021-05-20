package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class manageRoomController implements Initializable {


    @FXML
    private Label mainLabel;

    @FXML
    private TextField lid;

    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    ObservableList<Room> observableList = FXCollections.observableArrayList();


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

    public void location(ActionEvent event) throws IOException {
        App.setRoot("location");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }
    //------------------------------------------------------------------------------------------

    public TableView<Room> tableView;
    public TableColumn<Room, Integer> id;//column id
    public TableColumn<Room, String> session;
    public TableColumn<Room, String> room;
    public TableColumn<Room, String> sessionDetails;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Sessions");
            System.out.println("In the view Quiry");

            while (rs.next()) {
                observableList.add(new Room(rs.getInt("id"),rs.getString("session"),rs.getString("room"),rs.getString("sessionDetails")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        session.setCellValueFactory(new PropertyValueFactory<>("session"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        sessionDetails.setCellValueFactory(new PropertyValueFactory<>("sessionDetails"));
        tableView.setItems(observableList);


    }

    //ObservableList<Location> observableList = FXCollections.observableArrayList(

    // new Location(1,"OLD Building","IT-002","Lecture Hall",180),
    // new Location(2,"OLD Building","IT-002","Lecture Hall",180)

    // );

    public void DeleteBtn(ActionEvent event) {

        String ids = lid.getText().toString();

        int sendID = Integer.parseInt(ids);


        deleting(sendID);



    }

    public void deleting(int id){

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Sessions WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data Deleted Successfully !!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void switchToNewLocation() throws IOException {
        App.setRoot("createRoom");
    }



}
