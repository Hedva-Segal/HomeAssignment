package com.intuit.homeAssignment.controller;

import com.intuit.homeAssignment.data.Player;
import com.intuit.homeAssignment.service.PlayerService;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"api/players"})
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping({"/{playerId}"})
    public ResponseEntity getById(@PathVariable String playerId) {
        try {
            Optional<Player> player = this.playerService.getById(playerId);
            return player.map((value) -> new ResponseEntity(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
        } catch (IOException e) {
            return this.getErrorResponseEntity(e);
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(this.playerService.getAll());
        } catch (IOException e) {
            return this.getErrorResponseEntity(e);
        }
    }

    private ResponseEntity<String> getErrorResponseEntity(IOException e) {
        e.printStackTrace();
        return ResponseEntity.internalServerError().body("Failed or interrupted I/O operations. please try later");
    }
}

