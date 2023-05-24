package com.example.Laborator11.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class GameRequestDto implements Serializable {
    @NotBlank(message = "Name for players is required!")
    private String name1;
    @NotBlank(message = "Name for players is required!")
    private String name2;
    @NotBlank(message = "Winner's name is required!!")
    private String winner;

    public GameRequestDto()  {
    }

    public GameRequestDto(String name1, String name2, String winner) {
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

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
