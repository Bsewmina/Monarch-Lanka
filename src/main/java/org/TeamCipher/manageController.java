package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class manageController implements Initializable {

    @FXML
    private Label mainLabel;

    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

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

    public void statistic(ActionEvent event) throws IOException {

        mainLabel.setText("statistics Clicked");
        App.setRoot("statistic");
    }

    public void studentGoups(ActionEvent event) throws IOException {
        App.setRoot("student_groups_menu");
    }

    public void location(ActionEvent event ) throws IOException {
        App.setRoot("location");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }
    //------------------------------------------------------------------------------------------

    public TableView<Location> tableView;
    public TableColumn<Location,Integer> id;
    public TableColumn<Location,String> building;
    public TableColumn<Location,String> room;
    public TableColumn<Location,String> type;
    public TableColumn<Location,Integer> cap;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        building.setCellValueFactory(new PropertyValueFactory<>("Building"));
        room.setCellValueFactory(new PropertyValueFactory<>("Rname"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        cap.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        tableView.setItems(observableList);

    }

    ObservableList<Location> observableList = FXCollections.observableArrayList(

      new Location(1,"OLD Building","IT-002","Lecture Hall",180),
      new Location(2,"OLD Building","IT-002","Lecture Hall",180)

    );
}
