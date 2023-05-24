package com.example.Laborator11.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class GameResponseDto implements Serializable {
    private String name1;
    private String name2;
    private String winner;

    public GameResponseDto() {
    }

    public GameResponseDto(String name1, String name2, String winner) {
        this.name1 = name1;
        this.name2 = name2;
        this.winner = winner;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getWinner() {
        return winner;
    }
}
