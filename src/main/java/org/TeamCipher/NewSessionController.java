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
import java.util.ResourceBundle;

public class NewSessionController implements Initializable {
    //------------------------------------------------------------------------------------------

    final int INITIAL_VALUE = 0;
    final int min_IValue = 0;

    @FXML
    void Btn_s_view(ActionEvent event) throws IOException {

        App.setRoot("SessionsTable");
    }

    @FXML
    private DatePicker s_date;
    @FXML
    private Spinner<Integer> Sp_Session_Start_THR;
    SpinnerValueFactory<Integer> svfStHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> Sp_Session_Start_TMin;

    SpinnerValueFactory<Integer> svfStMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);
    @FXML
    private Spinner<Integer> Sp_Session_End_THR;
    SpinnerValueFactory<Integer> svfEndHrs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, INITIAL_VALUE);
    @FXML
    private Spinner<Integer> Sp_Session_End_TMin;
    SpinnerValueFactory<Integer> svfEndMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, min_IValue);

    @FXML
    private TextField Tb_S_id;
    @FXML
    private Button Bt_session_Submit;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

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
        Sp_Session_Start_THR.setValueFactory(svfStHrs);
        Sp_Session_Start_TMin.setValueFactory(svfStMin);
        Sp_Session_End_THR.setValueFactory(svfEndHrs);
        Sp_Session_End_TMin.setValueFactory(svfEndMin);
    }
    public void Bt_session_Submit() {

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        {
            try {
                String sql = "INSERT INTO sessions  VALUES (null,?,?,?,?,?)";
                ps = con.prepareStatement(sql);

                ps.setString(1, Tb_S_id.getText());
                ps.setString(2, "null");
                ps.setString(3, "null");
                ps.setString(4, String.valueOf(s_date.getValue()));
                ps.setString(5, String.valueOf(Sp_Session_Start_THR.getValue()+"Hrs "+Sp_Session_Start_TMin.getValue()+"Min"+ "_To_"+Sp_Session_End_THR.getValue()+"Hrs"+Sp_Session_End_TMin.getValue()+"Min"));


                ps.execute();
                System.out.println("Data added successfully !!!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
