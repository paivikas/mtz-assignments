package com.monetize360;
import com.monetize360.iplstats.dto.PlayerDTO;
import com.monetize360.iplstats.dto.RoleCountDTO;
import com.monetize360.iplstats.dto.TeamAmountDTO;
import com.monetize360.iplstats.dto.TeamDTO;
import com.monetize360.iplstats.service.*;

import java.io.IOException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        TeamService teamService=new TeamService();
        Scanner sc=new Scanner(System.in);
        System.out.println("Question 1");
        List<String> getLabels=teamService.listTeamLabelsList();
        System.out.println("The Team Labels are:");
        System.out.println(getLabels);
        System.out.println();
        System.out.println("Question 2");
        System.out.println("Enter a team label:");
        String label=sc.nextLine();
        List<PlayerDTO> playerDTOList=teamService.getPlayerByTeamList(label);
        for (PlayerDTO player : playerDTOList)
        {
            System.out.println("Name:"+player.getName()+"     "+"Role:"+player.getRole()+"     "+"Price:"+player.getPrice());
        }
        System.out.println();
        System.out.println("Question 3");
        System.out.println("Enter a team label:");
        label=sc.nextLine();
        List<RoleCountDTO> roleCountDTOList=teamService.getCountByRoleList(label);
        for (RoleCountDTO role: roleCountDTOList){
            System.out.println("Role:"+role.getRoleName()+"     "+"Count:"+role.getCount());
        }
        System.out.println();
        System.out.println("Question 4");
        System.out.println("Enter a team label:");
        label=sc.nextLine();
        System.out.println("Enter a role");
        String role=sc.nextLine();
        List<PlayerDTO> playerRoles=teamService.getPlayerByTeamAndRoleList(label,role);
        for (PlayerDTO player:playerRoles){
            System.out.println("Name:"+player.getName()+"     "+"Price:"+player.getPrice()+"     Role:"+player.getRole());
        }
        System.out.println();
        System.out.println("Question 5");
        List<TeamDTO> allTeamDetails =teamService.getAllTeamDetailsList();
        System.out.println("Team Details are:");
        for (TeamDTO team:allTeamDetails){
            System.out.println("Home:"+team.getHome()+"     Coach:"+team.getCoach()+"     City:"+team.getCity()+"     Label:"+team.getLabel()+"     Team:"+team.getTeam());
        }
        System.out.println();
        System.out.println("Question 6");
        List<TeamAmountDTO> teamAmountDTOList=teamService.getAmountSpentByTeamsList();
        System.out.println("Amount spent by each team is");
        for (TeamAmountDTO teamAmount:teamAmountDTOList){
            System.out.println("Label:"+teamAmount.getLabel()+"     Amount Spent:"+teamAmount.getAmount());
        }
        System.out.println();
        System.out.println("Question 7");
        System.out.println("Enter a team label:");
        label=sc.nextLine();
        System.out.println("Enter a role");
        role=sc.nextLine();
        System.out.println("Total Amount:"+teamService.teamAndRoleAmount(label,role));
        System.out.println();
        System.out.println("Question 8");
        System.out.println("Enter the option:Name\nRole\nAmount");
        String fieldName =sc.nextLine();
        List<PlayerDTO> playerDTO=teamService.getPlayersBySort(fieldName);
        for (PlayerDTO player:playerDTO){
            System.out.println("Name:"+player.getName()+"     Role:"+player.getRole()+"     Price:"+player.getPrice());
        }
        System.out.println();
        System.out.println("Question 9");
        System.out.println("Maximum paid players for each role is:");
        Map<String, List<PlayerDTO>> result = teamService.getMaxPaidPlayersByRoleMap();
        result.forEach((roleOfPlayer, players) -> {
            System.out.println("Role: " + roleOfPlayer + ", Players: " + players);
        });
}}