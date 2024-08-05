package com.monetize360.iplstats.service;

import com.monetize360.iplstats.dto.PlayerDTO;
import com.monetize360.iplstats.dto.RoleCountDTO;
import com.monetize360.iplstats.dto.TeamAmountDTO;
import com.monetize360.iplstats.dto.TeamDTO;

import java.util.List;
import java.util.Map;

public interface TeamServiceInterface {
    List<TeamDTO> getAllTeamDetailsList();
    double teamAndRoleAmount(String label, String role);
    List<TeamAmountDTO> getAmountSpentByTeamsList();
    List<RoleCountDTO> getCountByRoleList(String label);
    Map<String, List<PlayerDTO>> getMaxPaidPlayersByRoleMap();
    List<PlayerDTO> getPlayerByTeamList(String label);
    List<PlayerDTO> getPlayerByTeamAndRoleList(String label, String role);
    List<PlayerDTO> getPlayersBySort(String fieldName);
    List<String> listTeamLabelsList();
}
