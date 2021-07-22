package org.TeamCipher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddSessionController implements Initializable{

    public static String lecurerData,lecturerData2=null;
    @FXML
    private ChoiceBox choBox_seleLec1,choBox_seleLec2;
    @FXML
    private Label mainLabel;




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

    //------------------------------------------------------------------------------------------

    public void next(ActionEvent event) throws IOException {
        String check = validate();
        if (check.equals("true")) {

            lecurerData = (String) choBox_seleLec1.getValue();
            lecturerData2 = (String) choBox_seleLec2.getValue();
            if(lecurerData.equals(lecturerData2)){
                lecturerData2=null;
            }
            App.setRoot("AddSession2");

        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText(check);
            alert.showAndWait();
            mainLabel.setText(validate());
        }
    }
    public void clear(ActionEvent event) {
        choBox_seleLec1.setValue(null);
        choBox_seleLec2.setValue(null);
    }

    public void getLecture1() throws SQLException {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Lecturer");
            while (rs.next()) {
                choBox_seleLec1.getItems().add(rs.getString("name"));
            }
           // txtField_lecturers.setText((String) choBox_seleLec1.getValue());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getLecture2() {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Lecturer");
            while (rs.next()) {
                choBox_seleLec2.getItems().add(rs.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String validate() {
        if (choBox_seleLec1.getValue() == null){
            return "Insert Lecurer";
        }
        return  "true";
    }

    private String conform() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("You are going to delete this entry");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("OKAY");
            return "true";
        } else {
            System.out.println("CANCEL");
            return "false";
        }
    }

    public void initialize(URL url, ResourceBundle resources) {
        try {
            getLecture1();
            getLecture2();
        } catch (SQLException throwables) {
            System.out.println("error");
            throwables.printStackTrace();
        }
    }
}
