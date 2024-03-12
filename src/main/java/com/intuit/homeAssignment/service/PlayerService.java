package com.intuit.homeAssignment.service;

import com.intuit.homeAssignment.dao.PlayerDao;
import com.intuit.homeAssignment.data.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    @Autowired
    private PlayerDao playerDao;

    public PlayerService() {
    }

    public Optional<Player> getById(String id) throws IOException {
        return this.playerDao.getPlayerById(id);
    }

    public List<Player> getAll() throws IOException {
        return this.playerDao.getPlayers();
    }
}