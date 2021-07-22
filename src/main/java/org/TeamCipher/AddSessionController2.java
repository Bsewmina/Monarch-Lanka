package org.TeamCipher;

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

import static org.TeamCipher.AddSessionController.lecturerData2;
import static org.TeamCipher.AddSessionController.lecurerData;

public class AddSessionController2 implements Initializable {

    public ChoiceBox choBox_group,choBox_tag,choBox_type,choBox_subject;
    public Button btn_submit;
    public Button btn_clear;
    public Spinner spinner_noOfStd,spinner_duration;

    //------------------------------------------------------------------------------------------

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;

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

    public void session(ActionEvent event) throws IOException {

        App.setRoot("ManageSession");
    }

    public void statistic(ActionEvent event) throws IOException {

        mainLabel.setText("statistics Clicked");
        App.setRoot("statistic");
    }

    public void studentGoups(ActionEvent event) {

    }

    public void location(ActionEvent event ) throws IOException {
        App.setRoot("location");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) throws IOException {


    }
    //------------------------------------------------------------------------------------------

    public void submit(ActionEvent event) throws IOException {
        String lec1,lec2;
        lec1 =lecurerData;
        lec2 =lecturerData2;
        String check = validate();
        if (check.equals("true")) {
            insert(lec1,lec2,choBox_tag.getValue().toString(),choBox_group.getValue().toString(),choBox_subject.getValue().toString(),
                    choBox_type.getValue().toString(),Integer.parseInt( spinner_noOfStd.getValue().toString()),Integer.parseInt(spinner_duration.getValue().toString()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Updated successfully");
            alert.showAndWait();
            App.setRoot("ManageSession");


        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText(check);
            alert.showAndWait();
            mainLabel.setText(validate());
        }
    }

    public void clear(ActionEvent event) {
        choBox_tag.setValue(null);
        choBox_group.setValue(null);
        choBox_type.setValue(null);
        spinner_noOfStd.getValueFactory().setValue(0);
        spinner_duration.getValueFactory().setValue(0);
    }

    public static void insert(String lecturer1,String lecturer2,String tag,String group,String subject,String sType,int student,int duration){

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Session VALUES (null,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,lecturer1);
            ps.setString(2,lecturer2);
            ps.setString(3,tag);
            ps.setString(4,group);
            ps.setString(5,subject);;
            ps.setString(6,sType);
            ps.setInt(7,student);
            ps.setInt(8,duration);

            ps.execute();
            App.setRoot("AddSession2");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while inserting data !!!!!");
        }
    }

    public void getTag() throws SQLException {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM tags");
            while (rs.next()) {
                choBox_tag.getItems().add(rs.getString("related_tag"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getGroup() throws SQLException {


        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM student_groups");
            while (rs.next()) {
                choBox_group.getItems().add(rs.getString("subgroup_id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getSubject() throws SQLException {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Subject");
            while (rs.next()) {
                choBox_subject.getItems().add(rs.getString("name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String validate() {
        if (choBox_tag.getValue() == null){
            return "Select Tag";
        }
        else if (choBox_group.getValue() == null){
            return "Select Group";
        }
        else if (choBox_subject.getValue() == null){
            return "Select Subject";
        }
        else if (choBox_type.getValue() == null){
            return "Select Session Type";
        }
        return  "true";
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            getTag();
            getGroup();
            getSubject();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}