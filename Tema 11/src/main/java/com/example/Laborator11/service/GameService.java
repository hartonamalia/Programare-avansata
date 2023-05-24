package com.example.Laborator11.service;

import com.example.Laborator11.model.Game;
import com.example.Laborator11.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void saveGame(Game newGame) {
        gameRepository.save(newGame);
    }


    public List<Game> getAll() {
        return gameRepository.findAll();
    }
}
