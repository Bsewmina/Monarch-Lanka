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


public class ManageNotAvailableTimesController implements Initializable {
    ObservableList<NotAvailableTimesModel> observableList = FXCollections.observableArrayList();

    int tempId;
    //------------------------------------------------------------------------------------------
    @FXML
    private TableView<NotAvailableTimesModel> NotAv_Table;
    @FXML
    private TableColumn<NotAvailableTimesModel,String> TC_Date;
    @FXML
    private TableColumn<NotAvailableTimesModel,String> TC_Time;
    @FXML
    private TableColumn<NotAvailableTimesModel,String> TC_Lecturer;
    @FXML
    private TableColumn<NotAvailableTimesModel,String> TC_group_id;
    @FXML
    private TableColumn<NotAvailableTimesModel,String> TC_sub_group_id;
    @FXML
    private TableColumn<NotAvailableTimesModel,String> TC_session_id;
    @FXML
    private TableColumn<NotAvailableTimesModel,Integer> TC_id;

    @FXML
    void Btn_view(ActionEvent event) throws IOException {
    //App.setRoot("ManageNotAvailableTimes");
    }
    @FXML
    void BT_Back(ActionEvent event) throws IOException {
    App.setRoot("NotAvailableTimes");
    }

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    final int INITIAL_VALUE = 0;
    final int min_IValue = 0;
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


    //--------------------------UPDATE-----------------------

    @FXML
    private TextField TF_date;
    @FXML
    private TextField TF_time;
    @FXML
    private TextField TF_Lecturer;

    @FXML
    private TextField TF_grp_id;

    @FXML
    private TextField TF_sub_grp_id;

    @FXML
    private TextField TF_session_id;

    @FXML
    private TextField TF_id;


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
    private void btn_update() throws IOException, SQLException {

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE NotAvailableTimes SET lecturer=?, group_=?, sub_group=?, session_id=?, date_=?, time_=? WHERE id=?";
            ps = con.prepareStatement(sql);


            ps.setString(1, TF_Lecturer.getText());
            ps.setString(2, TF_grp_id.getText());
            ps.setString(3, TF_sub_grp_id.getText());
            ps.setString(4, TF_session_id.getText());
            ps.setString(5, TF_date.getText());
            ps.setString(6, TF_time.getText());
            ps.setInt(7,Integer.parseInt(TF_id.getText()));

            ps.execute();
            System.out.println("Data Updated Successfully !!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
con.close();
        alertU(null);
        //refresh the page
        App.setRoot("ManageNotAvailableTimes");
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

    public void Remove(int id) throws SQLException {
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM NotAvailableTimes WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            System.out.println("Data Deleted Successfully !!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
con.close();
    }
    @FXML
    private void onClicked() {
        if (NotAv_Table.getSelectionModel().getSelectedItems() != null) {
            NotAvailableTimesModel location = NotAv_Table.getSelectionModel().getSelectedItem();
            tempId = location.getId();

        }


        if (NotAv_Table.getSelectionModel().getSelectedItems() != null) {
            NotAvailableTimesModel location = NotAv_Table.getSelectionModel().getSelectedItem();
            TF_date.setText(String.valueOf(location.getDate()));
            TF_time.setText(String.valueOf(location.getTime()));
            TF_Lecturer.setText(String.valueOf(location.getLecturer()));
            TF_grp_id.setText(String.valueOf(location.getGroup_id()));
            TF_sub_grp_id.setText(String.valueOf(location.getSub_group_id()));
            TF_session_id.setText(String.valueOf(location.getSession_id()));
            TF_id.setText(String.valueOf(location.getId()));

        }


    }
    public void Remove(ActionEvent event) throws IOException, SQLException {
        Remove(tempId);
        //------------------------------POST DELETE ACTIONS
        //calling the alert
        alertD(null);
        //refresh the page
        App.setRoot("ManageNotAvailableTimes");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM NotAvailableTimes");
            System.out.println("In the view Query");
            while (rs.next()) {
                observableList.add(new NotAvailableTimesModel(rs.getInt("id"),
                        rs.getString("lecturer"),
                        rs.getString("group_"),
                        rs.getString("sub_group"),
                        rs.getString("session_id"),
                        rs.getString("date_"),
                        rs.getString("time_")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        TC_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TC_session_id.setCellValueFactory(new PropertyValueFactory<>("session_id"));
        TC_group_id.setCellValueFactory(new PropertyValueFactory<>("group_id"));
        TC_sub_group_id.setCellValueFactory(new PropertyValueFactory<>("sub_group_id"));
        TC_Lecturer.setCellValueFactory(new PropertyValueFactory<>("lecturer"));
        TC_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        TC_Time.setCellValueFactory(new PropertyValueFactory<>("time"));
        NotAv_Table.setItems(observableList);

    }

    //------------------------------------------------------------------------------------------
}

