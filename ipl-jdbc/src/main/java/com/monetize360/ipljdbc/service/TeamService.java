package com.monetize360.ipljdbc.service;

import com.monetize360.ipljdbc.dto.PlayerDTO;
import com.monetize360.ipljdbc.dto.RoleCountDTO;
import com.monetize360.ipljdbc.dto.TeamAmountDTO;
import com.monetize360.ipljdbc.dto.TeamDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamService implements TeamServiceInterface {

    DbUtil util = new DbUtil();
    Connection connection = util.connection();

    @Override
    public List<TeamDTO> getAllTeamDetails() {
        String sql = "SELECT * FROM teams";
        List<TeamDTO> teamDTOList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TeamDTO dto = new TeamDTO();
                dto.setCity(rs.getString("city"));
                dto.setCoach(rs.getString("coach"));
                dto.setLabel(rs.getString("label"));
                dto.setHome(rs.getString("home"));
                dto.setTeam(rs.getString("name"));
                teamDTOList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamDTOList;
    }

    @Override
    public double teamAndRoleAmount(String label, String role) {
        double price = 0;
        String sql = "SELECT SUM(price) AS total_amount FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?) AND role = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            stmt.setString(2, role);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    price = rs.getDouble("total_amount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    @Override
    public List<TeamAmountDTO> getAmountSpentByTeams() {
        String sql = "SELECT team_name, SUM(price) AS total_amount FROM players GROUP BY team_name";
        List<TeamAmountDTO> teamAmountDTOList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                TeamAmountDTO dto = new TeamAmountDTO();
                dto.setLabel(rs.getString("team_name"));
                dto.setAmount(rs.getDouble("total_amount"));
                teamAmountDTOList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamAmountDTOList;
    }

    @Override
    public List<RoleCountDTO> getCountByRole(String label) {
        String sql = "SELECT role, COUNT(*) AS role_count FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?) GROUP BY role";
        List<RoleCountDTO> roleCountDTOList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RoleCountDTO dto = new RoleCountDTO();
                    dto.setRoleName(rs.getString("role"));
                    dto.setCount(rs.getInt("role_count"));
                    roleCountDTOList.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleCountDTOList;
    }

    @Override
    public Map<String, List<PlayerDTO>> getMaxPaidPlayersByRole() {
        String sql = "SELECT role, name, MAX(price) AS max_price FROM players GROUP BY role, name";
        Map<String, List<PlayerDTO>> roleToPlayerMap = new HashMap<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String role = rs.getString("role");
                PlayerDTO dto = new PlayerDTO();
                dto.setName(rs.getString("name"));
                dto.setPrice(rs.getLong("max_price"));
                dto.setRole(role);

                if (!roleToPlayerMap.containsKey(role)) {
                    roleToPlayerMap.put(role, new ArrayList<>());
                }
                roleToPlayerMap.get(role).add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleToPlayerMap;
    }

    @Override
    public List<PlayerDTO> getPlayerByTeam(String label) {
        String sql = "SELECT name, price, role FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?)";
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PlayerDTO dto = new PlayerDTO();
                    dto.setName(rs.getString("name"));
                    dto.setPrice(rs.getLong("price"));
                    dto.setRole(rs.getString("role"));
                    playerDTOList.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerDTOList;
    }

    @Override
    public List<PlayerDTO> getPlayerByTeamAndRole(String label, String role) {
        String sql = "SELECT name, price, role FROM players WHERE team_name IN (SELECT name FROM teams WHERE label = ?) AND role = ?";
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, label);
            stmt.setString(2, role);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PlayerDTO dto = new PlayerDTO();
                    dto.setName(rs.getString("name"));
                    dto.setPrice(rs.getLong("price"));
                    dto.setRole(rs.getString("role"));
                    playerDTOList.add(dto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerDTOList;
    }

    @Override
    public List<PlayerDTO> getPlayersBySort(String fieldName) {
        String sql = "SELECT * FROM players ORDER BY " + fieldName;
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                PlayerDTO dto = new PlayerDTO();
                dto.setName(rs.getString("name"));
                dto.setPrice(rs.getLong("price"));
                dto.setRole(rs.getString("role"));
                playerDTOList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerDTOList;
    }

    @Override
    public List<String> listTeamLabels() {
        String sql = "SELECT DISTINCT label FROM teams";
        List<String> teamLabels = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                teamLabels.add(rs.getString("label"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamLabels;
    }
}
