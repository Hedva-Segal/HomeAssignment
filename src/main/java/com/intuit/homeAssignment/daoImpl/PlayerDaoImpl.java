package com.intuit.homeAssignment.daoImpl;

import com.intuit.homeAssignment.dao.PlayerDao;
import com.intuit.homeAssignment.data.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.*;

@Repository
public class PlayerDaoImpl implements PlayerDao {
    @Value("${file.path}")
    private String filePath;
    private static final String[] HEADERS = new String[]{"playerID", "birthYear", "birthMonth", "birthDay", "birthCountry", "birthState", "birthCity", "deathYear", "deathMonth", "deathDay", "deathCountry", "deathState", "deathCity", "nameFirst", "nameLast", "nameGiven", "weight", "height", "bats", "throws", "debut", "finalGame", "retroID", "bbrefID"};
    private final CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();


    public Optional<Player> getPlayerById(String id) throws IOException {
        try (Reader in = new FileReader(this.filePath)) {
            for (CSVRecord record : csvFormat.parse(in)) {
                if (record.get("playerID").equals(id)) {
                    return Optional.of(this.convertPlayerFileRecordToPlayerObject(record));
                }
            }
        }
        return Optional.empty();
    }

    public List<Player> getPlayers() throws IOException {
        List<Player> playerList = new ArrayList();
        try (Reader in = new FileReader(this.filePath)) {
            for (CSVRecord record : csvFormat.parse(in)) {
                playerList.add(this.convertPlayerFileRecordToPlayerObject(record));
            }
        }
        return playerList;
    }

    private Player convertPlayerFileRecordToPlayerObject(CSVRecord record) {
        Player player = new Player();
        Arrays.stream(HEADERS).forEach((header) -> {
            String value = record.get(header);
            if (!value.isEmpty()) {
                this.setFieldByHeader(header, player, value);
            }

        });
        return player;
    }

    private void setFieldByHeader(String header, Player player, String value) {
        switch (header) {
            case "playerID" -> player.setPlayerId(value);
            case "birthYear" -> player.setBirthYear(Integer.valueOf(value));
            case "birthMonth" -> player.setBirthMonth(Integer.valueOf(value));
            case "birthDay" -> player.setBirthDay(Integer.valueOf(value));
            case "birthCountry" -> player.setBirthCountry(value);
            case "birthState" -> player.setBirthState(value);
            case "birthCity" -> player.setBirthCity(value);
            case "deathYear" -> player.setDeathYear(Integer.valueOf(value));
            case "deathMonth" -> player.setDeathMonth(Integer.valueOf(value));
            case "deathDay" -> player.setDeathDay(Integer.valueOf(value));
            case "deathCountry" -> player.setDeathCountry(value);
            case "deathState" -> player.setDeathState(value);
            case "deathCity" -> player.setDeathCity(value);
            case "nameFirst" -> player.setNameFirst(value);
            case "nameLast" -> player.setNameLast(value);
            case "nameGiven" -> player.setNameGiven(value);
            case "weight" -> player.setWeight(Float.parseFloat(value));
            case "height" -> player.setHeight(Float.parseFloat(value));
            case "bats" -> player.setPlayerBats(value);
            case "throws" -> player.setPlayerThrows(value);
            case "debut" -> player.setDebut(LocalDate.parse(value));
            case "finalGame" -> player.setFinalGame(LocalDate.parse(value));
            case "retroID" -> player.setRetroID(value);
            case "bbrefID" -> player.setBbrefID(value);
        }

    }
}