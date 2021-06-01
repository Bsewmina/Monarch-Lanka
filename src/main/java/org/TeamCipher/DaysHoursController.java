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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DaysHoursController implements Initializable {

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    // working days and hours form

    @FXML // fx:id="SpNumOfWorkingDays"
    private Spinner<Integer> SpNumOfWorkingDays; // Value injected by FXMLLoader
    final int INITIAL_VALUE = 0;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,7,INITIAL_VALUE);

// Working days selection Check boxes
    @FXML
    public CheckBox CbMonday;
    @FXML
    public CheckBox CbTuesday;
    @FXML
    public CheckBox CbWednesday;
    @FXML
    public CheckBox CbThursday;
    @FXML
    public CheckBox CbFriday;
    @FXML
    public CheckBox CbSaturday;
    @FXML
    public CheckBox CbSunday;

    // Working time according to days


    @FXML
    private Spinner<Integer> SpMonHrs;
    SpinnerValueFactory<Integer> svfMonHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> SpMonMins;
    final int min_IValue = 0;
    SpinnerValueFactory<Integer> svfMonMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> SpTuesHrs;
    SpinnerValueFactory<Integer> svfTuesHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> SpTuesMins;
    SpinnerValueFactory<Integer> svfTuesMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> SpWedHrs;
    SpinnerValueFactory<Integer> svfWedHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> SpWedMins;
    SpinnerValueFactory<Integer> svfWedMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> SpThrsHrs;
    SpinnerValueFactory<Integer> svfThrsHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> SpThrsMins;
    SpinnerValueFactory<Integer> svfThrsMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> SpFriHrs;
    SpinnerValueFactory<Integer> svfFriHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> SpFriMins;
    SpinnerValueFactory<Integer> svfFriMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> SpSatHrs;
    SpinnerValueFactory<Integer> svfSatHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> SpSatMins;
    SpinnerValueFactory<Integer> svfSatMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> SpSunHrs;
    SpinnerValueFactory<Integer> svfSunHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> SpSunMins;
    SpinnerValueFactory<Integer> svfSunMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);

    public void DaysCheckBoxes(ActionEvent event){
        if(CbMonday.isSelected()){
            SpMonHrs.setDisable(false);
            SpMonMins.setDisable(false);
        }else {
            SpMonHrs.setDisable(true);
            SpMonMins.setDisable(true);
        }

        if(CbTuesday.isSelected()){
            SpTuesHrs.setDisable(false);
            SpTuesMins.setDisable(false);
        }else {
            SpTuesHrs.setDisable(true);
            SpTuesMins.setDisable(true);
        }

        if(CbWednesday.isSelected()){
            SpWedHrs.setDisable(false);
            SpWedMins.setDisable(false);
        }else {
            SpWedHrs.setDisable(true);
            SpWedMins.setDisable(true);
        }

        if(CbThursday.isSelected()){
            SpThrsHrs.setDisable(false);
            SpThrsMins.setDisable(false);
        }else {
            SpThrsHrs.setDisable(true);
            SpThrsMins.setDisable(true);
        }

        if(CbFriday.isSelected()){
            SpFriHrs.setDisable(false);
            SpFriMins.setDisable(false);
        }else {
            SpFriHrs.setDisable(true);
            SpFriMins.setDisable(true);
        }

        if(CbSaturday.isSelected()){
            SpSatHrs.setDisable(false);
            SpSatMins.setDisable(false);
        }else {
            SpSatHrs.setDisable(true);
            SpSatMins.setDisable(true);
        }

        if(CbSunday.isSelected()){
            SpSunHrs.setDisable(false);
            SpSunMins.setDisable(false);
        }else {
            SpSunHrs.setDisable(true);
            SpSunMins.setDisable(true);
        }

    }


    @FXML
    private Button BtnClearWDH;
    @FXML
    private Button BtnViewWDH;

    public void setBtnClearWDH (ActionEvent event){
        SpNumOfWorkingDays.commitValue();
    }

    public void BtnViewWDH (ActionEvent event) throws IOException {
        App.setRoot("DaysHoursTable");
    }

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



    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        SpNumOfWorkingDays.setValueFactory(svf);
        SpMonHrs.setValueFactory(svfMonHrs);
        SpMonMins.setValueFactory(svfMonMin);
        SpTuesHrs.setValueFactory(svfTuesHrs);
        SpTuesMins.setValueFactory(svfTuesMin);
        SpWedHrs.setValueFactory(svfWedHrs);
        SpWedMins.setValueFactory(svfWedMin);
        SpThrsHrs.setValueFactory(svfThrsHrs);
        SpThrsMins.setValueFactory(svfThrsMin);
        SpFriHrs.setValueFactory(svfFriHrs);
        SpFriMins.setValueFactory(svfFriMin);
        SpSatHrs.setValueFactory(svfSatHrs);
        SpSatMins.setValueFactory(svfSatMin);
        SpSunHrs.setValueFactory(svfSunHrs);
        SpSunMins.setValueFactory(svfSunMin);



    }



    public void Insert() {

        int id = 0;
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        {
            try {
                String sql = "INSERT INTO workingdays  VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setString(1, String.valueOf(SpNumOfWorkingDays.getValue()));
                ps.setString(2, CbMonday.getText());
                ps.setString(3, String.valueOf(SpMonHrs.getValue()+"Hrs "+SpMonMins.getValue()+"Min"));
                ps.setString(4, CbTuesday.getText());
                ps.setString(5, String.valueOf(SpTuesHrs.getValue()+"Hrs "+SpTuesMins.getValue()+"Min"));
                ps.setString(6, CbWednesday.getText());
                ps.setString(7, String.valueOf(SpWedHrs.getValue()+"Hrs "+SpWedMins.getValue()+"Min"));
                ps.setString(8, CbThursday.getText());
                ps.setString(9, String.valueOf(SpThrsHrs.getValue()+"Hrs "+SpWedMins.getValue()+"Min"));
                ps.setString(10, CbFriday.getText());
                ps.setString(11, String.valueOf(SpFriHrs.getValue()+"Hrs "+SpFriMins.getValue()+"Min"));
                ps.setString(12, CbSaturday.getText());
                ps.setString(13, String.valueOf(SpSatHrs.getValue()+"Hrs "+SpSatMins.getValue()+"Min"));
                ps.setString(14, CbSunday.getText());
                ps.setString(15, String.valueOf(SpSunHrs.getValue()+"Hrs "+SpSunMins.getValue()+"Min"));

                ps.execute();
                System.out.println("Data added successfully !!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
