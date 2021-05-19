package org.TeamCipher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    String tempRank;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Monarch Lanka Institute Time-Table management System");
        scene = new Scene(loadFXML("primary"), 1250, 750);
        stage.setScene(scene);
        stage.show();
        SQliteConnection.DBconnect();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /*
    public static void insert(int id,String name,String faculty,String dept,String center,String building,int level,String rank){

        Connection con = SQliteConnection.DBconnect();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO Lecturer (empId,name,faculty,department,center,building,level,rank) VALUES (?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setString(3,faculty);
            ps.setString(4,dept);
            ps.setString(5,center);
            ps.setString(6,building);
            ps.setInt(7,level);
            ps.setString(8,rank);

            ps.execute();
            System.out.println("Data added successfully !!!!!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     */

    public static void main(String[] args) {
        launch();
        //insert(01,"nirmal","mathematics");
        //SQliteConnection.DBconnect();

    }

}