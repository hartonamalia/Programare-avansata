package com.example.Laborator11.controller;

import com.example.Laborator11.model.Player;
import com.example.Laborator11.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getPlayer(){
        return playerService.findAll();
    }
    @PostMapping()
    public ResponseEntity<String> addPlayer(@RequestParam String name){
        Player player = new Player();
        player.setName(name);
        playerService.savePlayer(player);
            return  ResponseEntity.ok("Player " + player.getName() + " has been registered successfully!");
    }
    @PutMapping("/{name}")
    public ResponseEntity<String> updatePlayer(@PathVariable String name,@RequestParam String newName){
        Player player = playerService.getByName(name);
        if(player != null) {
            player.setName(newName);
            playerService.savePlayer(player);
            return ResponseEntity.ok("Player " + player.getName() + "'s name has been updated successfully!");
        }else {
            return ResponseEntity.status(404).body("Player not found");
        }
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<String> deletePlayer(@PathVariable String name){
        Player player = playerService.getByName(name);
        if(player != null) {

            playerService.deletePlayer(player);
            return ResponseEntity.ok("Player " + player.getName() + "'s name has been deleted successfully!");
        }else {
            return ResponseEntity.status(404).body("Player not found");
        }
    }
}
