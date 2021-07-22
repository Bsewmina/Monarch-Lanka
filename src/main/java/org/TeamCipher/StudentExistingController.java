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

public class StudentExistingController implements Initializable {
    int tempId;
    ObservableList<Group> observableList = FXCollections.observableArrayList();
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    @FXML TextField  ST_ID,ST_AC,ST_PR,ST_GN,ST_GID,ST_SNO,ST_SID;
    @FXML
    private TableView<Group> ST_Table;
    @FXML
    private TableColumn<Group, Integer> id;
    @FXML
    private TableColumn<Group, String> academy;
    @FXML
    private TableColumn<Group, String> prgrm;
    @FXML
    private TableColumn<Group, Integer> gNo;
    @FXML
    private TableColumn<Group, String> gID;
    @FXML
    private TableColumn<Group,Integer> sgNo;
    @FXML
    private TableColumn<Group, String> sgID;


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

    @FXML
    void create_new_stdGroups(ActionEvent event) throws IOException {
        App.setRoot("add_groups");
    }

    public void manage_existing_stdGroups(ActionEvent event) throws IOException {
        App.setRoot("student_existing");
    }

    //------------------------------------------------------------- DB View

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM student_groups");
            System.out.println("In the view Quiry");
            while (rs.next()) {
                observableList.add(new Group(rs.getInt("ID"),
                        rs.getString("yearandsem"),
                        rs.getString("program"),
                        rs.getInt("group_no"),
                        rs.getString("group_id"),
                        rs.getInt("subgroup_no"),
                        rs.getString("subgroup_id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        academy.setCellValueFactory(new PropertyValueFactory<>("sem"));
        prgrm.setCellValueFactory(new PropertyValueFactory<>("program"));
        gNo.setCellValueFactory(new PropertyValueFactory<>("groupNo"));
        gID.setCellValueFactory(new PropertyValueFactory<>("groupID"));
        sgNo.setCellValueFactory(new PropertyValueFactory<>("subgroupNo"));
        sgID.setCellValueFactory(new PropertyValueFactory<>("getSubgroupID"));

        ST_Table.setItems(observableList);

    }


    //------------------------------------------------------------- DB Delete


    //Alert Delete
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

    //Alert Update
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


    public void delete(int id) {
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM student_groups WHERE ID = ?";
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
        if (ST_Table.getSelectionModel().getSelectedItems() != null) {

            Group location = ST_Table.getSelectionModel().getSelectedItem();
            ST_ID.setText(String.valueOf(location.getId()));
            ST_AC.setText(String.valueOf(location.getSem()));
            ST_PR.setText(String.valueOf(location.getProgram()));
            ST_GN.setText(String.valueOf(location.getGroupNo()));
            ST_GID.setText(String.valueOf(location.getGroupID()));
            ST_SNO.setText(String.valueOf(location.getSubgroupNo()));
            ST_SID.setText(String.valueOf(location.getGetSubgroupID()));

        }
    }

    @FXML
    private void updateDetails() throws IOException {

        String ay,p,g,sg;
        ay = ST_AC.getText();
        p =  ST_PR.getText();
        g =  ST_GN.getText();
        sg = ST_SNO.getText();
        String grpID = ay + "." + p + "." + g;
        String subgrpID = ay + "." + p + "." + g + "." + sg;
        ST_GID.setText(grpID);
        ST_SID.setText(subgrpID);

        int idText = Integer.parseInt(ST_ID.getText());
        String academic_yearText = ST_AC.getText();
        String programName = ST_PR.getText();
        int groupNumber = Integer.parseInt(ST_GN.getText());
        String group_idText = ST_GID.getText();
        int subNumber =Integer.parseInt(ST_SNO.getText());
        String subgroup_idText = ST_SID.getText();


        Updatedetails(idText,academic_yearText,programName,groupNumber,group_idText,subNumber,subgroup_idText);

        alertU(null);
        //refresh the page
        App.setRoot("student_existing");

    }

    public void delete(ActionEvent event) throws IOException {

        onClicked();
        int idText = Integer.parseInt(ST_ID.getText());
        delete(idText);
        //------------------------------POST DELETE ACTIONS
        //calling the alert
        alertD(null);

        //refresh the page
        App.setRoot("student_existing");
    }

//-------------------------------------------------------------DB UPDATE

    public static void Updatedetails(int id,String academic_year, String program, int group_inc,  String group_id, int subgroup_inc, String subgroup_id) {
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE student_groups SET yearandsem=?, program=?, group_no=?, group_id=?, subgroup_no=?, subgroup_id=? WHERE ID=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, academic_year);
            ps.setString(2, program);
            ps.setInt(3, group_inc);
            ps.setString(4, group_id);
            ps.setInt(5, subgroup_inc);
            ps.setString(6, subgroup_id);
            ps.setInt(7, id);
            ps.execute();
            System.out.println("Data Updated Successfully !!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
