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
    ObservableList<Session> observableList2 = FXCollections.observableArrayList();
    ObservableList<Session> observableList3 = FXCollections.observableArrayList();
    ObservableList<Session> observableList4 = FXCollections.observableArrayList();


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

    public TableView<Room> tableView;
    public TableColumn<Room, Integer> id;//column id
    public TableColumn<Room, String> session;
    public TableColumn<Room, String> room;
    public TableColumn<Room, String> sessionDetails;

//------------- Session consecutive --------------
    public TableView<Session> tableView1;
    public TableColumn<Session,Integer>ID;
    public TableColumn<Session,String>L1;
    public TableColumn<Session,String>L2;
    public TableColumn<Session,String>TAG;
    public TableColumn<Session,String>GID;
    public TableColumn<Session,String>SUBJECT;
    public TableColumn<Session,String>TYPE;
    public TableColumn<Session,Integer>NOS;
    public TableColumn<Session,Integer>DURATION;



    //-------------------- non overlapping ---------------


    public TableView<Session> tableView21;
    public TableColumn<Session,Integer>NON_SES_ID;
    public TableColumn<Session,String>NON_SES_L1;
    public TableColumn<Session,String>NON_SES_L2;
    public TableColumn<Session,String>NON_SES_TAG;
    public TableColumn<Session,String>NON_SES_GID;
    public TableColumn<Session,String>NON_SES_SUBJECT;
    public TableColumn<Session,String>NON_SES_TYPE;
    public TableColumn<Session,Integer>NON_SES_NOS;
    public TableColumn<Session,Integer>NON_SES_DURATION;

    //paralel

    public TableView<Session> tableView2;
    public TableColumn<Session,Integer>PARA_SES_ID;
    public TableColumn<Session,String>PARA_SES_L1;
    public TableColumn<Session,String>PARA_SES_L2;
    public TableColumn<Session,String>PARA_SES_TAG;
    public TableColumn<Session,String>PARA_SES_GID;
    public TableColumn<Session,String>PARA_SES_SUBJECT;
    public TableColumn<Session,String>PARA_SES_TYPE;
    public TableColumn<Session,Integer>PARA_SES_NOS;
    public TableColumn<Session,Integer>PARA_SES_DURATION;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SessionView();
        ConsecityView();
        NonOverlappingView();
        ParalelView();



    }

    //ObservableList<Location> observableList = FXCollections.observableArrayList(

    // new Location(1,"OLD Building","IT-002","Lecture Hall",180),
    // new Location(2,"OLD Building","IT-002","Lecture Hall",180)

    // );




    public void SessionView(){


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


    public void ConsecityView(){

        String number="consecutive";

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {

            rs = con.createStatement().executeQuery("SELECT * FROM Session where s_sessionType LIKE '"+number+"'");
            System.out.println("in consecit");

            while (rs.next()) {
                observableList2.add(new Session(rs.getInt("s_id"),rs.getString("s_lecturer1"),rs.getString("s_lecturer2"),rs.getString("s_tag"),rs.getString("s_groudId"),rs.getString("s_subject"),rs.getString("s_sessionType"),rs.getInt("s_noOfStudent"),rs.getInt("s_duration")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        L1.setCellValueFactory(new PropertyValueFactory<>("lecturer1"));
        L2.setCellValueFactory(new PropertyValueFactory<>("lecturer2"));
        TAG.setCellValueFactory(new PropertyValueFactory<>("tag"));
        GID.setCellValueFactory(new PropertyValueFactory<>("groudId"));
        SUBJECT.setCellValueFactory(new PropertyValueFactory<>("subject"));
        TYPE.setCellValueFactory(new PropertyValueFactory<>("sessionType"));
        NOS.setCellValueFactory(new PropertyValueFactory<>("noOfStudent"));
        DURATION.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableView1.setItems(observableList2);



    }




    public void ParalelView(){

        String number="Parallel";

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {

            rs = con.createStatement().executeQuery("SELECT * FROM Session where s_sessionType LIKE '"+number+"'");
            System.out.println("in consecit");

            while (rs.next()) {
                observableList4.add(new Session(rs.getInt("s_id"),rs.getString("s_lecturer1"),rs.getString("s_lecturer2"),rs.getString("s_tag"),rs.getString("s_groudId"),rs.getString("s_subject"),rs.getString("s_sessionType"),rs.getInt("s_noOfStudent"),rs.getInt("s_duration")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }






        PARA_SES_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        PARA_SES_L1.setCellValueFactory(new PropertyValueFactory<>("lecturer1"));
        PARA_SES_L2.setCellValueFactory(new PropertyValueFactory<>("lecturer2"));
        PARA_SES_TAG.setCellValueFactory(new PropertyValueFactory<>("tag"));
        PARA_SES_GID.setCellValueFactory(new PropertyValueFactory<>("groudId"));
        PARA_SES_SUBJECT.setCellValueFactory(new PropertyValueFactory<>("subject"));
        PARA_SES_TYPE.setCellValueFactory(new PropertyValueFactory<>("sessionType"));
        PARA_SES_NOS.setCellValueFactory(new PropertyValueFactory<>("noOfStudent"));
        PARA_SES_DURATION.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableView2.setItems(observableList4);



    }



    public void NonOverlappingView(){

        String number="Non-overlapping";

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {

            rs = con.createStatement().executeQuery("SELECT * FROM Session where s_sessionType LIKE '"+number+"'");
            System.out.println("in consecit");

            while (rs.next()) {
                observableList3.add(new Session(rs.getInt("s_id"),rs.getString("s_lecturer1"),rs.getString("s_lecturer2"),rs.getString("s_tag"),rs.getString("s_groudId"),rs.getString("s_subject"),rs.getString("s_sessionType"),rs.getInt("s_noOfStudent"),rs.getInt("s_duration")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        NON_SES_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        NON_SES_L1.setCellValueFactory(new PropertyValueFactory<>("lecturer1"));
        NON_SES_L2.setCellValueFactory(new PropertyValueFactory<>("lecturer2"));
        NON_SES_TAG.setCellValueFactory(new PropertyValueFactory<>("tag"));
        NON_SES_GID.setCellValueFactory(new PropertyValueFactory<>("groudId"));
        NON_SES_SUBJECT.setCellValueFactory(new PropertyValueFactory<>("subject"));
        NON_SES_TYPE.setCellValueFactory(new PropertyValueFactory<>("sessionType"));
        NON_SES_NOS.setCellValueFactory(new PropertyValueFactory<>("noOfStudent"));
        NON_SES_DURATION.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tableView21.setItems(observableList3);



    }











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

    @FXML
    public void manageSession() throws IOException {
        App.setRoot("ManageSession");
    }
}
