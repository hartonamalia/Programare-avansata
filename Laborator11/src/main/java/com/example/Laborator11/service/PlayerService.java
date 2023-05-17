package com.example.Laborator11.service;

import com.example.Laborator11.model.Player;
import com.example.Laborator11.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//aceasta rep un component de serviciu al ap
//in PlayerService e logica, implementez metodele din PlayerRepository

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }// este sg metoda expusa de serviciu si returneaza toți jucatorii din bd utilizand metodele puse la disp de ob playerRepository
}
