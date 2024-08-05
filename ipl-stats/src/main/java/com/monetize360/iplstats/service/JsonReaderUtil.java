package com.monetize360.iplstats.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.iplstats.domain.PlayerDetails;
import com.monetize360.iplstats.domain.TeamDetails;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class JsonReaderUtil {
    static JsonNode node = JsonReaderUtil.loadJson();

    private JsonReaderUtil() {
    }

    public static JsonNode loadJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;
        Reader reader;
        try {
            InputStream inputStream = JsonReaderUtil.class.getClassLoader().getResourceAsStream("ipl2020.json");

            assert inputStream != null;
            reader = new InputStreamReader(inputStream);
            node = objectMapper.readTree(reader);
        } catch (IOException e) {
            System.out.println("IOException occurred");
        }
        return node;
    }

    public static List<TeamDetails> setTeamDetails() {

        return StreamSupport.stream(node.spliterator(), false).map(teamNode -> {
            TeamDetails teamDetails = new TeamDetails();
            teamDetails.setCoach(teamNode.get("coach").asText());
            teamDetails.setCity(teamNode.get("city").asText());
            teamDetails.setLabel(teamNode.get("label").asText());
            teamDetails.setName(teamNode.get("name").asText());
            teamDetails.setHome(teamNode.get("home").asText());

            List<PlayerDetails> playerDetails = StreamSupport.stream(teamNode.get("players").spliterator(), false).map(player -> {
                PlayerDetails playerDetail = new PlayerDetails();
                playerDetail.setRole(player.get("role").asText());
                playerDetail.setName(player.get("name").asText());
                playerDetail.setAmount(player.get("price").asLong());
                return playerDetail;
            }).collect(Collectors.toList());

            teamDetails.setPlayers(playerDetails);
            return teamDetails;
        }).collect(Collectors.toList());
    }

    public static List<PlayerDetails> setPlayerDetails() {
        List<TeamDetails> teamDetailsList = setTeamDetails();

        return teamDetailsList.stream().flatMap(team -> team.getPlayers().stream()).collect(Collectors.toList());
    }
}

