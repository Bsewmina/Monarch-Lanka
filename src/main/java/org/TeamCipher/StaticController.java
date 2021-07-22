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
    private Label subjectCount,groupID,lsubject;

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

        //get latest lecture
         int idnumber =  getUpdatedLecture();
         System.out.println(idnumber);
         getLatestLecture(idnumber);
         //end of latest lecture


         piechartShow();

        //get latest studentGroup
         int stgroup=   getUpdatedStudentGroup();
         getLatestGroup(stgroup);
         //end of studentGroup


        //get latest Subject
       int subjID= getUpdatedSubject();
        getLatestSubject(subjID);


    }



    public void piechartShow(){

        String name1 = "Available";
        String name2 = "Non-avalable";

        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList(


                new PieChart.Data(name1,15),
                new PieChart.Data(name2,45),
                new PieChart.Data("lecture",20),
                new PieChart.Data("overlap",20)

        );

        locationChart.setData(pieChartData);


    }



    public void getSeccion(){


        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT count(name) FROM Lecturer");

            while (rs.next()) {
               // sessionId.getItems().add(rs.getString("session"));
                reglecture.setText(rs.getString("count(name)"));
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
            rs = con.createStatement().executeQuery("SELECT count(ID) FROM student_groups");
            rs.next();
            studentCount.setText(rs.getString("count(ID)"));


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
            rs = con.createStatement().executeQuery("SELECT count(id) FROM Subject");
            rs.next();
            subjectCount.setText(rs.getString("count(id)"));


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
            rs = con.createStatement().executeQuery("SELECT empId FROM Lecturer ORDER BY empId DESC LIMIT 1");
            rs.next();
            //lectureID.setText(rs.getString("ID"));
            id = Integer.parseInt(rs.getString("empId"));



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


    //---------------------------------------------- stuent group latest --------

    public int getUpdatedStudentGroup(){

        Connection con = SQliteConnection.DBconnect();

        int id =0;
        ResultSet rs = null;

        try {
            rs = con.createStatement().executeQuery("SELECT ID FROM student_groups ORDER BY ID DESC LIMIT 1");
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
    public void getLatestGroup(int id){

        Connection con = SQliteConnection.DBconnect();

        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM student_groups where ID ="+id+"");

            while(rs.next()) {

                groupID.setText(rs.getString("group_id"));

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


//------------------------ end of student group --------------------------------------




//---- subject latest

    public int getUpdatedSubject(){

        Connection con = SQliteConnection.DBconnect();

        int id =0;
        ResultSet rs = null;

        try {
            rs = con.createStatement().executeQuery("SELECT id FROM Subject ORDER BY ID DESC LIMIT 1");
            rs.next();
            //lectureID.setText(rs.getString("ID"));
            id = Integer.parseInt(rs.getString("id"));



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
    public void getLatestSubject(int id){

        Connection con = SQliteConnection.DBconnect();

        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Subject where id ="+id+"");

            while(rs.next()) {

                lsubject.setText(rs.getString("name"));

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



// --------- end of subject latest


    public void getLatestLecture(int id){

        Connection con = SQliteConnection.DBconnect();

        try {

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Lecturer where empId ="+id+"");

             while(rs.next()) {

               lectureID.setText(rs.getString("name"));

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
