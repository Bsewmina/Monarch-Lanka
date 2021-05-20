package org.TeamCipher;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;


public class PrimaryController {

    //------------------------------------------------------------------------------------------

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

    public void session(ActionEvent event) throws IOException {

        mainLabel.setText("session Clicked");
        App.setRoot("NewSession");
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
}

