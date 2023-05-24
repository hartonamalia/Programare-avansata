package com.example.Laborator11.client;

import com.example.Laborator11.dto.GameRequestDto;
import com.example.Laborator11.dto.GameResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@Service
public class GameClient {
    private final String SERVER_URL = "http://localhost:8082/api/v1/game";
    private RestTemplate restTemplate;

    public GameClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<GameResponseDto> getGames() {
        ResponseEntity<GameResponseDto[]> response = restTemplate.getForEntity(SERVER_URL, GameResponseDto[].class);
        return Arrays.asList(response.getBody());
    }

    public String addGame(GameRequestDto gameDto) {
        ResponseEntity<String> response = restTemplate.postForEntity(SERVER_URL, gameDto, String.class);
        return response.getBody();
    }
}