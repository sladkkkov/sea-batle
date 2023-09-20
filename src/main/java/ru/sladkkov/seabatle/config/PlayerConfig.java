package ru.sladkkov.seabatle.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sladkkov.seabatle.model.GameBoard;
import ru.sladkkov.seabatle.model.Player;

@Configuration
@RequiredArgsConstructor
public class PlayerConfig {

    private final GameBoard gameBoard;

    @Bean
    public Player firstPlayer() {
        return new Player(gameBoard, "First Player Name");
    }

    @Bean
    public Player secondPlayer() {
        return new Player(gameBoard, "Second Player Name");
    }
}

