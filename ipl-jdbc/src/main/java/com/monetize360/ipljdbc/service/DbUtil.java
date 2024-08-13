package com.monetize360.ipljdbc.service;

import com.monetize360.ipljdbc.domain.PlayerDetails;
import com.monetize360.ipljdbc.domain.TeamDetails;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DbUtil {
    private Connection conn = null;

    public Connection connection() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }
            Properties prop = new Properties();
            prop.load(input);

            String dbUrl = prop.getProperty("DB_URL");
            String user = prop.getProperty("USER");
            String password = prop.getProperty("PASS");
            conn = DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void dbSetup() {
        try {
            conn = connection();
            Statement stmt = conn.createStatement();

            String createTeamsTable = "CREATE TABLE IF NOT EXISTS teams (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255) UNIQUE," +
                    "city VARCHAR(255)," +
                    "coach VARCHAR(255)," +
                    "home VARCHAR(255)," +
                    "label VARCHAR(50)" +
                    ")";
            stmt.executeUpdate(createTeamsTable);

            String createPlayersTable = "CREATE TABLE IF NOT EXISTS players (" +
                    "id SERIAL PRIMARY KEY," +
                    "team_name VARCHAR(255)," +
                    "name VARCHAR(255)," +
                    "price DOUBLE PRECISION," +
                    "role VARCHAR(50)," +
                    "FOREIGN KEY (team_name) REFERENCES teams(name)," +
                    "UNIQUE (team_name, name)" +
                    ")";
            stmt.executeUpdate(createPlayersTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(List<TeamDetails> teams) {
        try {
            conn = connection();
            if (isTableEmpty(conn, "teams")) {
                String teamSql = "INSERT INTO teams (home, label, name, coach, city) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement teamStmt = conn.prepareStatement(teamSql)) {
                    for (TeamDetails team : teams) {
                        teamStmt.setString(1, team.getHome());
                        teamStmt.setString(2, team.getLabel());
                        teamStmt.setString(3, team.getName());
                        teamStmt.setString(4, team.getCoach());
                        teamStmt.setString(5, team.getCity());
                        teamStmt.addBatch();
                    }
                    teamStmt.executeBatch();
                }
            }

            if (isTableEmpty(conn, "players")) {
                String playerSql = "INSERT INTO players (team_name, role, name, price) VALUES (?, ?, ?, ?)";
                try (PreparedStatement playerStmt = conn.prepareStatement(playerSql)) {
                    for (TeamDetails team : teams) {
                        for (PlayerDetails player : team.getPlayers()) {
                            playerStmt.setString(1, team.getName());
                            playerStmt.setString(2, player.getRole());
                            playerStmt.setString(3, player.getName());
                            playerStmt.setDouble(4, player.getPrice());
                            playerStmt.addBatch();
                        }
                    }
                    playerStmt.executeBatch();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isTableEmpty(Connection connection, String tableName) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName;
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        }
        return false;
    }

    public void closeConn() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
