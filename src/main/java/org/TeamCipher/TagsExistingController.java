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
import java.util.zip.InflaterInputStream;

public class TagsExistingController implements Initializable {
    int tempId;
    ObservableList<Tag> observableList = FXCollections.observableArrayList();
    @FXML
    private Label mainLabel;
    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    @FXML
    private TextField TAG_ID,TAG_SN,TAG_SC,TAG_RELT;

    @FXML
    private TableView<Tag> TAG_TABLE;
    @FXML
    private TableColumn<Tag, String> T_ID;
    @FXML
    private TableColumn<Tag, String> T_subname;
    @FXML
    private TableColumn<Tag, String> T_subcode;
    @FXML
    private TableColumn<Tag, String> T_reltag;


    @FXML
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

    public void statistic(ActionEvent event) {

        mainLabel.setText("statistics Clicked");
    }

    public void studentGoups(ActionEvent event) throws IOException {
        App.setRoot("student_groups_menu");
    }

    public void location(ActionEvent event ){
        mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) throws IOException {
        App.setRoot("tags_menu");
    }

    public void timeTables(ActionEvent event) {

        mainLabel.setText("Time Table Clicked");

    }

    public void manage_existing_tags(ActionEvent event) throws IOException {
        App.setRoot("tags_existing");
    }

    public void create_new_tags(ActionEvent event) throws IOException {
        App.setRoot("add_tags");
    }

    //------------------------------------------------------------- DB View

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Connection con = SQliteConnection.DBconnect();
        ResultSet rs = null;
        try {
            rs = con.createStatement().executeQuery("SELECT * FROM tags");
            System.out.println("In the view Quiry");
            while (rs.next()) {
                observableList.add(new Tag(rs.getInt("ID"),
                        rs.getString("sub_name"),
                        rs.getString("sub_code"),
                        rs.getString("related_tag")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        T_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        T_subname.setCellValueFactory(new PropertyValueFactory<>("tagName"));
        T_subcode.setCellValueFactory(new PropertyValueFactory<>("tagCode"));
        T_reltag.setCellValueFactory(new PropertyValueFactory<>("relTag"));


        TAG_TABLE.setItems(observableList);

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
            String sql = "DELETE FROM tags WHERE ID = ?";
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

        if (TAG_TABLE.getSelectionModel().getSelectedItems() != null) {

            Tag location = TAG_TABLE.getSelectionModel().getSelectedItem();
            // lid.setText(String.valueOf(location.getId()));
            tempId = location.getId();
            TAG_ID.setText(String.valueOf(location.getId()));
            TAG_SN.setText(String.valueOf(location.getTagName()));
            TAG_SC.setText(String.valueOf(location.getTagCode()));
            TAG_RELT.setText(String.valueOf(location.getRelTag()));

        }
    }

    @FXML
    private void updateTags() throws IOException {



        int idText = Integer.parseInt(TAG_ID.getText());
        String tag_snText = TAG_SN.getText();
        String tag_scText = TAG_SC.getText();
        String tag_reltText = TAG_RELT.getText();



        Updatedetails(idText,tag_snText,tag_scText,tag_reltText);

        alertU(null);
        //refresh the page
        App.setRoot("tags_existing");

    }


    public void delete(ActionEvent event) throws IOException {
        delete(tempId);

        //------------------------------POST DELETE ACTIONS
        //calling the alert
        alertD(null);

        //refresh the page
        App.setRoot("tags_existing");
    }

//-------------------------------------------------------------DB UPDATE

    public static void Updatedetails(int id,String tag_snText, String tag_scText,   String tag_reltText) {
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE tags SET sub_name=?, sub_code=?, related_tag=? WHERE ID=?";
            ps = con.prepareStatement(sql);

            ps.setString(1, tag_snText);
            ps.setString(2, tag_scText);
            ps.setString(3, tag_reltText);
            ps.setInt(4, id);
            ps.execute();
            System.out.println("Data Updated Successfully !!!!!!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
