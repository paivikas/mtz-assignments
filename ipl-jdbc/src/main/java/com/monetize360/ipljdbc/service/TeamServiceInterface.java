package com.monetize360.ipljdbc.service;

import com.monetize360.ipljdbc.dto.PlayerDTO;
import com.monetize360.ipljdbc.dto.RoleCountDTO;
import com.monetize360.ipljdbc.dto.TeamAmountDTO;
import com.monetize360.ipljdbc.dto.TeamDTO;

import java.util.List;
import java.util.Map;

public interface TeamServiceInterface {
    List<TeamDTO> getAllTeamDetails();
    double teamAndRoleAmount(String label, String role);
    List<TeamAmountDTO> getAmountSpentByTeams();
    List<RoleCountDTO> getCountByRole(String label);
    Map<String, List<PlayerDTO>> getMaxPaidPlayersByRole();
    List<PlayerDTO> getPlayerByTeam(String label);
    List<PlayerDTO> getPlayerByTeamAndRole(String label, String role);
    List<PlayerDTO> getPlayersBySort(String fieldName);
    List<String> listTeamLabels();
}
