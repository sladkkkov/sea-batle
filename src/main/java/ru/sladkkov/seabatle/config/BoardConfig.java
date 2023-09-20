package ru.sladkkov.seabatle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.sladkkov.seabatle.model.Ship;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BoardConfig {

    @Value("${board.size}")
    public int boardSize;

    @Value("${board.symbol.empty-space}")
    public char emptySpaceSymbol;

    @Bean
    @Scope("prototype")
    public char[][] board() {
        char[][] chars = new char[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                chars[i][j] = emptySpaceSymbol;
            }
        }

        return chars;
    }

    @Bean
    public List<Ship> ships() {
        return new ArrayList<>(boardSize);
    }
}
