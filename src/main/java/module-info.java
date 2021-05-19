module org.TeamCipher {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires sqlite.jdbc;

    opens org.TeamCipher to javafx.fxml;
    exports org.TeamCipher;
}