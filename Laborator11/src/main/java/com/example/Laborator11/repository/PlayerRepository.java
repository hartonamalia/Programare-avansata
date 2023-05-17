package com.example.Laborator11.repository;

import com.example.Laborator11.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Adnotarea @Repository indica faptul ca aceasta interfata este un repository Spring si va fi gestionata de containerul Spring
// Repository-ul este responsabil pt persistenta entitatilor Player in bd si ofera o abstractie pentru interogarea si manipularea datelor
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
