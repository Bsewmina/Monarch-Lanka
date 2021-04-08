package org.TeamCipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.EventListener;
import java.util.ResourceBundle;
import java.util.function.Function;

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
    final int min_IValue = 30;
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

    public void setBtnClearWDH (ActionEvent event){
        SpNumOfWorkingDays.commitValue();
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

    public void location(ActionEvent event ){
        mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }

}
