package org.TeamCipher;

import java.sql.*;

public class SQliteConnection {

    public static Connection DBconnect() {

        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/Users/SEMINA/IdeaProjects/Monarch-Lanka/db.db");
            System.out.print("---------##### Connected databaseee ####------");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.print(e);
            System.out.print("Connection error");
        }

        return con;
    }


}
