package com.example.Laborator11.controller;

import com.example.Laborator11.dto.GameRequestDto;
import com.example.Laborator11.dto.GameResponseDto;
import com.example.Laborator11.model.Game;
import com.example.Laborator11.model.Player;
import com.example.Laborator11.service.GameService;
import com.example.Laborator11.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    private final GameService gameService;
    private final PlayerService playerService;

    public GameController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }
    @GetMapping
    public ResponseEntity<List<GameResponseDto>> getGames(){
        List<Game> gamesFound = gameService.getAll();
        List<GameResponseDto> games = gamesFound.stream()
                .map(game -> new GameResponseDto(game.getPlayer1().getName(),game.getPlayer2().getName(),game.getWinner().getName()))
                .toList();
        return ResponseEntity.ok(games);
    }
    @PostMapping()
    public ResponseEntity<String> addGame(@RequestBody GameRequestDto gameDto){
        Player player1 = playerService.getByName(gameDto.getName1());
        Player player2 = playerService.getByName(gameDto.getName2());
        Player winner = playerService.getByName(gameDto.getWinner());
        if(player1 == null || player2 == null || winner == null){
            return new ResponseEntity<>("One of the players is not registered", HttpStatus.NOT_FOUND);
        }

        Game newGame = new Game();
        newGame.setGameTimestamp(LocalDateTime.now());
        newGame.setPlayer1(player1);
        newGame.setPlayer2(player2);
        newGame.setWinner(winner);
        gameService.saveGame(newGame);
        return new ResponseEntity<>("Game saved succesfully between " + gameDto.getName1() + " and " + gameDto.getName2(),HttpStatus.CREATED);
    }

}
