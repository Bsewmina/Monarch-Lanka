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
import java.util.ResourceBundle;

public class SubjectManagmentController implements Initializable {

    @FXML
    private Label mainLabel;

    @FXML
    private ImageView logo;
    //btnTimetables,btnLecturers,btnSubject,btnStudentGroups,btnLocation,btnTag,btnWorking,btnStatistic,btnSession,btnLogout;

    @FXML
    private TextField txtField_subName,txt_Field_subCode,btnID;

    @FXML
    private Spinner<Integer> spinner_noLecHours,spinner_noTutHours,spinner_noLabHours,spinner_noEvalHours;

    @FXML
    private ChoiceBox<?> choiceBox_year;

    @FXML
    private RadioButton rButton1,rButton2;

    @FXML
    private TableView<ModelSubjectTable>subjectTable;
    @FXML
    private TableColumn<ModelSubjectTable,Integer>subId;
    @FXML
    private TableColumn<ModelSubjectTable,String>subName;
    @FXML
    private TableColumn<ModelSubjectTable,String>subCode;
    @FXML
    private TableColumn<ModelSubjectTable,Integer>subYear;
    @FXML
    private TableColumn<ModelSubjectTable,Integer>sem;

    ObservableList<ModelSubjectTable> objList = FXCollections.observableArrayList();

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

    public void studentGoups(ActionEvent event) {

    }

    public void location(ActionEvent event ){
        mainLabel.setText("location Clicked");
    }

    public void tags(ActionEvent event) {

        mainLabel.setText("tags Clicked");
    }

    public void timeTables(ActionEvent event) throws IOException {

        App.setRoot("new_lecturer");
        //mainLabel.setText("Time Table Clicked");

    }

    public void addSubject(ActionEvent event) throws IOException{
        App.setRoot("new_subject");
    }
    //------------------------------------------------------------------------------------------


    public void Clear(ActionEvent event) {
        txtField_subName.setText("");
        txt_Field_subCode.setText("");
        spinner_noLecHours.getValueFactory().setValue(0);
        spinner_noTutHours.getValueFactory().setValue(0);
        spinner_noLabHours.getValueFactory().setValue(0);
        spinner_noEvalHours.getValueFactory().setValue(0);
        btnID.setText(null);
    }

    public void Delete(ActionEvent event) {
        String temp = btnID.getText().toString();
        if (temp.equals("")){
            System.out.println("Insert ID");
        }
        else
            deleteData(Integer.parseInt(temp));
    }

    public void Update(ActionEvent event) {
        String check = validate();

        //validate fields
        int semester = getSemester();
        if (!check.equals("true")) {
            System.out.println(check);
        }
        //validate year
        if (semester == 0) {
            System.out.println("add semester");
        } else
            UpdateSubject(Integer.parseInt(btnID.getText()),txtField_subName.getText(),txt_Field_subCode.getText(),Integer.parseInt((String) choiceBox_year.getValue()),semester,Integer.parseInt(spinner_noLecHours.getValue().toString()),Integer.parseInt(spinner_noTutHours.getValue().toString()),Integer.parseInt(spinner_noLabHours.getValue().toString()),Integer.parseInt(spinner_noEvalHours.getValue().toString()));
    }

    public int getSemester(){
        if (rButton1.isSelected()){
            mainLabel.setText(rButton1.getText());
            return 1;
        }
        else if (rButton2.isSelected()){
            mainLabel.setText(rButton2.getText());
            return 2;
        }
        return 0;
    }

    public String validate() {
        if(choiceBox_year.getValue() == null){
            return "insert Year";
        }
        else if(txtField_subName.getText().equals("")){
            return  "insert SubjectName";
        }
        else if(txt_Field_subCode.getText().equals("")){
            return  "insert SubjectCode";
        }
        else if(spinner_noLecHours.getValue() == null){
            return  "insert Lecture Hours";
        }
        else if(spinner_noTutHours.getValue() == null){
            return  "insert Tute Hours";
        }
        else if(spinner_noLabHours.getValue() == null){
            return  "insert Lab Hours";
        }
        else if(spinner_noEvalHours.getValue() == null){
            return  "insert Evaluation Hours";
        }
        return  "true";
    }

    public void deleteData(int id){
        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM Subject WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            System.out.println("Data Deleted Successfully !!!!!");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while Deleting Data !!!!!");
        }

    }

    public static void UpdateSubject(int id,String name,String code,int year,int semester,int lec_hours,int tut_hours,int lab_hours,int evaluation_hours) {

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "UPDATE Subject SET name=?, code=?, year=?, semester=?, lec_hours=?, tut_hours=?, lab_hours=?, evaluation_hours=? WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1,"dddddd");
            ps.setString(2,code);
            ps.setInt(3,year);
            ps.setInt(4,semester);
            ps.setInt(5,lec_hours);
            ps.setInt(6,tut_hours);
            ps.setInt(7,lab_hours);
            ps.setInt(8,evaluation_hours);
            ps.setInt(9,id);
            ps.execute();
            System.out.println("Data updated successfully !!!!!");

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while updating data !!!!!");
        }
    }

    @FXML
    private void selectRow() {
        if (subjectTable.getSelectionModel().getSelectedItems() != null) {

            ModelSubjectTable subTableObj = subjectTable.getSelectionModel().getSelectedItem();
            btnID.setText(String.valueOf(subTableObj.getId()));
            txtField_subName.setText(subTableObj.getSubjName());
            txt_Field_subCode.setText(subTableObj.getSubjCode());
            //rButton1.set(subTableObj.getSemester());
            //  choiceBox_year.setValue(lecturerTable.getFaculty());
            //  getFaculty.setText(lecturerTable.getType());


        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Connection con = SQliteConnection.DBconnect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Subject");

            while (rs.next()){
                objList.add(new ModelSubjectTable(rs.getInt("id"),rs.getString("name"),rs.getString("code"),rs.getInt("year"),rs.getInt("semester")));
            }

        }catch (Exception e){
            System.err.println("-------------- Error while retrieving data" + e);
        }

        subId.setCellValueFactory(new PropertyValueFactory<>("id"));
        subName.setCellValueFactory(new PropertyValueFactory<>("subjName"));
        subCode.setCellValueFactory(new PropertyValueFactory<>("subjCode"));
        subYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        sem.setCellValueFactory(new PropertyValueFactory<>("semester"));

        subjectTable.setItems(objList);

    }
}
