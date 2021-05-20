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

public class SessionsTableController implements Initializable {

    ObservableList<NewSessionModel> observableList = FXCollections.observableArrayList();
    //------------------------------------------------------------------------------------------

    int tempId;

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;

    @FXML
    private TableView<NewSessionModel> Sessions_Table;
    @FXML
    private TableColumn<NewSessionModel,String> TC_session_id;
    @FXML
    private TableColumn<NewSessionModel,String> TC_group_id;
    @FXML
    private TableColumn<NewSessionModel,String> TC_room_id;
    @FXML
    private TableColumn<NewSessionModel,String> TC_s_date;
    @FXML
    private TableColumn<NewSessionModel,String> TC_s_time;
    @FXML
    private TableColumn<NewSessionModel,Integer> sid;


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


    //------------------------------------------------------------- DB Delete

    //Alert
    @FXML
    private void alertD(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText("You are going to delete this entry");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("OKAY");
        } else {
            System.out.println("CANCEL");
        }
    }

    public void RemoveSession(int id) {
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM sessions WHERE sid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            System.out.println("Data Deleted Successfully !!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void onClicked() {
        if (Sessions_Table.getSelectionModel().getSelectedItems() != null) {
            NewSessionModel location = Sessions_Table.getSelectionModel().getSelectedItem();
            tempId = location.getSid();
        }
    }
    public void RemoveSession(ActionEvent event) throws IOException {
        RemoveSession(tempId);
        //------------------------------POST DELETE ACTIONS
        //calling the alert
        alertD(null);
        //refresh the page
        App.setRoot("SessionsTable");
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM sessions");
            System.out.println("In the view Query");
            while (rs.next()) {
                observableList.add(new NewSessionModel(rs.getInt("sid"),
                        rs.getString("session_id"),
                        rs.getString("group_id"),
                        rs.getString("room_id"),
                        rs.getString("s_date"),
                        rs.getString("s_time")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TC_session_id.setCellValueFactory(new PropertyValueFactory<>("session_id"));
        TC_group_id.setCellValueFactory(new PropertyValueFactory<>("group_id"));
        TC_room_id.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        TC_s_date.setCellValueFactory(new PropertyValueFactory<>("s_date"));
        TC_s_time.setCellValueFactory(new PropertyValueFactory<>("s_time"));
        //sid.setCellValueFactory(new PropertyValueFactory<>("sid"));
        Sessions_Table.setItems(observableList);

    }






    //------------------------------------------------------------------------------------------
}

