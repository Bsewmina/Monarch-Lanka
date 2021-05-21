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

public class ManageNotAvailableRoomsController implements Initializable {

    ObservableList<NotAvailableRoomsModel> observableList = FXCollections.observableArrayList();
    //------------------------------------------------------------------------------------------

    int tempId;

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;

    @FXML
    private TableView<NotAvailableRoomsModel> Sessions_Table;
    @FXML
    private TableColumn<NotAvailableRoomsModel,String> TC_session_id;
    @FXML
    private TableColumn<NotAvailableRoomsModel,String> TC_group_id;
    @FXML
    private TableColumn<NotAvailableRoomsModel,String> TC_room_id;
    @FXML
    private TableColumn<NotAvailableRoomsModel,String> TC_s_date;
    @FXML
    private TableColumn<NotAvailableRoomsModel,String> TC_s_time;
    @FXML
    private TableColumn<NotAvailableRoomsModel,Integer> sid;


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

    //--------------------------UPDATE-----------------------



    @FXML
    private TextField TF_sesid;

    @FXML
    private TextField TF_st;

    @FXML
    private TextField TF_sd;

    @FXML
    private TextField TF_rmid;

    @FXML
    private TextField TF_grpid;

    @FXML
    private void alertU(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Confirmation");
        alert.setHeaderText("You are going to update this entry");
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("OKAY");
        } else {
            System.out.println("CANCEL");
        }
    }

    @FXML
    private void updateSession() throws IOException {


        String TF_sesid_text = TF_sesid.getText();
        String TF_grpid_text=TF_grpid.getText();
        String TF_rmid_text = TF_rmid.getText();
        String TF_sd_text = TF_sd.getText();
        String TF_st_text = TF_st.getText();


        updateSession(tempId,TF_sesid_text,TF_grpid_text,TF_rmid_text,TF_sd_text,TF_st_text);
        alertU(null);
        //refresh the page
        App.setRoot("SessionsTable");
    }


    public static void updateSession(int id,String TF_sesid_text, String TF_grpid_text,String TF_rmid_text, String TF_sd_text,String TF_st_text) {
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE sessions SET session_id=?, group_id=?, room_id=?, s_date=?, s_time=? WHERE sid=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, TF_sesid_text);
            ps.setString(2, TF_grpid_text);
            ps.setString(3, TF_rmid_text);
            ps.setString(4, TF_sd_text);
            ps.setString(5, TF_st_text);
            ps.setInt(6, id);
            ps.execute();
            System.out.println("Data Updated Successfully !!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            NotAvailableRoomsModel location = Sessions_Table.getSelectionModel().getSelectedItem();
            tempId = location.getSid();

        }


        if (Sessions_Table.getSelectionModel().getSelectedItems() != null) {
           NotAvailableRoomsModel location = Sessions_Table.getSelectionModel().getSelectedItem();
            TF_sesid.setText(String.valueOf(location.getSession_id()));
            TF_grpid.setText(String.valueOf(location.getGroup_id()));
            TF_rmid.setText(String.valueOf(location.getRoom_id()));
            TF_sd.setText(String.valueOf(location.getS_date()));
            TF_st.setText(String.valueOf(location.getS_time()));

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
                observableList.add(new NotAvailableRoomsModel(rs.getInt("sid"),
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

