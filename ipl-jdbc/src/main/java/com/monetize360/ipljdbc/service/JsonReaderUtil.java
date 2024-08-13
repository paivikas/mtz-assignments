package com.monetize360.ipljdbc.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import com.monetize360.ipljdbc.domain.TeamDetails;

import java.io.*;

import java.util.Collections;
import java.util.List;


public class JsonReaderUtil {
    private JsonReaderUtil(){

    }
    public static List<TeamDetails> setTeamDetails() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<TeamDetails> teams;
        String fileName="ipl2020.json";
        try (InputStream inputStream = JsonReaderUtil.class.getClassLoader().getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (InputStreamReader reader = new InputStreamReader(inputStream)) {

                CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, TeamDetails.class);
                teams = objectMapper.readValue(reader, listType);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

        return teams;
    }
}