package org.TeamCipher;

import java.sql.*;

public class SQliteConnection {
    public static Connection DBconnect(){

        Connection con = null;

        try{

            Class.forName("org.sqlite.JDBC");//identifier
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\SEMINA\\IdeaProjects\\Monarch-Lanka\\database.db");
            System.out.println("-----------------------------------Connected to Database---------------------------------");


        }catch(ClassNotFoundException | SQLException e){


            System.out.println(e);
            System.out.println("-------------------------------------Connection error");

        }

        return con;
    }
}
