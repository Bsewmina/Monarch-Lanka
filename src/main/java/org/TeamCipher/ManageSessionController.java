package org.TeamCipher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManageSessionController implements Initializable {
    public ChoiceBox choBox_seleLec1,choBox_subject,choBox_seleLec2,choBox_searchType,choBox_type,choBox_group,choBox_tag;
    public TextField txt_sessionId;
    public Spinner spinner_noOfStd,spinner_duration;
    //------------------------------------------------------------------------------------------
    Integer tempID;
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;

    @FXML
    private TableView<ModelSession> tableView;
    @FXML
    private TableColumn<ModelSession, Integer> id;
    @FXML
    private TableColumn<ModelSession,String> lec1;
    @FXML
    private TableColumn<ModelSession, String> lec2;
    @FXML
    private TableColumn<ModelSession,String> session;
    @FXML
    private TableColumn<ModelSession, String> subject;
    @FXML
    private TableColumn<ModelSession, String> group;
    @FXML
    private TableColumn<ModelSession, String> tag;

    @FXML
    private TableColumn<ModelSession, Integer> level;
    ObservableList<ModelSession> objList = FXCollections.observableArrayList();

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

    public void session(ActionEvent event) throws IOException {

        App.setRoot("ManageSession");
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

    public void search(ActionEvent event) {

    }

    public void update(ActionEvent event) {
        String check = validate();
        if (!check.equals("true")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(check);
            alert.showAndWait();

        } else {
            UpdateSession(Integer.parseInt(txt_sessionId.getText()), choBox_seleLec1.getValue().toString(),choBox_seleLec2.getValue().toString(),choBox_tag.getValue().toString(),choBox_group.getValue().toString(),choBox_subject.getValue().toString(),
                    choBox_type.getValue().toString(),Integer.parseInt( spinner_noOfStd.getValue().toString()),Integer.parseInt(spinner_duration.getValue().toString()));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Updated successfully");
            alert.showAndWait();
        }

    }

    public void delete(ActionEvent event) {
        String temp = txt_sessionId.getText().toString();
        if (temp.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Select Row");
            alert.showAndWait();

        } else {
            deleteData(Integer.parseInt(temp));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleted successfully");
            alert.showAndWait();
        }

    }

    public void clear(ActionEvent event) {
        choBox_seleLec1.setValue(null);
        choBox_seleLec2.setValue(null);
        choBox_tag.setValue(null);
        choBox_group.setValue(null);
        choBox_type.setValue(null);
        spinner_noOfStd.getValueFactory().setValue(0);
        spinner_duration.getValueFactory().setValue(0);
    }


    public void deleteData(int id){

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Session WHERE s_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data Deleted Successfully !!!!!");

            ps.close();
            con.close();
            App.setRoot("ManageSession");
            mainLabel.setText("Data Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void getSession() {
        if (tableView.getSelectionModel().getSelectedItems() != null) {

            ModelSession sessionTable = tableView.getSelectionModel().getSelectedItem();
            txt_sessionId.setText(String.valueOf(sessionTable.getId()));
            choBox_seleLec1.setValue(sessionTable.getLec1());
            choBox_seleLec2.setValue(sessionTable.getLec2());
            choBox_tag.setValue(sessionTable.getTag());
            choBox_group.setValue(sessionTable.getGroup());
            choBox_searchType.setValue(sessionTable.getSsType());

        }
    }

    public static void UpdateSession(int id,String lecturer1,String lecturer2,String tag,String group,String subject,String sType,int student,int duration) {

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Session SET s_lecturer1=?, s_lecturer2=?, s_tag=?, s_groudId=?, s_subject=?, s_sessionType=?, s_noOfStudent=?, s_duration=? WHERE s_id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,lecturer1);
            ps.setString(2,lecturer2);
            ps.setString(3,tag);
            ps.setString(4,group);
            ps.setString(5,subject);;
            ps.setString(6,sType);
            ps.setInt(7,student);
            ps.setInt(8,duration);
            ps.setInt(9,id);
            ps.execute();
            System.out.println("Data updated successfully !!!!!");

            ps.close();
            con.close();
            App.setRoot("ManageSession");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while updating data !!!!!");
        }
    }

    public String validate() {
        if (choBox_tag.getValue() == null){
            return "Select Tag";
        }
        else if (choBox_seleLec1.getValue() == null){
            return "Select Lecturer 1";
        }
        else if (choBox_group.getValue() == null){
            return "Select Group";
        }
        else if (choBox_subject.getValue() == null){
            return "Select Subject";
        }
        else if (choBox_type.getValue() == null){
            return "Select Session Type";
        }
        return  "true";
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

    public void getTag() throws SQLException {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM tags");
            while (rs.next()) {
                choBox_tag.getItems().add(rs.getString("related_tag"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getGroup() throws SQLException {


        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM student_groups");
            while (rs.next()) {
                choBox_group.getItems().add(rs.getString("subgroup_id"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void getSubject() throws SQLException {
        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM Subject");
            while (rs.next()) {
                choBox_subject.getItems().add(rs.getString("name"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Connection con = SQliteConnection.DBconnect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Session");

            while (rs.next()){
                objList.add(new ModelSession(rs.getInt("s_id"),rs.getString("s_lecturer1"),rs.getString("s_lecturer2"),rs.getString("s_tag"),rs.getString("s_groudId"),rs.getString("s_subject"),rs.getString("s_sessionType")));
            }

            getTag();
            getGroup();
            getSubject();

        }catch (Exception e){
            System.err.println("---- Error while retrieving data" + e);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        lec1.setCellValueFactory(new PropertyValueFactory<>("lec1"));
        lec2.setCellValueFactory(new PropertyValueFactory<>("lec2"));
        session.setCellValueFactory(new PropertyValueFactory<>("ssType"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        group.setCellValueFactory(new PropertyValueFactory<>("group"));
        tag.setCellValueFactory(new PropertyValueFactory<>("tag"));

        tableView.setItems(objList);
    }

    public void createSession(ActionEvent event) throws IOException {
        App.setRoot("AddSession1");
    }


    //------------------------------------------------------------------------------------------
}
