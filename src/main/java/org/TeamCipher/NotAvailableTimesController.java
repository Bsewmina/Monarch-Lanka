package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.Optional;
import java.util.ResourceBundle;


public class NotAvailableTimesController implements Initializable {
    final int INITIAL_VALUE = 0;
    final int min_IValue = 0;
    @FXML
    void Btn_view(ActionEvent event) throws IOException {
    App.setRoot("ManageNotAvailableTimes");
    }

    @FXML
    private ChoiceBox CHB_Session_id;
    @FXML
    private ChoiceBox CBH_lecturer;
    @FXML
    private ChoiceBox CBH_group_id;
    @FXML
    private ChoiceBox CBH_sub_group_id;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;

    @FXML
    private DatePicker ID_SetDate;
    @FXML
    private Spinner<Integer> Sp_S_HR;
    SpinnerValueFactory<Integer> svfStHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> Sp_S_Min;
    SpinnerValueFactory<Integer> svfStMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> Sp_E_HR;
    SpinnerValueFactory<Integer> svfEndHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> Sp_E_Min;
    SpinnerValueFactory<Integer> svfEndMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);


    public void Subject(ActionEvent event) {

        mainLabel.setText("Subject Clicked");
    }

    public void WorkingDH(ActionEvent event) throws IOException {

        App.setRoot("DaysHours");
    }


    public void lecturers(ActionEvent event) throws IOException {

        App.setRoot("Lecturer_Managment");
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

    public void studentGoups(ActionEvent event) {

    }

    public void location(ActionEvent event ){
        mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) throws IOException {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getSession_ID();
        getGroup_ID();
        getSubGroup_ID();
        getLecturer();
        Sp_S_HR.setValueFactory(svfStHrs);
        Sp_S_Min.setValueFactory(svfStMin);
        Sp_E_HR.setValueFactory(svfEndHrs);
        Sp_E_Min.setValueFactory(svfEndMin);






    }
    public void Bt_Submit() {

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        {
            try {
                String sql = "INSERT INTO NotAvailableTimes  VALUES (null,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setString(1, String.valueOf(CBH_lecturer.getValue()));
                ps.setString(2, String.valueOf(CBH_group_id.getValue()));
                ps.setString(3, String.valueOf(CBH_sub_group_id.getValue()));
                ps.setString(4, String.valueOf(CHB_Session_id.getValue()));
                ps.setString(5, String.valueOf(ID_SetDate.getValue()));
                ps.setString(6, String.valueOf(Sp_S_HR.getValue()+" : "+Sp_S_Min.getValue()+ "_To_"+Sp_E_HR.getValue()+":"+Sp_E_Min.getValue()));


                ps.execute();
                System.out.println("Data added successfully !!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void getLecturer() {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM sessions");
            while (rs.next()) {

                CBH_lecturer.getItems().add(rs.getString("session_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getSession_ID() {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM sessions");
            while (rs.next()) {

                CHB_Session_id.getItems().add(rs.getString("session_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getGroup_ID() {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM sessions");
            while (rs.next()) {

                CBH_group_id.getItems().add(rs.getString("group_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getSubGroup_ID() {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM sessions");
            while (rs.next()) {

                CBH_sub_group_id.getItems().add(rs.getString("room_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------
}

