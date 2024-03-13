package com.intuit.homeAssignment;

import com.intuit.homeAssignment.dao.PlayerDaoImpl;
import com.intuit.homeAssignment.persistence.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(
        locations = {"classpath:application-test.properties"}
)
public class PlayerDaoIntegrationTest {
    @Autowired
    private PlayerDaoImpl playerDao;

    public PlayerDaoIntegrationTest() {
    }

    @Test
    public void testGetPlayerById_PlayerExists() throws IOException {
        String playerId = "aardsda01";
        Optional<Player> playerOptional = this.playerDao.getPlayerById(playerId);
        Assertions.assertTrue(playerOptional.isPresent());
        Player player = (Player) playerOptional.get();
        Assertions.assertEquals(playerId, player.getPlayerId());
    }

    @Test
    public void testGetPlayerById_PlayerDoesNotExist() throws IOException {
        String playerId = "nonexistent_player_id";
        Optional<Player> playerOptional = this.playerDao.getPlayerById(playerId);
        Assertions.assertTrue(playerOptional.isEmpty());
    }

    @Test
    public void testGetPlayers() throws IOException {
        List<Player> players = this.playerDao.getPlayers();
        Assertions.assertEquals(2, players.size());
    }
}

