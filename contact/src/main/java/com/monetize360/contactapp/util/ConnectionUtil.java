package com.monetize360.contactapp.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionUtil {

    private static Connection conn;
    private static Statement statement;

    // Method to create a connection
    public static Connection openConnection() {
        try (InputStream input = ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
            }
            Properties prop = new Properties();
            prop.load(input);

            String dbUrl = prop.getProperty("DB_URL");
            String user = prop.getProperty("USER");
            String password = prop.getProperty("PASS");
            conn = DriverManager.getConnection(dbUrl, user, password);
            System.out.println("Connection established successfully.");

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static Statement getStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return stmt;
    }

    public static void closeStatement(Statement stmt){
        try {
            stmt.close();

        }
        catch (Exception e){
            System.out.println();
        }
    }

    public static void closeConn(Connection conn){
        try {
            conn.close();

        }
        catch (Exception e){
            System.out.println();
        }
    }

}
