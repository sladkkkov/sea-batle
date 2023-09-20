package ru.sladkkov.seabatle.service;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sladkkov.seabatle.model.Player;

import java.util.Scanner;

@RequiredArgsConstructor
@Service
@Slf4j
public class ShipService {

    private boolean turnFirstPlayer;

    private final Player firstPlayer;
    private final Player secondPlayer;

    public void play() {
        @Cleanup Scanner scanner = new Scanner(System.in);

        while (!isGameOver()) {
            Player currentPlayer = turnFirstPlayer ? firstPlayer : secondPlayer;
            Player opponent = turnFirstPlayer ? secondPlayer : firstPlayer;

            log.info(currentPlayer.getName() + " ход: ");
            opponent.getGameBoard().displayBoardForEnemy();
            log.info("Введите координаты например F5: ");
            String target = scanner.nextLine();

            if (opponent.shot(target)) {
                log.info("Попал!");
            } else {
                log.info("Промазал!");
            }

            turnFirstPlayer = !turnFirstPlayer;
        }
        log.info("Игра закончена!");
    }

    private boolean isGameOver() {
        return firstPlayer.isDefeated() || secondPlayer.isDefeated();

    }
}
