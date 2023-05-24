package com.example.Laborator11.repository;

import com.example.Laborator11.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<Player, UUID> {
    boolean findByName(String name);

    Player getByName(String name);
}
