package com.monetize360.iplstats.service;

import com.monetize360.iplstats.domain.PlayerDetails;
import com.monetize360.iplstats.domain.TeamDetails;
import com.monetize360.iplstats.dto.PlayerDTO;
import com.monetize360.iplstats.dto.RoleCountDTO;
import com.monetize360.iplstats.dto.TeamAmountDTO;
import com.monetize360.iplstats.dto.TeamDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.*;
import java.util.stream.Collectors;

public class TeamService implements TeamServiceInterface {
    ModelMapper modelMapper = new ModelMapper();
    List<TeamDetails> teamDetails = JsonReaderUtil.setTeamDetails();
    List<PlayerDetails> playerDetails = JsonReaderUtil.setPlayerDetails();

    //1
    public List<String> listTeamLabelsList() {
        return teamDetails.stream().map(TeamDetails::getLabel).collect(Collectors.toList());
    }

    //2
    public List<PlayerDTO> getPlayerByTeamList(String label) {

        return teamDetails.stream().filter(teamDetail -> teamDetail.getLabel().equals(label)).flatMap(teamDetail -> teamDetail.getPlayers().stream()).map(playerDetail -> {
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.setName(playerDetail.getName());
            playerDTO.setPrice(playerDetail.getAmount());
            playerDTO.setRole(playerDetail.getRole());
            return playerDTO;
        }).collect(Collectors.toList());
    }

    //3
    public List<RoleCountDTO> getCountByRoleList(String label) {

        return teamDetails.stream().filter(teamDetail -> teamDetail.getLabel().equals(label)).flatMap(teamDetail -> teamDetail.getPlayers().stream()).collect(Collectors.groupingBy(PlayerDetails::getRole, Collectors.counting())).entrySet().stream().map(entry -> {
            RoleCountDTO dto = new RoleCountDTO();
            dto.setRoleName(entry.getKey());
            dto.setCount(entry.getValue().intValue());
            return dto;
        }).collect(Collectors.toList());
    }

    //4
    public List<PlayerDTO> getPlayerByTeamAndRoleList(String label, String role) {
        return getPlayerByTeamList(label).stream().filter(playerDTO -> Objects.equals(role, playerDTO.getRole())).collect(Collectors.toList());
    }

    //5
    public List<TeamDTO> getAllTeamDetailsList() {
        return teamDetails.stream().map(teamDetails -> {
            TeamDTO team = new TeamDTO();
            team.setTeam(teamDetails.getName());
            team.setCity(teamDetails.getCity());
            team.setLabel(teamDetails.getLabel());
            team.setHome(teamDetails.getHome());
            team.setCoach(teamDetails.getCoach());
            return team;
        }).collect(Collectors.toList());
    }

    //6
    public List<TeamAmountDTO> getAmountSpentByTeamsList() {

        return teamDetails.stream().map(teamDetail -> {
            TeamAmountDTO teamAmount = new TeamAmountDTO();
            teamAmount.setLabel(teamDetail.getLabel());

            double totalAmount = teamDetail.getPlayers().stream().mapToDouble(PlayerDetails::getAmount).sum();

            teamAmount.setAmount(totalAmount);
            return teamAmount;
        }).collect(Collectors.toList());
    }

    //7
    public double teamAndRoleAmount(String label, String role) {
        return teamDetails.stream().filter(team -> team.getLabel().equals(label)).flatMap(team -> team.getPlayers().stream()).filter(player -> player.getRole().equals(role)).mapToDouble(PlayerDetails::getAmount).sum();
    }

    //8
    public List<PlayerDTO> getPlayersBySort(String fieldName) {

        Comparator<PlayerDetails> comparator;
        switch (fieldName) {
            case "name":
                comparator = Comparator.comparing(PlayerDetails::getName);
                break;
            case "role":
                comparator = Comparator.comparing(PlayerDetails::getRole);
                break;
            case "amount":
                comparator = Comparator.comparing(PlayerDetails::getAmount);
                break;
            default:
                System.out.println("Please specify either 1.name 2.role 3.amount");
                return List.of();
        }

        return playerDetails.stream().sorted(comparator).map(playerDetail -> {
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.setName(playerDetail.getName());
            playerDTO.setRole(playerDetail.getRole());
            playerDTO.setPrice(playerDetail.getAmount());
            return playerDTO;
        }).collect(Collectors.toList());
    }

    //9
    public Map<String, List<PlayerDTO>> getMaxPaidPlayersByRoleMap() {

        ModelMapper modelMapper1 = new ModelMapper();
        modelMapper1.addMappings(new PropertyMap<PlayerDetails, PlayerDTO>() {
            @Override
            protected void configure() {
                map().setPrice(source.getAmount());
            }
        });
        Map<String, List<PlayerDTO>> maxPaidPlayersByRole = new HashMap<>();
        playerDetails.forEach(player -> {
            PlayerDTO playerDTO = modelMapper.map(player, PlayerDTO.class);

            maxPaidPlayersByRole.merge(player.getRole(), new ArrayList<>(List.of(playerDTO)), (existing, newList) -> {
                if (player.getAmount() > existing.get(0).getPrice()) {
                    return newList;
                } else if (player.getAmount() == existing.get(0).getPrice()) {
                    existing.add(playerDTO);
                }
                return existing;
            });
        });

        return maxPaidPlayersByRole;
    }


}
