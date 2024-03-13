package com.intuit.homeAssignment.dao;

import com.intuit.homeAssignment.constants.PlayerConstants;
import com.intuit.homeAssignment.persistence.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.*;

import static com.intuit.homeAssignment.constants.PlayerConstants.*;

@Repository
public class PlayerDaoImpl implements PlayerDao {

    private final Logger logger = LogManager.getLogger(PlayerDaoImpl.class);
    @Value("${file.path}")
    private String filePath;
    private final CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(HEADERS).setSkipHeaderRecord(true).build();


    public Optional<Player> getPlayerById(String id) throws IOException {
        try (Reader in = new FileReader(this.filePath)) {
            for (CSVRecord record : csvFormat.parse(in)) {
                if (record.get(PLAYER_ID).equals(id)) {
                    return Optional.of(this.convertPlayerFileRecordToPlayerObject(record));
                }
            }
        } catch (IOException e) {
            logger.error("Failed to parse file, the error: " + e.getMessage());
            throw e;
        }
        return Optional.empty();
    }

    public List<Player> getPlayers() throws IOException {
        final List<Player> playerList = new ArrayList<>();
        try (Reader in = new FileReader(this.filePath)) {
            for (CSVRecord record : csvFormat.parse(in)) {
                playerList.add(this.convertPlayerFileRecordToPlayerObject(record));
            }
        } catch (IOException e) {
            logger.error("Failed to parse file, the error: " + e.getMessage());
            throw e;
        }
        return playerList;
    }

    private Player convertPlayerFileRecordToPlayerObject(CSVRecord record) {
        final Player player = new Player();
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
            case PLAYER_ID -> player.setPlayerId(value);
            case BIRTH_YEAR -> player.setBirthYear(Integer.valueOf(value));
            case BIRTH_MONTH -> player.setBirthMonth(Integer.valueOf(value));
            case BIRTH_DAY -> player.setBirthDay(Integer.valueOf(value));
            case BIRTH_COUNTRY -> player.setBirthCountry(value);
            case BIRTH_STATE -> player.setBirthState(value);
            case BIRTH_CITY -> player.setBirthCity(value);
            case DEATH_YEAR -> player.setDeathYear(Integer.valueOf(value));
            case DEATH_MONTH -> player.setDeathMonth(Integer.valueOf(value));
            case DEATH_DAY -> player.setDeathDay(Integer.valueOf(value));
            case DEATH_COUNTRY -> player.setDeathCountry(value);
            case DEATH_STATE -> player.setDeathState(value);
            case DEATH_CITY -> player.setDeathCity(value);
            case NAME_FIRST -> player.setNameFirst(value);
            case NAME_LAST -> player.setNameLast(value);
            case NAME_GIVEN -> player.setNameGiven(value);
            case WEIGHT -> player.setWeight(Float.parseFloat(value));
            case HEIGHT -> player.setHeight(Float.parseFloat(value));
            case BATS -> player.setPlayerBats(value);
            case THROWS -> player.setPlayerThrows(value);
            case DEBUT -> player.setDebut(LocalDate.parse(value));
            case FINAL_GAME -> player.setFinalGame(LocalDate.parse(value));
            case RETRO_ID -> player.setRetroID(value);
            case BBREF_ID -> player.setBbrefID(value);
        }
    }
}