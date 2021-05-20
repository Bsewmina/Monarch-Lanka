package org.TeamCipher;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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


    public static void main(String[] args) {
        launch();


    }

}