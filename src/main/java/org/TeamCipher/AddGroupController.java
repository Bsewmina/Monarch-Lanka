package org.TeamCipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AddGroupController implements Initializable {

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    @FXML
    private ChoiceBox<?> program;

    @FXML
    private TextField academic_year, group_id, subgroup_id;


    @FXML
    private Spinner<Integer> group_inc; // Value injected by FXMLLoader
    final int INITIAL_VALUE = 0;
    SpinnerValueFactory<Integer> group = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, INITIAL_VALUE);

    @FXML
    private Spinner<Integer> subgroup_inc; // Value injected by FXMLLoader
    SpinnerValueFactory<Integer> subgroup = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, INITIAL_VALUE);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        group_inc.setValueFactory(group);
        subgroup_inc.setValueFactory(subgroup);
    }


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

    public void location(ActionEvent event) {
        mainLabel.setText("location Clicked");
    }


    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }

    public void tags(ActionEvent event) throws IOException {
        App.setRoot("tags_menu");
        ;
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
        academic_year.setText(null);
        //program.setItems(null);
        group_inc.getValueFactory().setValue(0);
        subgroup_inc.getValueFactory().setValue(0);
        group_id.setText(null);
        subgroup_id.setText(null);

    }
    //------------------------------------------------------------- Generate IDs
    //Eg: Y1.S1.IT.01.1
    @FXML
    void genarateIDs(ActionEvent event) {
        String ay,p,g,sg;
        ay = academic_year.getText();
        p = (String) program.getValue();
        g = group_inc.getValue().toString();
        sg = subgroup_inc.getValue().toString();
        String grpID = ay + "." + p + "." + g;
        String subgrpID = ay + "." + p + "." + g + "." + sg;
        group_id.setText(grpID);
        subgroup_id.setText(subgrpID);
    }
//------------------------------------------------------------- DB Insert


    public void InsertData(ActionEvent event) {
        String academic_yearText = academic_year.getText();
        String programName = program.getValue().toString();
        String groupNumber = group_inc.getValue().toString();
        String group_idText = group_id.getText();
        String subNumber = subgroup_inc.getValue().toString();
        String subgroup_idText = subgroup_id.getText();


        SendToDataBase(academic_yearText,programName,groupNumber,group_idText,subNumber,subgroup_idText);

    }

    public void SendToDataBase(String academic_year, String program, String group_inc,  String group_id,String subgroup_inc, String subgroup_id) {



        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO student_groups  VALUES (NULL ,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);

            ps.setString(1, academic_year);
            ps.setString(2, program);
            ps.setInt(3, Integer.parseInt(group_inc));
            ps.setString(4, group_id);
            ps.setInt(5, Integer.parseInt(subgroup_inc));
            ps.setString(6, subgroup_id);
            ps.execute();

            System.out.println("Data added successfully !!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }






}
