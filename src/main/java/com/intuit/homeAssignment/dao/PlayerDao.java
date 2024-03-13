package com.intuit.homeAssignment.dao;

import com.intuit.homeAssignment.persistence.Player;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PlayerDao {
    Optional<Player> getPlayerById(String id) throws IOException;

    List<Player> getPlayers() throws IOException;
}
