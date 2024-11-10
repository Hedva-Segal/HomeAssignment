package com.intuit.homeAssignment.service;

import com.intuit.homeAssignment.dao.PlayerDao;
import com.intuit.homeAssignment.persistence.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerDao playerDao;

    public Optional<Player> getById(String id) throws IOException {
        final Optional<Player> player = playerDao.getPlayerById(id);
        return player;
    }

    public List<Player> getAll() throws IOException {
        List<Player> players = playerDao.getPlayers();
        return players;
    }
}
