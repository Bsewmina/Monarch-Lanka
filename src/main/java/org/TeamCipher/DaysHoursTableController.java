package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
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

public class DaysHoursTableController implements Initializable {

    int tempId;
    ObservableList<Object> observableList = FXCollections.observableArrayList();
    //------------------------------------------------------------------------------------------

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    @FXML
    void Back_to_WH(ActionEvent event) throws IOException {
    App.setRoot("DaysHours");
    }

    @FXML
    private TableView<Object> WH_Table;
    @FXML
    private TableColumn<DaysHoursModel,Integer> TC_ID;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Num_of_Days;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Monday;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Mon_Time;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Tuesday;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Tue_Time;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Wednesday;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Wed_Time;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Thursday;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Thu_Time;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Friday;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Fri_Time;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Saturday;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Sat_Time;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Sunday;
    @FXML
    private TableColumn<DaysHoursModel,String> TC_Sun_Time;



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

//-------------------------UPDATE-----------------------


    @FXML
    private TextField TF_Id;

    @FXML
    private TextField TF_NOD;

    @FXML
    private TextField TF_Mon;

    @FXML
    private TextField TF_Tue;

    @FXML
    private TextField TF_Wed;

    @FXML
    private TextField TF_Thu;

    @FXML
    private TextField TF_Fri;

    @FXML
    private TextField TF_Sat;

    @FXML
    private TextField TF_Sun;

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
    private void btn_update_WH() throws IOException {


        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE workingdays SET num_of_days=?, mon_t=?, tue_t=?, wed_t=?, thu_t=?, fri_t=?, sat_t=?, sun_t=? WHERE WH_id=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, TF_NOD.getText());
            ps.setString(2, TF_Mon.getText());
            ps.setString(3, TF_Tue.getText());
            ps.setString(4, TF_Wed.getText());
            ps.setString(5, TF_Thu.getText());
            ps.setString(6, TF_Fri.getText());
            ps.setString(7, TF_Sat.getText());
            ps.setString(8, TF_Sun.getText());
            ps.setInt(9, Integer.parseInt(TF_Id.getText()));
            ps.execute();
            System.out.println("Data Updated Successfully !!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        alertU(null);
        //refresh the page
        App.setRoot("DaysHoursTable");
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

    public void Removedays(int id) {
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM workingdays WHERE WH_id = ?";
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
        if (WH_Table.getSelectionModel().getSelectedItems() != null) {
            DaysHoursModel location = (DaysHoursModel) WH_Table.getSelectionModel().getSelectedItem();
            tempId = location.getWH_id();
            TF_NOD.setText(String.valueOf(location.getNum_of_days()));
            TF_Mon.setText(String.valueOf(location.getMon_t()));
            TF_Tue.setText(String.valueOf(location.getTue_t()));
            TF_Wed.setText(String.valueOf(location.getWed_t()));
            TF_Thu.setText(String.valueOf(location.getThu_t()));
            TF_Fri.setText(String.valueOf(location.getFri_t()));
            TF_Sat.setText(String.valueOf(location.getSat_t()));
            TF_Sun.setText(String.valueOf(location.getSun_t()));
            TF_Id.setText(String.valueOf(location.getWH_id()));
        }
    }
    public void Removedays(ActionEvent event) throws IOException {
        Removedays(tempId);
        //------------------------------POST DELETE ACTIONS
        //calling the alert
        alertD(null);
        //refresh the page
        App.setRoot("DaysHoursTable");
    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM workingdays");
            System.out.println("In the view Query");
            while (rs.next()) {
                observableList.add(new DaysHoursModel(
                        rs.getInt("WH_id"),
                        rs.getString("num_of_days"),
                        rs.getString("monday"),
                        rs.getString("mon_t"), 
                        rs.getString("tuesday"),
                        rs.getString("tue_t"), 
                        rs.getString("wednesday"),
                        rs.getString("wed_t"), 
                        rs.getString("thursday"),
                        rs.getString("thu_t"),
                        rs.getString("friday"),
                        rs.getString("fri_t"),
                        rs.getString("saturday"),
                        rs.getString("sat_t"),
                        rs.getString("sunday"),
                        rs.getString("sun_t")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        TC_ID.setCellValueFactory(new PropertyValueFactory<>("WH_id"));
        TC_Num_of_Days.setCellValueFactory(new PropertyValueFactory<>("num_of_days"));
       // TC_Monday.setCellValueFactory(new PropertyValueFactory<>("monday"));
        TC_Mon_Time.setCellValueFactory(new PropertyValueFactory<>("mon_t"));
       // TC_Tuesday.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        TC_Tue_Time.setCellValueFactory(new PropertyValueFactory<>("tue_t"));
       // TC_Wednesday.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        TC_Wed_Time.setCellValueFactory(new PropertyValueFactory<>("wed_t"));
       // TC_Thursday.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        TC_Thu_Time.setCellValueFactory(new PropertyValueFactory<>("thu_t"));
       // TC_Friday.setCellValueFactory(new PropertyValueFactory<>("friday"));
        TC_Fri_Time.setCellValueFactory(new PropertyValueFactory<>("fri_t"));
       // TC_Saturday.setCellValueFactory(new PropertyValueFactory<>("saturday"));
        TC_Sat_Time.setCellValueFactory(new PropertyValueFactory<>("sat_t"));
       // TC_Sunday.setCellValueFactory(new PropertyValueFactory<>("sunday"));
        TC_Sun_Time.setCellValueFactory(new PropertyValueFactory<>("sun_t"));
        WH_Table.setItems(observableList);

    }
    //------------------------------------------------------------------------------------------
}

