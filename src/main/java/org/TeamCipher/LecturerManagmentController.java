package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javax.xml.stream.Location;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LecturerManagmentController implements Initializable {

    String rank;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    @FXML
    private TableView<ModelLecturerTable> tableView;
    @FXML
    private TableColumn<ModelLecturerTable, Integer> id;
    @FXML
    private TableColumn<ModelLecturerTable,String> name;
    @FXML
    private TableColumn<ModelLecturerTable, String> empID;
    @FXML
    private TableColumn<ModelLecturerTable,String> faculty;
    @FXML
    private TableColumn<ModelLecturerTable, Integer> level;

    ObservableList<ModelLecturerTable> objList = FXCollections.observableArrayList();

    //----------------------------------------

    @FXML
    private TextField txtField_id, txtField_name, texField_rank;

    @FXML
    private ChoiceBox<?> choiceBox_faculty, choiceBox_department, choiceBox_center, choiceBox_building, choiceBox_level;

    @FXML
    private Button btn_delete, btn_clear, btn_update;

////////////////////////////////////////////////////////////////////


public void Subject(ActionEvent event) throws IOException {

    App.setRoot("Subject_Managment");
}

    public void WorkingDH(ActionEvent event) {

        mainLabel.setText("WorkingDH Clicked");
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

    public void studentGoups(ActionEvent event) throws IOException {

    }

    public void location(ActionEvent event ){

    mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }

    public void addLecturer(ActionEvent event) throws IOException {
        App.setRoot("new_lecturer");
    }
    //------------------------------------------------------------------------------------------

    @FXML
    void Clear(ActionEvent event) {
        txtField_id.setText(null);
        txtField_name.setText(null);
        texField_rank.setText(null);

    }

    @FXML
    void Delete(ActionEvent event) {
        String temp = txtField_id.getText().toString();
        if (temp.equals("")) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Select Row");
            alert.showAndWait();

        } else {
            deleteData(Integer.parseInt(txtField_id.getText().toString()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleted successfully");
            alert.showAndWait();
        }
    }


    @FXML
    void Update(ActionEvent event) {

        String check = validate();


        //validate fields
        if (!check.equals("true")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(check);
            alert.showAndWait();
        }
        else {
            String id, l;
            id = txtField_id.getText().toString();
            l = (String) choiceBox_level.getValue();
            rank = l + "." + id;

            Updatedetails(Integer.parseInt(txtField_id.getText()), txtField_name.getText(), choiceBox_faculty.getValue().toString(), choiceBox_department.getValue().toString(), choiceBox_center.getValue().toString(), choiceBox_building.getValue().toString(), Integer.parseInt(choiceBox_level.getValue().toString()), rank);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Updated successfully");
            alert.showAndWait();
        }
    }

    public void deleteData(int id){

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Lecturer WHERE empId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data Deleted Successfully !!!!!");

            ps.close();
            con.close();
            App.setRoot("Lecturer_Managment");
            mainLabel.setText("Data Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Updatedetails(int id,String name,String faculty,String dept,String center,String building,int level,String rank) {

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Lecturer SET empId=?, name=?, faculty=?, department=?, center=?, building=?, level=?, rank=? WHERE empId = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,faculty);
            ps.setString(4,dept);
            ps.setString(5,center);
            ps.setString(6,building);
            ps.setInt(7,level);
            ps.setString(8,rank);
            ps.setInt(9,id);
            ps.execute();
            System.out.println("Data Updated Successfully !!!!!!");

            ps.close();
            con.close();
            App.setRoot("Lecturer_Managment");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectRow() {
        if (tableView.getSelectionModel().getSelectedItems() != null) {

            ModelLecturerTable lecturerTable = tableView.getSelectionModel().getSelectedItem();
            txtField_id.setText(String.valueOf(lecturerTable.getId()));
            txtField_name.setText(lecturerTable.getName());
            //choiceBox_faculty.setValue(lecturerTable.getFaculty());
        }
    }

    public String validate() {
        if(txtField_id.getText().equals("")){
            return "Select a Table row";
        }
        else if(txtField_name.getText().equals("")){
            return  "Insert Employee ID";
        }
        else if(choiceBox_faculty.getValue() == null){
            return  "Select Faculty";
        }
        else if(choiceBox_department.getValue() == null){
            return  "Select Department";
        }
        else if(choiceBox_center.getValue() == null){
            return  "Select Center";
        }
        else if(choiceBox_building.getValue() == null){
            return  "Select Building";
        }
        else if(choiceBox_level.getValue() == null){
            return  "Select Lever";
        }
        else
            return  "true";
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        try{
            Connection con = SQliteConnection.DBconnect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Lecturer");

            while (rs.next()){
                objList.add(new ModelLecturerTable(rs.getInt("empId"),rs.getString("name"),rs.getString("faculty"),rs.getInt("level")));
            }

        }catch (Exception e){
            System.err.println("---- Error while retrieving data" + e);
        }

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        empID.setCellValueFactory(new PropertyValueFactory<>("id"));
        faculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        level.setCellValueFactory(new PropertyValueFactory<>("level"));

        tableView.setItems(objList);
    }
}
