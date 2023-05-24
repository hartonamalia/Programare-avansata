package com.example.Laborator11.service;

import com.example.Laborator11.model.Player;
import com.example.Laborator11.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public boolean findByName(String name) {
        return playerRepository.findByName(name);
    }

    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

    public Player getByName(String name) {
        return playerRepository.getByName(name);
    }

    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }
}
