package com.example.Laborator11.repository;

import com.example.Laborator11.model.Game;
import com.example.Laborator11.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GameRepository extends JpaRepository<Game, UUID> {
}
