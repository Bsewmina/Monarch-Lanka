package org.TeamCipher;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class AddTagsController {

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;
    @FXML
    private TextField tagName,tagCode;

    @FXML
    private ChoiceBox relTag;


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
        App.setRoot("student_groups_menu");
    }

    public void location(ActionEvent event ){
        mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) throws IOException {
        App.setRoot("tags_menu");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }

    public void manage_existing_tags(ActionEvent event) throws IOException {
        App.setRoot("add_tags");
    }

    @FXML
    void create_new_stdGroups(ActionEvent event) throws IOException {
        App.setRoot("add_groups");
    }

    public void manage_existing_stdGroups(ActionEvent event) throws IOException {
        App.setRoot("student_existing");
    }

    //------------------------------------------------------------- Clear Field
    @FXML
    void clear(ActionEvent event) {

//------------------------------------------------------------- DB Insert
    }
    public void InsertData(ActionEvent event) {
        String tagName_Text = tagName.getText();
        String tagCode_Text = tagCode.getText();
        String relTag_Text = relTag.getValue().toString();


        SendToDataBase(tagName_Text,tagCode_Text,relTag_Text);

    }

    public void SendToDataBase(String tagName, String tagCode, String relTag) {



        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO tags  VALUES (NULL,?,?,?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, tagName);
            ps.setString(2, tagCode);
            ps.setString(3, relTag);

            ps.execute();

            System.out.println("Data added successfully !!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
