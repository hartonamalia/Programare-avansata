package com.example.Laborator11.client;

import com.example.Laborator11.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class PlayerClient {
    private final String SERVER_URL = "http://localhost:8082/api/v1/player";
    private RestTemplate restTemplate;

    public PlayerClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<Player> getPlayers() {
        ResponseEntity<Player[]> response = restTemplate.getForEntity(SERVER_URL, Player[].class);
        return Arrays.asList(response.getBody());
    }

    public String addPlayer(String name) {
        ResponseEntity<String> response = restTemplate.postForEntity(SERVER_URL + "?name=" + name, null, String.class);
        return response.getBody();
    }

    public String updatePlayer(String name, String newName) {
        restTemplate.put(SERVER_URL + "/" + name + "?newName=" + newName, null);
        return "Player " + name + "'s name has been updated successfully!";
    }

    public String deletePlayer(String name) {
        restTemplate.delete(SERVER_URL + "/" + name);
        return "Player " + name + "'s name has been deleted successfully!";
    }
}