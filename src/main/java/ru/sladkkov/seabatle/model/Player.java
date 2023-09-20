package ru.sladkkov.seabatle.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class Player {

    private final GameBoard gameBoard;

    private final String name;

    @Value("${board.size}")
    private int boardSize;

    public Player(GameBoard gameBoard, String name) {
        this.gameBoard = gameBoard;
        this.name = name;
    }

    public boolean isDefeated() {
        return gameBoard.isAllShipsNotAlive();
    }

    public boolean shot(String index) {
        return gameBoard.shot(index);
    }

}
