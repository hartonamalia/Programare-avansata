package com.example.Laborator11.controller;

import com.example.Laborator11.model.Player;
import com.example.Laborator11.service.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// acest controller ofera un endpoint prin care se pot obtine toti jucatorii din bd atunci cand se efectueaza o cerere GET la "/api/v1/player"
@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Metoda returneaza toti userii din baza de date
     * @return
     */
    @GetMapping()
    public List<Player> getPlayer(){
        return playerService.findAll();
    }
}
