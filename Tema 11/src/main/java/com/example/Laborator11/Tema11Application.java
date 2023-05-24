package com.example.Laborator11;

import com.example.Laborator11.client.GameClient;
import com.example.Laborator11.client.PlayerClient;
import com.example.Laborator11.dto.GameRequestDto;
import com.example.Laborator11.dto.GameResponseDto;
import com.example.Laborator11.model.Player;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication

public class Tema11Application {

	public static void main(String[] args) {
		SpringApplication.run(Tema11Application.class, args);
		players();
		games();
	}
	public static void games(){
		GameClient gameClient = new GameClient();

		List<GameResponseDto> games = gameClient.getGames();
		for (GameResponseDto game : games) {
			System.out.println("Game ID: " + game.getWinner());
			System.out.println("Game Name: " + game.getName1());
		}

		GameRequestDto newGame = new GameRequestDto();
		newGame.setName1("amalia");
		newGame.setName2("nicola");
		newGame.setWinner("amalia");

		String response = gameClient.addGame(newGame);
		System.out.println("Response from server: " + response);
	}
	public static void players(){
		PlayerClient playerClient = new PlayerClient();

		List<Player> players = playerClient.getPlayers();
		for (Player player : players) {
			System.out.println("Player ID: " + player.getId());
			System.out.println("Player Name: " + player.getName());
		}

		String addPlayerResponse = playerClient.addPlayer("New Player");
		System.out.println("Add player response: " + addPlayerResponse);

		String updatePlayerResponse = playerClient.updatePlayer("New Player", "Updated Player");
		System.out.println("Update player response: " + updatePlayerResponse);

		String deletePlayerResponse = playerClient.deletePlayer("Updated Player");
		System.out.println("Delete player response: " + deletePlayerResponse);
	}
}
