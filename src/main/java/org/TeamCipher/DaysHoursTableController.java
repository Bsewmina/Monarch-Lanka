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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DaysHoursTableController implements Initializable {

    ObservableList<Object> observableList = FXCollections.observableArrayList();
    //------------------------------------------------------------------------------------------

    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;

    @FXML
    private TableView<Object> WH_Table;
    @FXML
    private TableColumn<DaysHours,Integer> TC_ID;
    @FXML
    private TableColumn<DaysHours,String> TC_Num_of_Days;
    @FXML
    private TableColumn<DaysHours,String> TC_Monday;
    @FXML
    private TableColumn<DaysHours,String> TC_Mon_Time;
    @FXML
    private TableColumn<DaysHours,String> TC_Tuesday;
    @FXML
    private TableColumn<DaysHours,String> TC_Tue_Time;
    @FXML
    private TableColumn<DaysHours,String> TC_Wednesday;
    @FXML
    private TableColumn<DaysHours,String> TC_Wed_Time;
    @FXML
    private TableColumn<DaysHours,String> TC_Thursday;
    @FXML
    private TableColumn<DaysHours,String> TC_Thu_Time;
    @FXML
    private TableColumn<DaysHours,String> TC_Friday;
    @FXML
    private TableColumn<DaysHours,String> TC_Fri_Time;
    @FXML
    private TableColumn<DaysHours,String> TC_Saturday;
    @FXML
    private TableColumn<DaysHours,String> TC_Sat_Time;
    @FXML
    private TableColumn<DaysHours,String> TC_Sunday;
    @FXML
    private TableColumn<DaysHours,String> TC_Sun_Time;



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


        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM workingdays");
            System.out.println("In the view Query");
            while (rs.next()) {
                observableList.add(new DaysHours(
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
        TC_Monday.setCellValueFactory(new PropertyValueFactory<>("monday"));
        TC_Mon_Time.setCellValueFactory(new PropertyValueFactory<>("mon_t"));
        TC_Tuesday.setCellValueFactory(new PropertyValueFactory<>("tuesday"));
        TC_Tue_Time.setCellValueFactory(new PropertyValueFactory<>("tue_t"));
        TC_Wednesday.setCellValueFactory(new PropertyValueFactory<>("wednesday"));
        TC_Wed_Time.setCellValueFactory(new PropertyValueFactory<>("wed_t"));
        TC_Thursday.setCellValueFactory(new PropertyValueFactory<>("thursday"));
        TC_Thu_Time.setCellValueFactory(new PropertyValueFactory<>("thu_t"));
        TC_Friday.setCellValueFactory(new PropertyValueFactory<>("friday"));
        TC_Fri_Time.setCellValueFactory(new PropertyValueFactory<>("fri_t"));
        TC_Saturday.setCellValueFactory(new PropertyValueFactory<>("saturday"));
        TC_Sat_Time.setCellValueFactory(new PropertyValueFactory<>("sat_t"));
        TC_Sunday.setCellValueFactory(new PropertyValueFactory<>("sunday"));
        TC_Sun_Time.setCellValueFactory(new PropertyValueFactory<>("sun_t"));
        WH_Table.setItems(observableList);

    }
    //------------------------------------------------------------------------------------------
}

