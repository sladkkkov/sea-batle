package ru.sladkkov.seabatle.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.sladkkov.seabatle.model.Ship;
import ru.sladkkov.seabatle.enums.ShipType;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
@Component
@Scope("prototype")
@Slf4j
public class GameBoard {

    private final char[][] board;
    private final List<Ship> ships;

    @Value("${board.symbol.empty-space}")
    public char emptySpaceSymbol;

    @Value("${board.symbol.ship}")
    public char shipSymbol;

    @Value("${board.symbol.shot}")
    public char shotSymbol;

    @Value("${board.symbol.hit}")
    public char hitSymbol;

    @PostConstruct
    public void autoPlaceShips() {
        for (ShipType shipType : ShipType.values()) {
            for (int i = 0; i < shipType.getCountShip(); i++) {

                boolean placed = false;
                while (!placed) {

                    int x = ThreadLocalRandom.current().nextInt(board.length);
                    int y = ThreadLocalRandom.current().nextInt(board.length);
                    boolean horizontal = ThreadLocalRandom.current().nextBoolean();

                    if (isValidPlacement(x, y, shipType.getSize(), horizontal)) {
                        placeShip(x, y, shipType.getSize(), horizontal);
                        placed = true;
                    }
                }
            }
        }
    }

    public void displayBoardForEnemy() {
        System.out.println("  A  B  C  D  E  F  G  H  I  J");

        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < board.length; j++) {

                char currentChar = board[i][j];
                switch (currentChar) {
                    case '⊠':
                        System.out.print(hitSymbol + " ");
                        break;
                    case '⊡':
                        System.out.print(shotSymbol + " ");
                        break;
                    default:
                        System.out.print(emptySpaceSymbol + " ");
                        break;
                }
            }
            System.out.println();
        }
    }

    private boolean isValidPlacement(int x, int y, int size, boolean horizontal) {
        if (x < 0 || x >= board.length || y < 0 || y >= board.length) {
            return false;
        }

        if (horizontal) {
            if (y + size > board.length) {
                return false;
            }

            for (int i = y; i < y + size; i++) {
                if (board[x][i] != emptySpaceSymbol) {
                    return false;
                }

                if (x > 0 && board[x - 1][i] != emptySpaceSymbol) {
                    return false;
                }

                if (x < board.length - 1 && board[x + 1][i] != emptySpaceSymbol) {
                    return false;
                }
            }
        } else {
            if (x + size > board.length) {
                return false;
            }

            for (int i = x; i < x + size; i++) {
                if (board[i][y] != ' ') {
                    return false;
                }


                if (y > 0 && board[i][y - 1] != emptySpaceSymbol) {
                    return false;
                }

                if (y < board.length - 1 && board[i][y + 1] != emptySpaceSymbol) {
                    return false;
                }
            }
        }

        return true;
    }

    private void placeShip(int x, int y, int size, boolean horizontal) {
        if (horizontal) {
            for (int i = y; i < y + size; i++) {
                board[x][i] = shipSymbol;
            }
        } else {
            for (int i = x; i < x + size; i++) {
                board[i][y] = shipSymbol;
            }
        }

        ships.add(new Ship(size));
    }

    public boolean shot(String target) {
        int x = convertCoordinateX(target);
        int y = Integer.parseInt(String.valueOf(target.charAt(1)));
        if (x >= 0 && x < board.length && y >= 0 && y < board.length) {
            if (board[y][x] == shipSymbol) {
                board[y][x] = hitSymbol;
                return true;
            } else if (board[y][x] == emptySpaceSymbol) {
                board[y][x] = shotSymbol;
                return false;
            }
        }
        return false;
    }

    private int convertCoordinateX(String coordinate) {
        if (coordinate.length() != 2) {
            throw new IllegalArgumentException("Ошибка ввода координаты");
        }

        char letter = coordinate.toUpperCase().charAt(0);
        if (letter < 'A' || letter > 'J') {
            throw new IllegalArgumentException("Неправильная координата: " + coordinate);
        }
        return 'A' - 65;
    }

    public boolean isAllShipsNotAlive() {
        return ships.stream()
                .map(Ship::isAlive)
                .allMatch(hits -> hits.equals(true));
    }
}
