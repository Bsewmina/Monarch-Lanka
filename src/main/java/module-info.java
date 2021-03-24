module org.TeamCipher {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.TeamCipher to javafx.fxml;
    exports org.TeamCipher;
}