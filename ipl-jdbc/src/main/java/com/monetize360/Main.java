package com.monetize360;

import com.monetize360.ipljdbc.domain.TeamDetails;
import com.monetize360.ipljdbc.dto.PlayerDTO;
import com.monetize360.ipljdbc.dto.RoleCountDTO;
import com.monetize360.ipljdbc.dto.TeamAmountDTO;
import com.monetize360.ipljdbc.dto.TeamDTO;
import com.monetize360.ipljdbc.service.DbUtil;
import com.monetize360.ipljdbc.service.JsonReaderUtil;
import com.monetize360.ipljdbc.service.TeamService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<TeamDetails> teamDetails = JsonReaderUtil.setTeamDetails();
        DbUtil dbUtil = new DbUtil();
        dbUtil.dbSetup();

        dbUtil.insertData(teamDetails);
        TeamService teamService = new TeamService();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Getting all team details...");
            List<TeamDTO> teamDTOs = teamService.getAllTeamDetails();
            for (TeamDTO team : teamDTOs) {
                System.out.println(team);
            }

            System.out.println("Getting amount spent on a role by team...");
            System.out.print("Enter team label: ");
            String label2 = scanner.nextLine();
            System.out.print("Enter role: ");
            String role = scanner.nextLine();
            double amountSpent = teamService.teamAndRoleAmount(label2, role);
            System.out.println("Total amount spent on " + role + " by team " + label2 + ": " + amountSpent);

            System.out.println("Getting amount spent by each team...");
            List<TeamAmountDTO> teamAmountDTOs = teamService.getAmountSpentByTeams();
            for (TeamAmountDTO teamAmount : teamAmountDTOs) {
                System.out.println(teamAmount);
            }

            System.out.println("Getting count by role for a team...");
            System.out.print("Enter team label: ");
            String label4 = scanner.nextLine();
            List<RoleCountDTO> roleCountDTOs = teamService.getCountByRole(label4);
            for (RoleCountDTO roleCount : roleCountDTOs) {
                System.out.println(roleCount);
            }

            System.out.println("Getting max paid players by role...");
            Map<String, List<PlayerDTO>> maxPaidPlayersByRole = teamService.getMaxPaidPlayersByRole();
            for (Map.Entry<String, List<PlayerDTO>> entry : maxPaidPlayersByRole.entrySet()) {
                System.out.println("Role: " + entry.getKey());
                for (PlayerDTO player : entry.getValue()) {
                    System.out.println(player);
                }
            }

            System.out.println("Getting players by team...");
            System.out.print("Enter team label: ");
            String label6 = scanner.nextLine();
            List<PlayerDTO> playersByTeam = teamService.getPlayerByTeam(label6);
            for (PlayerDTO player : playersByTeam) {
                System.out.println(player);
            }

            System.out.println("Getting players by team and role...");
            System.out.print("Enter team label: ");
            String label7 = scanner.nextLine();
            System.out.print("Enter role: ");
            String role7 = scanner.nextLine();
            List<PlayerDTO> playersByTeamAndRole = teamService.getPlayerByTeamAndRole(label7, role7);
            for (PlayerDTO player : playersByTeamAndRole) {
                System.out.println(player);
            }

            System.out.println("Getting players sorted by field...");
            System.out.print("Enter field name to sort by: ");
            String fieldName = scanner.nextLine();
            List<PlayerDTO> sortedPlayers = teamService.getPlayersBySort(fieldName);
            for (PlayerDTO player : sortedPlayers) {
                System.out.println(player);
            }

            System.out.println("Listing all team labels...");
            List<String> teamLabels = teamService.listTeamLabels();
            for (String label : teamLabels) {
                System.out.println(label);
            }
        } finally {
            dbUtil.closeConn();
        }
    }
}
