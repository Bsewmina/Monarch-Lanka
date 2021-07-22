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
