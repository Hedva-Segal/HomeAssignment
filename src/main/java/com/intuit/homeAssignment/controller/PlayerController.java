package com.intuit.homeAssignment.controller;

import com.intuit.homeAssignment.persistence.Player;
import com.intuit.homeAssignment.service.PlayerService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping({"api/players"})
public class PlayerController {
    private final Logger logger = LogManager.getLogger(PlayerController.class);
    @Autowired
    private PlayerService playerService;

    @GetMapping({"/{playerId}"})
    public ResponseEntity getById(@PathVariable
                                  @Size(min = 8, max = 9, message = "Player ID must be between 8 and 9 characters long")
                                  @Pattern(regexp = ".*\\d{2}$", message = "Player ID must end with two digits")
                                  String playerId) {
        try {
            final Optional<Player> player = this.playerService.getById(playerId);
            return player.map((value) -> new ResponseEntity(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity(HttpStatus.NOT_FOUND));
        } catch (IOException e) {
            return this.getErrorResponseEntity(e);
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<Player> players = playerService.getAll();
            return ResponseEntity.ok(players);
        } catch (IOException e) {
            return this.getErrorResponseEntity(e);
        }
    }

    @ExceptionHandler(IOException.class)
    private ResponseEntity getErrorResponseEntity(IOException e) {
        logger.error("Failed or interrupted I/O operations. " + e.getMessage());
        return ResponseEntity.internalServerError().body("Failed or interrupted I/O operations. please try later");
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity handleValidationException(ValidationException ex) {
        String errorMessage = ex.getMessage();
        logger.warn("API validation error: " + errorMessage);
        return ResponseEntity.badRequest().body(errorMessage);
    }
}

