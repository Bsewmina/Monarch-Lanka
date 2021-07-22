package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;


public class manageController implements Initializable {

    @FXML
    private Label mainLabel;

    @FXML
    private TextField lid,bname,froom,frtype,fcap;



    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    ObservableList<Location> observableList = FXCollections.observableArrayList();


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

    public TableView<Location> tableView;
    public TableColumn<Location, Integer> id;
    public TableColumn<Location, String> building;
    public TableColumn<Location, String> room;
    public TableColumn<Location, String> type;
    public TableColumn<Location, Integer> cap;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Location");
            System.out.println("In the view Quiry");

            while (rs.next()) {
                observableList.add(new Location(rs.getInt("id"), rs.getString("buildingName"), rs.getString("RoomName"), rs.getString("RoomType"), rs.getInt("Capacity")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        building.setCellValueFactory(new PropertyValueFactory<>("Building"));
        room.setCellValueFactory(new PropertyValueFactory<>("Rname"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        cap.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        tableView.setItems(observableList);


    }

    //ObservableList<Location> observableList = FXCollections.observableArrayList(

    // new Location(1,"OLD Building","IT-002","Lecture Hall",180),
    // new Location(2,"OLD Building","IT-002","Lecture Hall",180)

    // );

    public void DeleteBtn(ActionEvent event) {

        if(lid.getText().isEmpty()){

            alertError(null);

        }else {


            String ids = lid.getText().toString();

            int sendID = Integer.parseInt(ids);


            alertCom(null,sendID);
        }


    }

    public void deleting(int id){



            Connection con = SQliteConnection.DBconnect();
            PreparedStatement ps = null;
            try {
                String sql = "DELETE FROM Location WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
                System.out.println("Data Deleted Successfully !!!!!");

                alert("Deleted Successfully",null);

            } catch (Exception e) {
                e.printStackTrace();
            }
            clearField(null);
            Refresh();

    }



    public void UpdateTable(ActionEvent event){

        String idnumber =  lid.getText();

        if(idnumber.isEmpty()) {

            alertError(null);
        }else{

            int id = Integer.parseInt(lid.getText().toString());
            String building = bname.getText();
            String roomName = froom.getText();
            String romType = frtype.getText();
            String capt  = fcap.getText();

            Connection con = SQliteConnection.DBconnect();
            PreparedStatement ps = null;
            try {
                String sql = "UPDATE Location SET buildingName=?, RoomName=?, Capacity=?, RoomType=? WHERE ID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1,building);
                ps.setString(2,roomName);
                ps.setString(3,capt);
                ps.setString(4,romType);
                ps.setInt(5,id);
                ps.execute();
                System.out.println("Data Deleted Successfully !!!!!");

                alert("Updated Successfully",null);

            } catch (Exception e) {
                e.printStackTrace();
            }





        }

        clearField(null);
        Refresh();



    }





    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) {


        if(mouseEvent.getClickCount() >1){

            onClicked();

        }

    }

    private void onClicked() {

        if(tableView.getSelectionModel().getSelectedItems() != null){


            Location location = tableView.getSelectionModel().getSelectedItem();

            lid.setText(String.valueOf(location.getId()));
            bname.setText(location.getBuilding());
            froom.setText(location.getRname());
            frtype.setText(location.getType());
            fcap.setText(String.valueOf(location.getCapacity()));



        }

    }


    public void Refresh(){

        observableList.clear();

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Location");
            System.out.println("In the view Quiry");

            while (rs.next()) {
                observableList.add(new Location(rs.getInt("id"), rs.getString("buildingName"), rs.getString("RoomName"), rs.getString("RoomType"), rs.getInt("Capacity")));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        building.setCellValueFactory(new PropertyValueFactory<>("Building"));
        room.setCellValueFactory(new PropertyValueFactory<>("Rname"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        cap.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        tableView.setItems(observableList);


    }


    public void clearField(ActionEvent event){

        lid.clear();
        bname.clear();
        froom.clear();
        frtype.clear();
        fcap.clear();


    }

    @FXML
    private void alertError(ActionEvent event) {

        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setTitle("ERROR");
        al.setContentText("Please Select the Row");
        al.setHeaderText(null);
        al.showAndWait();


    }

    @FXML
    private void alertCom(ActionEvent event,int sendID){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Delete Data");
        alert.setHeaderText("Data will Delete Permanently");
        alert.setContentText("Do you want to Delete ?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){

            deleting(sendID);

        }
        else{
            System.out.println("Cancel");
        }

    }


    @FXML
    private void alert(String text,ActionEvent event){

        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Successful");
        al.setContentText(text);
        al.setHeaderText(null);
        al.showAndWait();


    }


}